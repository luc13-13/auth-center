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
 * 用户权限关系表(UserRefRole)实体类
 *
 * @author lucheng
 * @since 2022-04-29 14:34:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_ref_role")
public class UserRefRoleDO {


    /*** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
    @TableField(fill = FieldFill.INSERT)
    private Date dtCreated;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date dtModified;

}
