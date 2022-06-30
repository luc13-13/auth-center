package com.lc.auth.center.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作权限表(UPermission)业务对象
 *
 * @author lucheng
 * @since 2022-04-29 14:33:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UPermissionBO implements Serializable {

    private static final long serialVersionUID = -34620495405542805L;

    /*** 主键id */    
    private Long id;

    /*** 权限描述 */    
    private String desc;

    /*** 权限是否失效,0 已失效, 1 未失效 */    
    private Integer invalid;

    /*** 创建时间 */    
    private Date dtCreated;

    /*** 创建时间 */    
    private Date dtModified;

}
