package com.jodongari.handy.file;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class FileObjectStorageService {

    private static final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private static final String DEFAULT_KEY = "default_key";
    private static final String BASE_URL_PATH = "https://handy-image.s3.ap-northeast-2.amazonaws.com";
    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    public String uploadObjectToS3(MultipartFile imageFile) throws IOException {
        // TODO : Extension 검사 코드 구현

        if (isExistFile(imageFile)) {
            // TODO : 빈 파일 exception 코드 구현
            return "";
        }

        final String key = String.valueOf(Hashing.sha256().hashString(String.valueOf(System.currentTimeMillis()), StandardCharsets.UTF_8));
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageFile.getContentType());
        objectMetadata.setContentLength(imageFile.getSize());

        try {
            s3.putObject(new PutObjectRequest(HANDY_IMAGE_BUCKET_NAME, key, imageFile.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            // TODO : 커스텀 익셉션으로 내려보내서 어떤 에러인지 파악 가능하도록 변경할 것
            log.error(e.getMessage());
        }

        return BASE_URL_PATH + "/" + key;
    }

    private boolean isExistFile(MultipartFile imageFile) {
        return imageFile.isEmpty();
    }

}
