package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lc.auth.center.model.RoleDO;
import com.lc.auth.center.bo.RoleBO;
import java.util.List;

/**
 * 权限表(Role)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-29 14:30:20
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleDO> {

    List<RoleDO> queryAllByLimit();
    
    List<RoleDO> queryAll(RoleBO bo);

    /**
     * 根据user.id查询用户拥有的角色集合
     * @param userId
     * @return
     */
    List<RoleDO> queryUserRoleSet(@Param("id") Long userId);
}
