package com.vti.bookinghospitalbackend.security;

import com.vti.bookinghospitalbackend.exception.ApiException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;


import java.io.IOException;
import java.util.Optional;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        System.out.println("doFilterInternal_1");
//        try {
//            System.out.println("doFilterInternal_2");
//
//            String jwt = getJwt(request);
//            Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(jwt);
//            System.out.println("doFilterInternal_3");
//
//            if (jwt != null && jwtUtils.validateJwtToken2(jwt)) {
//                String username = jwtUtils.getUserNameFromJwtToken2(jwt);
//
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
////                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
////                        userDetails, null, userDetails.getAuthorities());
//                //   authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            logger.error("Can NOT set user authentication -> Message: {}", e);
//        }
//        System.out.println("doFilterInternal_3");
//
//        filterChain.doFilter(request, response);
//
//        System.out.println("doFilterInternal_31");
//
////        try {
////            String jwt = parseJwt(request);
////            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
////                String username = jwtUtils.getUserNameFromJwtToken(jwt);
////
////                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////                UsernamePasswordAuthenticationToken authentication =
////                        new UsernamePasswordAuthenticationToken(
////                                userDetails,
////                                null,
////                                userDetails.getAuthorities());
////                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////                SecurityContextHolder.getContext().setAuthentication(authentication);
////            }
////        } catch (Exception e) {
////            logger.error("Cannot set user authentication: {}", e);
////        }
////
////        filterChain.doFilter(request, response);
//    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ApiException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}