package com.lc.auth.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.auth.center.model.RoleRefPermissionDO;
import com.lc.auth.center.web.request.PageRequest;

import java.util.List;

/**
 * 角色权限关系表(RoleRefPermission)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:33:09
 */
public interface RoleRefPermissionService extends IService<RoleRefPermissionDO> {

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<RoleRefPermissionDO> getRoleRefPermissionDOListPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param roleRefPermissionDO 实例对象
     * @return 实例对象
     */
    RoleRefPermissionDO creatOrUpdate(RoleRefPermissionDO roleRefPermissionDO);

    /**
     * 修改数据
     *
     * @param roleRefPermissionDO 实例对象
     * @return 实例对象
     */
    RoleRefPermissionDO updateRoleRefPermissionDO(RoleRefPermissionDO roleRefPermissionDO);

    /**
     * 物理删除
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


}
