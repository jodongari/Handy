package com.jodongari.handy.service.impl;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.Order;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.exception.ErrorCode;
import com.jodongari.handy.protocol.exception.PaymentException;
import com.jodongari.handy.protocol.exception.SessionNotFoundException;
import com.jodongari.handy.protocol.model.MenuModel;
import com.jodongari.handy.protocol.session.KakaoPaymentSession;
import com.jodongari.handy.service.KakaoApiService;
import com.jodongari.handy.service.KakaoPaymentService;
import com.jodongari.handy.service.OrderDomainService;
import com.jodongari.handy.service.SessionDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class KakaoPaymentServiceImpl implements KakaoPaymentService {

    private final OrderDomainService orderDomainService;
    private final ModelMapper modelMapper;
    private final KakaoApiService kakaoApiService;
    private final SessionDomainService sessionDomainService;

    @Transactional(rollbackFor = Exception.class)
    public ReadyKakaoPaymentResponse readyKakaoPayment(ReadyPaymentRequest request) {

        final Order order = modelMapper.map(request, Order.class);
        final String orderSeq = Hashing.sha256().hashString(UUID.randomUUID().toString(), StandardCharsets.UTF_8).toString();
        order.setOrderSeq(orderSeq);

        final String parsedMenuName = parseMenuName(order.getMenus());
        final Integer totalAmount = getTotalAmount(order.getMenus());

        sessionDomainService.insertData(orderSeq, order);
        
        final ReadyKakaoPaymentResponse response = kakaoApiService.callKakaoPaymentReady(order.getOrderSeq(), order.getUserSeq().toString(), parsedMenuName, totalAmount);

        final KakaoPaymentSession session = KakaoPaymentSession.builder()
                .partnerOrderId(order.getOrderSeq())
                .tid(response.getTid())
                .partnerUserId(order.getUserSeq().toString())
                .build();

        try {
            sessionDomainService.insertSession(session.getPartnerOrderId(), session);
        } catch (Exception e) {
            throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "redis insert error");
        }

        return response;
    }

    public void approveKakaoPayment(String partnerOrderId, String pgToken) {

        try {
            final Order order = sessionDomainService.getData(partnerOrderId, Order.class);
            final KakaoPaymentSession kakaoPaymentSession = sessionDomainService.getSession(partnerOrderId, KakaoPaymentSession.class);

            // validation
            
            kakaoApiService.callKakaoPaymentApprove(kakaoPaymentSession.getTid(), partnerOrderId, kakaoPaymentSession.getPartnerUserId(), pgToken);
            orderDomainService.registerOrder(order);

        } catch (SessionNotFoundException e) {
            throw new PaymentException(ErrorCode.NOT_FOUND, "Session not found");
        } catch (Exception e) {
            log.error("Unknown exception occurred.", e);
            throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "Unknown exception occurred.");
        }
    }

    @Override
    public void failKakaoPayment(String partnerOrderId) {
        throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "Payment failed.");
    }

    @Override
    public void cancelKakaoPayment(String partnerOrderId) {
        throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "Payment failed.");
    }

    private String parseMenuName(List<MenuModel> menus) {
        final String firstMenuName = menus.get(0).getName();
        final int count = menus.size() - 1;

        return String.format("%s 외 %d건", firstMenuName, count);
    }

    private Integer getTotalAmount(List<MenuModel> menus) {
        return menus.stream().mapToInt(
                menu -> {
                    int result = 0;
                    result += menu.getCount() * menu.getMenuOption().getPrice();
                    if (menu.getExtraOptionGroups() != null) {
                        result += menu.getExtraOptionGroups().stream()
                                .filter(Objects::nonNull)
                                .mapToInt(extraOptionGroup -> extraOptionGroup.getExtraOptions().stream().mapToInt(
                                        extraOption -> extraOption.getCount() * extraOption.getPrice()
                                ).sum()).sum();
                    }
                    return result;
                }
        ).sum();
    }
}
