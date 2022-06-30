package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lc.auth.center.bo.UPermissionBO;
import com.lc.auth.center.model.UPermissionDO;
import com.lc.auth.center.bo.RoleRefPermissionBO;

import java.util.List;
import java.util.Set;

/**
 * 操作权限表(UPermission)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-29 14:33:34
 */
@Mapper
public interface UPermissionDao extends BaseMapper<UPermissionDO> {

    List<UPermissionDO> queryAllByLimit();
    
    List<UPermissionDO> queryAll(UPermissionBO bo);

    List<RoleRefPermissionBO> queryRolePermissionList(@Param("roleSet") Set<String> roleSet);
}
