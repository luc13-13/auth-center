package com.lc.auth.center.model.ex;

import com.lc.auth.center.model.UserDO;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/5/1 22:51
 * @version: 1.0
 */
@Data
public class UserDOex extends UserDO {
    private Long roleId;
    private String roleDesc;
    private Long permissionId;
    private String permissionDesc;
}
