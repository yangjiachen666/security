package com.xyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xyx_yjc
 * @since 2021-08-16
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色名称")
    private String rolename;

    @TableField(exist = false)
    @ApiModelProperty(value = "ip地址")
    private String ipaddr;

    @TableField(exist = false)
    @ApiModelProperty(value = "登录地址")
    private String loginLocation;

    @TableField(exist = false)
    @ApiModelProperty(value = "登录浏览器")
    private String browser;

    @TableField(exist = false)
    @ApiModelProperty(value = "登录系统")
    private String os;

}
