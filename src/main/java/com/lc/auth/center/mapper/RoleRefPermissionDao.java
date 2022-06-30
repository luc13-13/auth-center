package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lc.auth.center.model.RoleRefPermissionDO;
import com.lc.auth.center.bo.RoleRefPermissionBO;
import java.util.List;

/**
 * 角色权限关系表(RoleRefPermission)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-29 14:33:09
 */
@Mapper
public interface RoleRefPermissionDao extends BaseMapper<RoleRefPermissionDO> {

    List<RoleRefPermissionDO> queryAllByLimit();
    
    List<RoleRefPermissionDO> queryAll(RoleRefPermissionBO bo);
}
