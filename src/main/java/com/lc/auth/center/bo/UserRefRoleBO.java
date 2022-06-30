package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户权限关系表(UserRefRole)业务对象
 *
 * @author lucheng
 * @since 2022-04-29 14:34:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRefRoleBO implements Serializable {

    private static final long serialVersionUID = -46671066435613575L;

    /*** 主键id */    
    private Long id;

    /*** 用户id */    
    private Long userId;

    /*** 权限id */    
    private Long roleId;

    /*** 权限是否失效, 0 已失效, 1 未失效 */    
    private Integer invalid;

    /*** 权限到期日期 */    
    private Date expiredData;

    /*** 创建时间 */    
    private Date dtCreated;

    /*** 修改时间 */    
    private Date dtModified;

}
