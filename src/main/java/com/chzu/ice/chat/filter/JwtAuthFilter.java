package com.chzu.ice.chat.filter;

import com.chzu.ice.chat.util.JavaTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mason
 */
@Component
public class JasonTokenFilter extends OncePerRequestFilter {
    private static final String ACCESS_TOKEN = "AccessToken";
    private static final String REFRESH_TOKEN = "RefreshToken";
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JavaTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = httpServletRequest.getHeader(ACCESS_TOKEN);
        String refreshToken = httpServletRequest.getHeader(REFRESH_TOKEN);

        //当为accessToken时
        if (null != accessToken) {
            String username = tokenUtil.getUsernameFromToken(accessToken);
            System.out.println("用户名是:" + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (tokenUtil.validate(accessToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        //当为refreshToken时
        if(null != refreshToken) {

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
