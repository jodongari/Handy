package com.jodongari.handy.service;

public interface SessionDomainService {
    public <T> void insertSession(String key, T session);
    public <T> void insertData(String key, T session);
    public <T> T getData(String key, Class<T> clazz);
    public <T> T getSession(String key, Class<T> clazz);
}
