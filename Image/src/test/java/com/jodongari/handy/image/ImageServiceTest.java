package com.jodongari.handy.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;


class ImageServiceTest {

    static final ImageService imageService = new ImageService();
    private final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    @Test
    void putObject() throws InterruptedException, URISyntaxException {
        File origin = new File(getClass().getClassLoader().getResource("test.png").toURI()); //테스트시 본인 파일 path 입력
        String url = imageService.putObject("1", origin);

        Thread.sleep(1000);
        System.out.println("url  : " + url);
        System.out.format("Downloading %s from S3 bucket %s...\n", url, HANDY_IMAGE_BUCKET_NAME);
        try {
            S3Object o = s3.getObject(HANDY_IMAGE_BUCKET_NAME, url);
            S3ObjectInputStream s3is = o.getObjectContent();
            File expectedFile = new File("/Users/macho/Documents/" + url + ".png");
            FileUtils.copyInputStreamToFile(s3is, expectedFile);
            Assertions.assertEquals(FileUtils.readLines(expectedFile, "UTF-8"), FileUtils.readLines(origin, "UTF-8"));
            expectedFile.deleteOnExit();
            s3is.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
