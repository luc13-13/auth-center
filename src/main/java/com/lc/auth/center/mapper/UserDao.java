package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lc.auth.center.bo.UserBO;
import com.lc.auth.center.model.UserDO;
import com.lc.auth.center.model.ex.UserDOex;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 顾客账号信息表(User)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-29 14:33:56
 */
@Mapper
public interface UserDao extends BaseMapper<UserDO> {

    List<UserDO> queryAllByLimit();
    
    List<UserDO> queryAll(UserBO bo);

    List<UserDOex> queryRolePermission(@Param("id") Long id);
}
