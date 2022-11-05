package com.sebbe013.login.jwt;

import io.jsonwebtoken.io.Decoders;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SecretKeyTest {


    @Autowired
    private SecretKey secretKey;

    @Test
    void 인코딩키_생성_메서드() throws Exception{ //베이스 키가 정상적으로 인코딩됨.
        //given
        String baseKey = "123123123123123sdkfn32123123";
        //when
        SecretKey secretKey = new SecretKey();
        String encodeSecretKey = secretKey.encodeSecretKey(baseKey);
        //then
        Assertions.assertThat(baseKey).isEqualTo(new String(Decoders.BASE64.decode(encodeSecretKey)));
    }

}