package com.jodongari.handy.file;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest(classes= FileObjectStorageService.class)
class FileObjectStorageServiceTest {

    @Autowired
    FileObjectStorageService fileObjectStorageService;
    private static final String HANDY_IMAGE_BUCKET_NAME = "handy-image";
    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    @Test
    @DisplayName("S3 image upload test")
    void putObject() throws URISyntaxException, IOException {

        String fileName = "test";
        String contentType = "jpg";
        String filePath = "src/main/resources/test.jpg";
        MockMultipartFile imageFile = makeMultiPartFile(fileName, contentType, filePath);

        String url = fileObjectStorageService.uploadObjectToS3(imageFile);

        Assertions.assertEquals(url, fileName);

//        S3Object o = s3.getObject(HANDY_IMAGE_BUCKET_NAME, url);
//        S3ObjectInputStream s3is = o.getObjectContent();
//        File expectedFile = new File("./resources/" + url);
//        FileUtils.copyInputStreamToFile(s3is, expectedFile);
//        Assertions.assertEquals(FileUtils.readLines(expectedFile, "UTF-8"), FileUtils.readLines(origin, "UTF-8"));
//        expectedFile.deleteOnExit();
//        s3is.close();
    }

    private MockMultipartFile makeMultiPartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}
