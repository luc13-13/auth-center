package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录日志(LoginLog)业务对象
 *
 * @author lucheng
 * @since 2022-04-30 20:23:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginLogBO implements Serializable {

    private static final long serialVersionUID = 988124948687271872L;

    /*** 主键id */    
    private Long id;

    /*** 用户名，用于登录和显示 */    
    private String loginName;

    /*** 登陆时间 */    
    private Date loginTime;

    /*** 登陆地点 */    
    private String loginLocation;

    /*** 登陆IP,兼容IPv6, 只存转换后的内容 */    
    private String loginIp;

    /*** 操作系统 */    
    private String loginSystem;

    /*** 浏览器 */    
    private String loginBrowser;

    /*** 创建时间 */    
    private Date dtCreated;

    /*** 创建时间 */    
    private Date dtModified;

}
