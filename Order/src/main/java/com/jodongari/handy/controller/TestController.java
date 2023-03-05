package com.jodongari.handy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping("/data")
    public ResponseEntity<String> setRedisData() {
        redisTemplate.opsForValue().set("key", "value");
        return new ResponseEntity<>("정상 등록", HttpStatus.CREATED);
    }

    @GetMapping("/data")
    public ResponseEntity<String> getRedisData(){
        return new ResponseEntity<>(redisTemplate.opsForValue().get("key"), HttpStatus.OK);
    }
}
