package com.sebbe013.login.filter;

import com.sebbe013.login.jwt.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

@RequiredArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    @Override
    protected boolean shouldNotFilter( HttpServletRequest request ) throws ServletException{
        String authorization = request.getHeader("Authorization");
        return authorization == null || !authorization.startsWith("Bearer");//헤더값이 null이거나 Bearer로 시작하지 않는다면(true를 리턴) 해당 필터가 수행되지 않음
    }


    private void setAuthtoContext( Map<String, Object> claims ){
        String username = (String) claims.get("username");
        List<String> roles = (List<String>) claims.get("roles");
        List<GrantedAuthority> authorties = roles.stream()
                                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorties);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Map<String, Object> verifyJws( HttpServletRequest request ){
        String jws = request.getHeader("Authorization").replace("Bearer ", "");//토큰부분만 남아있게 한다.
        String encodedSecretKey = secretKey.encodeSecretKey(secretKey.getBaseKey());
        Key key = secretKey.getKeyFromEncodedKey(encodedSecretKey);

        Map<String, Object> claims = getClaims(jws, key).getBody();

        return claims;
    }
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException{
        log.info("권한");
        Map<String, Object> claims = verifyJws(request);
        log.info("claims 완료");
        setAuthtoContext(claims);
        filterChain.doFilter(request, response);
    }
    public Jws<Claims> getClaims( String jws, Key key){

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

        return claimsJws;
    }

}
