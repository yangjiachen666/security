package com.xyx.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xyx.entity.SysUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // 设置请求头部
    public static final String TOKEN_HEADER = "Authorization";

    // TOKEN 的前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 设置token的过期时间
    public static final int EXPIRITION = 30;
    protected static final long MILLIS_SECOND = 1000;
    public static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    // 令牌密令
    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz";

    // 创建token
    public String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return token;
    }

    // 从token中获取存入的内容
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        JwtUtils jwt = new JwtUtils();
        HashMap<String, Object> map = new HashMap<>();
        String uuid = IdWorker.get32UUID();
        map.put("lonin_user_key", uuid);
        System.out.println("IdWorker = " + uuid);
        String token = jwt.createToken(map);
        System.out.println("token = " + token);

        Claims claims = jwt.parseToken(token);
        System.out.println(claims.get("lonin_user_key"));
    }

    public void setUserAgent(SysUser sysUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        sysUser.setIpaddr(ip);
        sysUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        sysUser.setBrowser(userAgent.getBrowser().getName());
        sysUser.setOs(userAgent.getOperatingSystem().getName());
    }

    // 验证是否过期
    public boolean isExpire(String token){
        return parseToken(token).getExpiration().before(new Date());
    }

    public static String finalToken(String token){
        if(StringUtils.isNotEmpty(token)){
            throw new RuntimeException(MessageUtils.message("param.error"));
        }
        return TOKEN_PREFIX + token;
    }

}
