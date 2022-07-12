package com.jodongari.handy.image;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URISyntaxException;

@SpringBootTest(classes=ImageService.class)
class ImageServiceTest {

    @Autowired
    ImageService imageService;
    private static final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    @Test
    @DisplayName("S3 image upload test")
    void putObject() throws URISyntaxException, IOException {
        File origin = new File(getClass().getClassLoader().getResource("test.jpg").toURI());
        String url = imageService.putObject(".test", origin);
        S3Object o = s3.getObject(HANDY_IMAGE_BUCKET_NAME, url);
        S3ObjectInputStream s3is = o.getObjectContent();
        File expectedFile = new File("./resources/" + url);
        FileUtils.copyInputStreamToFile(s3is, expectedFile);
        Assertions.assertEquals(FileUtils.readLines(expectedFile, "UTF-8"), FileUtils.readLines(origin, "UTF-8"));
        expectedFile.deleteOnExit();
        s3is.close();
    }
}
