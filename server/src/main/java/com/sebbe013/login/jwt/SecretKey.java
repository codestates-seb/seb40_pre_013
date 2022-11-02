package com.sebbe013.login.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

//서명 키 생성클래스
@Component
public class SecretKey {

    @Getter
    @Value("${jwt.secret-key}")
    private String baseKey;

    //secretkey 생성메서드
    public Key getSecretKey(String baseKey){
        String encodeSecretKey = encodeSecretKey(baseKey);
        return getKeyFromEncodedKey(encodeSecretKey);
    }

    //기본 키를 base64로 인코딩해준다.
    public String encodeSecretKey( String baseKey ){
        return Encoders.BASE64.encode(baseKey.getBytes(StandardCharsets.UTF_8));
    }

    //인코딩한 기본 키를 시크릿키로 만들어준다.
    public Key getKeyFromEncodedKey( String encodingKey ){
        byte[] keyBytes = Decoders.BASE64.decode(encodingKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
