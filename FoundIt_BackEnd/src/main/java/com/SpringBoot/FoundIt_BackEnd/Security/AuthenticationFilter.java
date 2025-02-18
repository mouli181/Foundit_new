package com.SpringBoot.FoundIt_BackEnd.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class  AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Objects.requireNonNull(request, "Request cannot be null");
        Objects.requireNonNull(response, "Response cannot be null");
        Objects.requireNonNull(filterChain, "FilterChain cannot be null");
        final String authentication = request.getHeader("Authorization");
        final String jwt;
        final String email;
        if(authentication==null|| !authentication.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return ;
        }
        jwt = authentication.substring(7);
        email = jwtUtils.extractEmail(jwt);
        if(email!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtUtils.tokenValidation(jwt,userDetails)){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,jwtUtils.extractRole(jwt));
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
                logger.info("UserName : "+jwtUtils.extractEmail(jwt)+" Permission Graded ....!");
            }
        }
        filterChain.doFilter(request,response);
    }
}

