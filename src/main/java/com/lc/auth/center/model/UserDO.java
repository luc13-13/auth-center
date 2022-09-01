package com.lc.auth.center.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 顾客账号信息表(User)实体类
 *
 * @author lucheng
 * @since 2022-04-29 14:33:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_center.user")
public class UserDO {

    /*** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @NotEmpty
    /*** 用户名，用于登录和显示 */
    private String loginName;

    @Email(message = "邮箱格式有误")
    /*** 用户邮箱 */    
    private String email;

    /*** 手机号 */    
    private Long phone;

    /*** 身份证号 */    
    private String idCard;

    /*** 经后端加密后的密码 */    
    private String pwd;

    /*** 顾客是否注销,0 已注销, 1 未注销 */    
    private Integer invalid;

    /*** 登陆来源 */    
    private String loginSource;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date dtCreated;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date dtModified;

}
