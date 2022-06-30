package com.lc.auth.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.auth.center.model.RoleDO;
import com.lc.auth.center.web.request.PageRequest;

import java.util.List;

/**
 * 权限表(Role)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:30:13
 */
public interface RoleService extends IService<RoleDO> {

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<RoleDO> getRoleDOListPage(PageRequest pageRequest);

    /**
     * 根据user.id查询用户拥有的角色集合
     * @param userId
     * @return
     */
    List<RoleDO> getUserRoleList(Long userId);

    /**
     * 新增数据
     *
     * @param roleDO 实例对象
     * @return 实例对象
     */
    RoleDO creatOrUpdate(RoleDO roleDO);

    /**
     * 修改数据
     *
     * @param roleDO 实例对象
     * @return 实例对象
     */
    RoleDO updateRoleDO(RoleDO roleDO);

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


}
