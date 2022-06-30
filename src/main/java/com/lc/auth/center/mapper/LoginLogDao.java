package com.lc.auth.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lc.auth.center.model.LoginLogDO;

/**
 * 登录日志(LoginLog)表数据库访问层
 *
 * @author lucheng
 * @date 2022-04-30 20:38:57
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLogDO> {

}
