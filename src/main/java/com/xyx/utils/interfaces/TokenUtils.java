package com.xyx.utils.interfaces;

import com.xyx.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import java.util.Map;

public class TokenUtils {

    private static JwtUtils util = new JwtUtils();

    public static String createToken(Map<String, Object> claims){
        return util.createToken(claims);
    }

    public static Claims parseToken(String token){
        return util.parseToken(token);
    }
}
