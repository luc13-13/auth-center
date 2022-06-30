package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 顾客账号信息表(User)业务对象
 *
 * @author lucheng
 * @since 2022-04-29 14:33:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBO implements Serializable {

    private static final long serialVersionUID = 700976283574603173L;

    /*** 主键id */    
    private Long id;

    /*** 用户名，用于登录和显示 */    
    private String loginName;

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
    private Date dtCreated;

    /*** 创建时间 */    
    private Date dtModified;

    private Set<String> roleSet;
    private Set<String> uPermissionSet;

}
