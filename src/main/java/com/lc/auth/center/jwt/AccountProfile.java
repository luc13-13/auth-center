package com.lc.auth.center.jwt;

import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 认证成功后AccountRealm的信息载体,
 * @Author: Lu Cheng
 * @Data: 2022/4/29 17:39
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfile implements Serializable {
    private Long id;
    private String loginName;
    private Integer invalid;
    private Set<String> roleSet;
    private Set<String> uPermissionSet;
}
