package com.jodongari.handy.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jodongari.handy.domain.PaymentSession;
import com.jodongari.handy.infrastructure.repository.redis.PaymentSessionRedisRepository;
import com.jodongari.handy.protocol.exception.SessionNotFoundException;
import com.jodongari.handy.service.SessionDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class SessionDomainServiceImpl implements SessionDomainService {

    private static final String SESSION_PREFIX = "session:";
    private static final String STORAGE_PREFIX = "storage:";
    private final PaymentSessionRedisRepository paymentSessionRedisRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public <T> void insertSession(String key, T session) {
        final PaymentSession storedSession = new PaymentSession();
        storedSession.setKey(SESSION_PREFIX + key);
        storedSession.setValue(toJsonString(session));
        paymentSessionRedisRepository.save(storedSession);
    }

    @Transactional
    public <T> void insertData(String key, T data) {
        final PaymentSession storedSession = new PaymentSession();
        storedSession.setKey(STORAGE_PREFIX + key);
        storedSession.setValue(toJsonString(data));
        paymentSessionRedisRepository.save(storedSession);
    }

    public <T> T getData(String key, Class<T> clazz) {
        return clazz.cast(toObject(paymentSessionRedisRepository.findById(STORAGE_PREFIX + key)
                .orElseThrow(() -> {
                    final String msg = String.format("Redis data is not found. key : %s", key);
                    log.error(msg);
                    throw new SessionNotFoundException("Redis data is not found.");
                }).getValue(), clazz));
    }

    public <T> T getSession(String key, Class<T> clazz) {
        return clazz.cast(toObject(paymentSessionRedisRepository.findById(SESSION_PREFIX + key)
                .orElseThrow(() -> {
                    final String msg = String.format("Redis session not found key : %s", key);
                    log.error(msg);
                    throw new SessionNotFoundException("Redis session not found.");
                }).getValue(), clazz));
    }

    private <T> String toJsonString(T session) {
        try {
            return objectMapper.writeValueAsString(session);
        } catch (Exception e) {
            final String msg = String.format("Failed to serialize session to JSON string. session: %s", session);
            log.error(msg, e);
            // TODO Change SessionException
            throw new SessionNotFoundException("exception");
        }
    }

    private <T> T toObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            final String msg = String.format("Failed to deserialize JSON to session object. JSON: %s", jsonString);
            log.error(msg, e);
            // TODO Change SessionException
            throw new SessionNotFoundException("exception");
        }
    }
}
