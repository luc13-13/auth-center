package com.lc.auth.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.auth.center.mapper.LoginLogDao;
import com.lc.auth.center.service.LoginLogService;
import com.lc.auth.center.model.LoginLogDO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lc.auth.center.web.request.PageRequest;

import java.util.List;
/**
 * 登录日志(LoginLog)表服务接口
 * @author lucheng
 * @date 2022-04-30 20:23:36
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao,LoginLogDO> implements LoginLogService {
    
    @Autowired
    private LoginLogDao loginLogDao;
    

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    public List<LoginLogDO> getLoginLogDOListPage(PageRequest pageRequest){
        return null;
    }

    /**
     * 新增数据
     *
     * @param loginLogDO 实例对象
     * @return 实例对象
     */
    public LoginLogDO creatOrUpdate(LoginLogDO loginLogDO) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param loginLogDO 实例对象
     * @return 实例对象
     */
    public LoginLogDO updateLoginLogDO(LoginLogDO loginLogDO) {
        return null;
    }

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id) {
        return true;
    }
}
