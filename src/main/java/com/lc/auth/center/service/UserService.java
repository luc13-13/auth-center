package com.lc.auth.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.auth.center.web.request.PageRequest;
import com.lc.auth.center.bo.UserBO;
import com.lc.auth.center.model.UserDO;

import java.util.List;

/**
 * 顾客账号信息表(User)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:33:56
 */
public interface UserService extends IService<UserDO> {

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<UserDO> getUserDOListPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userDO 实例对象
     * @return 实例对象
     */
    UserDO creatOrUpdate(UserDO userDO);

    /**
     * 修改数据
     *
     * @param userDO 实例对象
     * @return 实例对象
     */
    UserDO updateUserDO(UserDO userDO);

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询用户角色、权限，封装进userBO，在shiro中封装进AuthorizationInfo
     * @param userBO
     * @return
     */
    void getAuthorizationInfo(UserBO userBO);
}
