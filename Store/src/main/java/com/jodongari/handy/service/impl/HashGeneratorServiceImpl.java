package com.jodongari.handy.service.impl;

import com.jodongari.handy.service.HashGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HashGeneratorServiceImpl implements HashGeneratorService{

    @Override
    public String encrypt() throws NoSuchAlgorithmException {
        StringBuffer resultBuffer = new StringBuffer();

        String randomUUIDString = UUID.randomUUID().toString();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(randomUUIDString.getBytes());
        byte[] result = md.digest();
        for(byte b : result) {
            resultBuffer.append(String.format("%02x", b));
        }
        return resultBuffer.toString();
    }
}
