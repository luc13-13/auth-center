package com.lc.auth.center.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 角色权限关系表(RoleRefPermission)实体类
 *
 * @author lucheng
 * @since 2022-04-29 14:33:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_center.role_ref_permission")
public class RoleRefPermissionDO {

    /*** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /*** 角色id */    
    private Long roleId;

    /*** 权限id */    
    private Long permissionId;

    /*** 角色权限是否失效,0 已失效, 1 未失效 */    
    private Integer invalid;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date dtCreated;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date dtModified;

}
