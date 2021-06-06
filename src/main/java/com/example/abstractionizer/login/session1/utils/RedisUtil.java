package com.example.abstractionizer.login.session1.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.security.jarsigner.JarSignerException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean set(@NonNull final String key, @NonNull final Object obj, @NonNull final long duration, @NonNull final TimeUnit timeUnit){
        try{
            this.redisTemplate.boundValueOps(key).set(obj, duration, timeUnit);
            return true;
        }catch(Exception var1){
            try{
                log.error("RedisUtil set object failed, key: {} obj: {}", key, objectMapper.writeValueAsString(obj), var1);
            }catch(JsonProcessingException var2){
                log.error("Object mapper processing json failed", var2);
            }
        }
        return false;
    }

    public <T> T get(@NonNull final String key, @NonNull final Class<T> cls){
        Object obj;
        try{
            obj = this.redisTemplate.boundValueOps(key).get();
        }catch(Exception var3){
            log.error("RedisUtil get obj failed, key: {}", key, var3);
            return null;
        }
        return cls.cast(obj);
    }

    public boolean isKeyExists(@NonNull final String key){
        if(key.isEmpty()){
            return false;
        }
        return Objects.nonNull(this.get(key, Object.class));
    }

    public long increment(@NonNull final String key, @NonNull long interval){
        return this.redisTemplate.boundValueOps(key).increment(interval);
    }

    public boolean delete(@NonNull final String key){
        return key.isEmpty() || this.redisTemplate.delete(key);
    }
}
