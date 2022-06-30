package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表(Role)业务对象
 *
 * @author lucheng
 * @since 2022-04-29 14:30:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleBO implements Serializable {

    private static final long serialVersionUID = -60432934106585701L;

    /*** 主键id */    
    private Long id;

    /*** 权限描述 */    
    private String roleDesc;

    /*** 创建时间 */    
    private Date dtCreated;

    /*** 创建时间 */    
    private Date dtModified;

}
