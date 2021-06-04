package com.example.abstractionizer.login.session1.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class MD5Util {

    public static String md5(String s){
        char[] hexDigits = new char[]{'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();

        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = digest.digest();

            for(byte b: bytes){
                sb.append(hexDigits[b >>> 4 & 0x0F]);
                sb.append(hexDigits[b & 0x0F]);
            }

        }catch(Exception e){
            log.error("MD5Util hashed failed", e);
        }
        return sb.toString();
    }
}
