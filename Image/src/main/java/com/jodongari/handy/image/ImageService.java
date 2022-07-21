package com.jodongari.handy.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ImageService {

    private static final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    public String uploadObjectToS3(File imageFile){
        // TODO : Extension 검사 코드 구현

        String key = String.valueOf(Hashing.sha256().hashString(String.valueOf(System.currentTimeMillis()), StandardCharsets.UTF_8));

        try {
            s3.putObject(HANDY_IMAGE_BUCKET_NAME, key, imageFile);
        } catch (AmazonServiceException e) {
            // TODO : 커스텀 익셉션으로 내려보내서 어떤 에러인지 파악 가능하도록 변경할 것
            log.error(e.getErrorMessage());
        }

        return key;
    }

}
