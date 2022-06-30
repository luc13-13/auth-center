package com.lc.auth.center.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 认证成功后AccountRealm的信息载体,
 * @author: lucheng
 * @data: 2022/4/29 17:39
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfile extends Object implements Serializable {
    private Long id;
    private String loginName;
    private Integer invalid;
}
