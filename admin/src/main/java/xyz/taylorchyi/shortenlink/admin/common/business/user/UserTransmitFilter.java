package xyz.taylorchyi.shortenlink.admin.common.business.user;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate hasUserLoggedInRedisTemplate;
    private final StringRedisTemplate loggedInUserInfoRedisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            Boolean doesUserLogin = loggedInUserInfoRedisTemplate.hasKey(token);
            if (doesUserLogin != null && doesUserLogin) {
                UserInfoDTO userInfoDTO = JSON.parseObject(loggedInUserInfoRedisTemplate.opsForValue().get(token), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}