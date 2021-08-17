package com.xyx.mapper;

import com.xyx.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xyx_yjc
 * @since 2021-08-17
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> findMenuListByUser(Integer id);

}
