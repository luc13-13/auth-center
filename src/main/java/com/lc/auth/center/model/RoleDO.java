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
 * 权限表(Role)实体类
 *
 * @author lucheng
 * @since 2022-04-29 14:30:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
public class RoleDO{

    /*** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /*** 权限描述 */
    private String roleDesc;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date dtCreated;

    /*** 创建时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date dtModified;

}
