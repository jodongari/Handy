package com.jodongari.handy.service;

import java.security.NoSuchAlgorithmException;

public interface HashGeneratorService {

    String encrypt() throws NoSuchAlgorithmException;

}
