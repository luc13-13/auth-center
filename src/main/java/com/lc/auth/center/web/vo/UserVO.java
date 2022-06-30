package com.lc.auth.center.web.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lucheng
 * @data: 2022/5/1 20:53
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVO {
    /*** 用户名，用于登录和显示 */
    private String loginName;

    /*** 用户邮箱 */
    private String email;

    /*** 手机号 */
    private Long phone;

    /*** 身份证号 */
    private String idCard;


    /*** 顾客是否注销,0 已注销, 1 未注销 */
    private Integer invalid;

    /*** 登陆来源 */
    private String loginSource;
}
