package com.jodongari.handy.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ImageService {

    private static final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    public String putObject(String storePK, File imageFile){
        // TODO : Extension 검사 코드 구현

        String imageUrl = storePK + "/" + Hashing.sha256().hashString(storePK + System.currentTimeMillis(), StandardCharsets.UTF_8);

        try {
            s3.putObject(HANDY_IMAGE_BUCKET_NAME, imageUrl, imageFile);
        } catch (AmazonServiceException e) {
            // TODO : 커스텀 익셉션으로 내려보내서 어떤 에러인지 파악 가능하도록 변경할 것
            log.error(e.getErrorMessage());
        }
        return imageUrl;
    }

}
