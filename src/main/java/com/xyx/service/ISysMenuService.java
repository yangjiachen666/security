package com.xyx.service;

import com.xyx.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xyx.entity.SysUser;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xyx_yjc
 * @since 2021-08-17
 */
public interface ISysMenuService extends IService<SysMenu> {

    Set<String> findMenuListByUser(SysUser sysUser);

}
