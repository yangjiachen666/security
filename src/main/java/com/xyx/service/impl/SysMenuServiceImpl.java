package com.xyx.service.impl;

import com.xyx.entity.SysMenu;
import com.xyx.entity.SysUser;
import com.xyx.mapper.SysMenuMapper;
import com.xyx.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xyx_yjc
 * @since 2021-08-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> findMenuListByUser(SysUser sysUser) {
        return new HashSet<>(sysMenuMapper.findMenuListByUser(sysUser.getId()));
    }
}
