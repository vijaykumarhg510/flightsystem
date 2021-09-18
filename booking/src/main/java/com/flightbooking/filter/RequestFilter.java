package com.flightbooking.filter;

import com.flightbooking.Mapper.GuestMapper;
import com.flightbooking.config.JwtConfig;
import com.flightbooking.get.GetGuest;
import com.flightbooking.repos.GuestRepository;
import com.flightbooking.service.GuestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GetGuest getGuest;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestUserService guestUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String userName;
        String jwtToken;

        if(requestTokenHeader != null  && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            userName = jwtConfig.getUsernameFromToken(jwtToken);

            if(userName !=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = guestUserService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,
                                userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
