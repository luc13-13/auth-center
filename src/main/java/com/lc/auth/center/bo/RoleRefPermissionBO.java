package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限关系表(RoleRefPermission)业务对象
 *
 * @author lucheng
 * @since 2022-04-29 14:33:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRefPermissionBO implements Serializable {

    private static final long serialVersionUID = 463659200213801343L;

    /*** 角色id */    
    private Long roleId;

    /*** 角色描述 */
    private String roleDesc;

    /*** 权限id */    
    private Long permissionId;

    /*** 权限描述 */
    private String permissionDesc;

    /*** 角色权限是否失效,0 已失效, 1 未失效 */    
    private Integer invalid;

    /*** 创建时间 */    
    private Date dtCreated;

    /*** 创建时间 */    
    private Date dtModified;

}
