package com.sebbe013.login.filter;

import com.sebbe013.login.jwt.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
jwt 인증 클래스
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    //클레임을 추출해서 Auth~에 저장하는 메서드
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException{
        log.info("권한");
        try{
            Map<String, Object> claims = verifyJws(request); //클레임 추출
            setAuthtoContext(claims);//Authentication에 저장
        } catch(SignatureException e){ //작동 안됨
            request.setAttribute("exception", e);
        } catch(ExpiredJwtException ee){
            request.setAttribute("exception", ee);
        } catch(Exception eee){
            request.setAttribute("exception", eee);
        }
        log.info("claims 완료");
        filterChain.doFilter(request, response); // 완료되면 다음 필터로 이동
    }

    //토큰 확인 후 예외 처리
    @Override
    protected boolean shouldNotFilter( HttpServletRequest request ) throws ServletException{
        String authorization = request.getHeader("Authorization"); // Authorization의 밸류값 획득
        return authorization == null || ! authorization.startsWith("Bearer");//헤더값이 null이거나 Bearer로 시작하지 않는다면(true를 리턴) 해당 필터가 수행되지 않음 예외처리
    }

    // 권한을 SecurityContextHoler에 저장하는 메서드
    private void setAuthtoContext( Map<String, Object> claims ){
        String username = (String) claims.get("username");// 해당 키값의 밸류 추출(이메일)
        List<String> roles = (List<String>) claims.get("roles");//해당 키값의 밸류 추출(역할)
        List<GrantedAuthority> authorties = roles.stream()//추출한 역할을 바탕으로 권한생성
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorties);//authentication에 유저네임과 권한 저장

        SecurityContextHolder.getContext().setAuthentication(authentication);//security~~에 저장
        log.info("sch= {}", SecurityContextHolder.getContext().getAuthentication().getDetails());
    }

    //jws 검증 메서드
    public Map<String, Object> verifyJws( HttpServletRequest request ){
        String jws = request.getHeader("Authorization").replace("Bearer ", "");//authorization의 밸류 값에서, brear 제거
        String encodedSecretKey = secretKey.encodeSecretKey(secretKey.getBaseKey());//  base64로 인코딩
        Key key = secretKey.getKeyFromEncodedKey(encodedSecretKey);//인코딩한 키를 시크릿키로 만들어줌

        Map<String, Object> claims = getClaims(jws, key).getBody();//key값과 jws값 이용해서 클레임 추출

        return claims;
    }

    //jws의 클레임 추출
    private Jws<Claims> getClaims( String jws, Key key ){

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key) // 시크릿 키 이용해서 토큰 해석
                .build().parseClaimsJws(jws); //클레임값 파싱

        return claimsJws;
    }

}
