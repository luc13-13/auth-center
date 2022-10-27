package com.lc.auth.center.model;
 
import java.util.Date;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
 
/**
 * 登录日志(LoginLog)实体类
 *
 * @author lucheng
 * @since 2022-04-30 20:23:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_center.login_log")
public class LoginLogDO {

    /** 主键id */     
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户名，用于登录和显示 */     
    private String loginName;


    /** 登陆时间 */     
    private Date loginTime;


    /** 登陆地点 */     
    private String loginLocation;


    /** 登陆IP,兼容IPv6, 只存转换后的内容 */     
    private String loginIp;


    /** 操作系统 */     
    private String loginSystem;


    /** 浏览器 */     
    private String loginBrowser;


    /** 创建时间 */     
    @TableField(fill = FieldFill.INSERT)
    private Date dtCreated;

    /** 创建时间 */     
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date dtModified;

}
