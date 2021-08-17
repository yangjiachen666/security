package com.xyx.entity.jwt;

import com.xyx.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {

    // 用户登录的token
    private String token;

    // 登录时间
    private Long loginTime;

    // 过期时间
    private Long expireTime;

    // 权限列表
    private Set<String> permissions;

    // 用户信息
    private SysUser sysUser;

    public JwtUser(SysUser sysUser, Set<String> permissions){
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
