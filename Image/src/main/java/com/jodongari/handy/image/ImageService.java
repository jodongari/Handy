package com.jodongari.handy.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;

public class ImageService {

    private final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    public String putObject(String storePK, File imageFile){
        String imageUrl = storePK + "/" +DigestUtils.md5Hex(storePK + System.currentTimeMillis());

        System.out.format("Uploading %s to S3 bucket %s...\n", imageUrl, HANDY_IMAGE_BUCKET_NAME);
        try {
            s3.putObject(HANDY_IMAGE_BUCKET_NAME, imageUrl, imageFile);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        System.out.println("Done");
        return imageUrl;
    }

}
