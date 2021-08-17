package com.xyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xyx.entity.SysUser;
import com.xyx.entity.jwt.JwtUser;
import com.xyx.service.ISysMenuService;
import com.xyx.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDetailServiceImpl implements UserDetailsService {

    private final ISysUserService iSysUserService;
    private final ISysMenuService iSysMenuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, s);
        try {
            SysUser sysUser = iSysUserService.getOne(wrapper);
            return createJwtUser(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private UserDetails createJwtUser(SysUser sysUser) {
        return new JwtUser(sysUser, iSysMenuService.findMenuListByUser(sysUser));
    }
}
