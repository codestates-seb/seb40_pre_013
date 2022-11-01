package com.sebbe013.login.filter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class Expiration {
    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public Date getExpiration( int tokenExpirationMinutes ){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, tokenExpirationMinutes);
        Date expiration = now.getTime();

        return expiration;
    }

}
