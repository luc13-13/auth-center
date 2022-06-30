package com.lc.auth.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.auth.center.model.LoginLogDO;
import com.lc.auth.center.web.request.PageRequest;

import java.util.List;

/**
 * 登录日志(LoginLog)表服务接口
 * @author lucheng
 * @date 2022-04-30 20:23:35
 */
public interface LoginLogService extends IService<LoginLogDO> {

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<LoginLogDO> getLoginLogDOListPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param loginLogDO 实例对象
     * @return 实例对象
     */
    LoginLogDO creatOrUpdate(LoginLogDO loginLogDO);

    /**
     * 修改数据
     *
     * @param loginLogDO 实例对象
     * @return 实例对象
     */
    LoginLogDO updateLoginLogDO(LoginLogDO loginLogDO);

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


}
