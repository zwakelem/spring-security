package za.co.simplitate.springsecurity.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static za.co.simplitate.springsecurity.util.Constants.*;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null != auth) {
            String secret = !StringUtils.isEmpty(getEnvironment().getProperty(JWT_SECRET_KEY)) ?
                    getEnvironment().getProperty(JWT_SECRET_KEY)  :
                    JWT_SECRET_DEFAULT;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
            String jwt = getJwtToken(auth, secretKey);
            response.setHeader(AUTHORIZATION, jwt);
        }
        filterChain.doFilter(request,response);
    }

    private static String getJwtToken(Authentication auth, SecretKey secretKey) {
        return Jwts.builder().issuer(EAZY_BANK).subject(JWT_TOKEN)
                .claim(USERNAME, auth.getName())
                .claim(AUTHORITIES, auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(secretKey).compact();
    }

    // when the endpoint is /user framework should invoke this filter, to generate token
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user") || !request.getServletPath().equals("/apiLogin");
    }

}
