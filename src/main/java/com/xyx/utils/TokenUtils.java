package com.xyx.utils;

import com.xyx.constants.RedisContants;
import com.xyx.entity.jwt.JwtUser;
import com.xyx.utils.cache.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtils {
    private static final long MILLIS_MINUTE_TEN = 1000 * 60 * 20;
    private final RedisCache redisCache = SpringUtils.getBean(RedisCache.class);

    public JwtUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = com.xyx.utils.interfaces.TokenUtils.parseToken(token);
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(RedisContants.CACHE_USER_KEY);
            return redisCache.getCacheObject(RedisContants.CACHE_USER_KEY + uuid);
        }
        return null;
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(JwtUtils.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(JwtUtils.TOKEN_PREFIX)) {
            token = token.replace(JwtUtils.TOKEN_PREFIX, "");
        }
        return token;
    }

    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = JwtUtils.TOKEN_PREFIX + token;
            redisCache.deleteObject(userKey);
        }
    }

    // 验证token
    public void verifyToken(JwtUser jwtUser) {
        long expireTime = jwtUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(jwtUser);
        }
    }

    // 刷新 token 缓存时间
    public void refreshToken(JwtUser jwtUser) {
        jwtUser.setLoginTime(System.currentTimeMillis());
        jwtUser.setExpireTime(jwtUser.getLoginTime() + JwtUtils.EXPIRITION * JwtUtils.MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = RedisContants.CACHE_USER_KEY + jwtUser.getToken();
        redisCache.setCacheObject(userKey, jwtUser, JwtUtils.EXPIRITION, TimeUnit.MINUTES);
    }
}
