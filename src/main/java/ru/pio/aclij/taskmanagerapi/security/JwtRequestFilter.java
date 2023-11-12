package ru.pio.aclij.taskmanagerapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.pio.aclij.taskmanagerapi.security.exceptions.AuthenticationFailedException;
import ru.pio.aclij.taskmanagerapi.security.utils.JwtTokenUtils;
import ru.pio.aclij.taskmanagerapi.security.utils.TokenAuthenticationHelper;

import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log
@Component("JwtRequestFilterOnce")
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils utils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = TokenAuthenticationHelper.getTokenFromRequest(request);
        String username = null;
        if (jwtToken != null)
            try {
                username = utils.getUsername(jwtToken);
            } catch (ExpiredJwtException e){
                log.info("Token expired");
            }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    utils.getRoles(jwtToken).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
