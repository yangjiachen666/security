package com.xyx.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xyx.constants.RedisContants;
import com.xyx.entity.SysUser;
import com.xyx.entity.jwt.JwtUser;
import com.xyx.entity.response.AppResponse;
import com.xyx.service.ISysUserService;
import com.xyx.utils.JwtUtils;
import com.xyx.utils.cache.RedisCache;
import com.xyx.utils.interfaces.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@CrossOrigin
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final RedisCache redisCache;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ISysUserService iSysUserService;

    @PostMapping("/login")
    public Map<String,String> login(String username, String password){
        // 验证成功之后又走UserDetailServiceImpl中的loadUserByUsername方法，将权限存入进去
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        System.out.println(" 验证成功，自动走哪个方法呢?");
        JwtUser jwtUser = (JwtUser)authenticate.getPrincipal();

        String token = IdWorker.get32UUID();
        jwtUser.setToken(token);

        jwtUser.setLoginTime(System.currentTimeMillis());
        jwtUser.setExpireTime(jwtUser.getLoginTime() + JwtUtils.EXPIRITION * JwtUtils.MILLIS_MINUTE);

        // 根据uuid将loginUser缓存
        String userKey = (RedisContants.CACHE_USER_KEY + jwtUser.getToken());
        redisCache.setCacheObject(userKey, jwtUser, JwtUtils.EXPIRITION, TimeUnit.MINUTES);
        HashMap<String, String> result = new HashMap<>();

        HashMap<String, Object> calim = new HashMap<>();
        calim.put(RedisContants.CACHE_USER_KEY, token);

        result.put("token",TokenUtils.createToken(calim));
        return result;
    }

    @PostMapping("/register")
    public AppResponse register(String username,String password){
        final SysUser user = SysUser.builder().username(username).password(bCryptPasswordEncoder.encode(password)).build();
        iSysUserService.save(user);
        return AppResponse.success();
    }



}


