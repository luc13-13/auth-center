package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lc.auth.center.model.UserRefRoleDO;
import com.lc.auth.center.bo.UserRefRoleBO;
import java.util.List;

/**
 * 用户权限关系表(UserRefRole)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-29 14:34:38
 */
@Mapper
public interface UserRefRoleDao extends BaseMapper<UserRefRoleDO> {

    List<UserRefRoleDO> queryAllByLimit();
    
    List<UserRefRoleDO> queryAll(UserRefRoleBO bo);
}
