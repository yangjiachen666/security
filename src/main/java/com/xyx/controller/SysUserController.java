package com.xyx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xyx.entity.SysUser;
import com.xyx.service.ISysUserService;
import com.xyx.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户Controller
 * @author xyx_yjc
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/sys-user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(value = "user", tags = "用户信息管理")
public class SysUserController {

    private final ISysUserService iSysUserService;

    @PreAuthorize("@ss.hasPermi('sys:user:list')")
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "测试")
    public List<SysUser> list(@RequestBody SysUser sysUser){
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotEmpty(sysUser.getUsername())){
            wrapper.like(SysUser::getUsername, sysUser.getUsername());
        }
        return iSysUserService.list(wrapper);
    }




}
