package za.co.simplitate.springsecurity.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static za.co.simplitate.springsecurity.util.Constants.*;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(AUTHORIZATION);
        try {
            Claims claims = getClaims(jwt);
            Authentication auth = getAuthentication(claims);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received");
        }
        filterChain.doFilter(request,response);
    }

    private static Authentication getAuthentication(Claims claims) {
        String username = String.valueOf(claims.get(USERNAME));
        String authorities = String.valueOf(claims.get(AUTHORITIES));
        return new UsernamePasswordAuthenticationToken(username, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    private Claims getClaims(String jwt) {
        Environment env = getEnvironment();
        String secret = !StringUtils.isEmpty(env.getProperty(JWT_SECRET_KEY)) ? env.getProperty(JWT_SECRET_KEY)
                                                                                : JWT_SECRET_DEFAULT;
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(jwt).getPayload();
    }

    // when the endpoint is /user framework should NOT invoke this filter
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user");
    }

}
