package com.lc.auth.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.auth.center.web.request.PageRequest;
import com.lc.auth.center.model.UserRefRoleDO;

import java.util.List;

/**
 * 用户权限关系表(UserRefRole)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:34:38
 */
public interface UserRefRoleService extends IService<UserRefRoleDO> {

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<UserRefRoleDO> getUserRefRoleDOListPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userRefRoleDO 实例对象
     * @return 实例对象
     */
    UserRefRoleDO creatOrUpdate(UserRefRoleDO userRefRoleDO);

    /**
     * 修改数据
     *
     * @param userRefRoleDO 实例对象
     * @return 实例对象
     */
    UserRefRoleDO updateUserRefRoleDO(UserRefRoleDO userRefRoleDO);

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


}
