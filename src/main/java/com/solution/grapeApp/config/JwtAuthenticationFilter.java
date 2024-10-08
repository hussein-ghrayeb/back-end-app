package com.solution.grapeApp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.solution.grapeApp.enums.AuthType;
import com.solution.grapeApp.services.CustomerDetailsService;
import com.solution.grapeApp.services.EmployeeDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final EmployeeDetailsService employeeDetailsService;
    private final CustomerDetailsService customerDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            if (request.getHeader("Auth-Type").equals("CUSTOMER")) {
                userDetails = this.customerDetailsService.loadUserByUsername(username);
            } else if (request.getHeader("Auth-Type").equals("EMPLOYEE")) {
                userDetails = this.employeeDetailsService.loadUserByUsername(username);
            } else {
                userDetails = this.userDetailsService.loadUserByUsername(username);
            }
            // if (request.getHeader("Auth-Type").equals("CUSTOMER")) {
            // System.out.println(AuthType.CUSTOMER.name());
            // } else
            // userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
