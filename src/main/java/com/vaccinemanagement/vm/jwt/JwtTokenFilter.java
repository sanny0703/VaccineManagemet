package com.vaccinemanagement.vm.jwt;

import com.vaccinemanagement.vm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // if the request doesn't have authorization header continue filtering
       if(!hasAuthorizationHeader(request)){
           filterChain.doFilter(request,response);
           return;
       }
       String accessToken = getAccessToken(request);
       // if the access token is not valid continue filtering
       if(!jwtTokenUtil.validateAccessToken(accessToken)){
           filterChain.doFilter(request,response);
           return;
       }
       setAuthenticationContext(accessToken,request);
       filterChain.doFilter(request,response);
    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(accessToken);
        // setting the user details for spring security to configure the user
        // here we are defining our own configure method for every request, the user makes
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,null,null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String accessToken) {
        User userDetails = new User();
        String[] subjectArray = jwtTokenUtil.getSubject(accessToken).split(",");
        userDetails.setUserId(Integer.parseInt(subjectArray[0]));
        userDetails.setUserEmail(subjectArray[1]);
        return userDetails;
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"))
            return false;
        return  true;
    }
    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return  token;
    }
}
