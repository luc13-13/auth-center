package com.lc.auth.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.auth.center.mapper.RoleRefPermissionDao;
import com.lc.auth.center.model.RoleRefPermissionDO;
import com.lc.auth.center.service.RoleRefPermissionService;
import com.lc.auth.center.web.request.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
/**
 * 角色权限关系表(RoleRefPermission)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:33:10
 */
@Service
public class RoleRefPermissionServiceImpl extends ServiceImpl<RoleRefPermissionDao, RoleRefPermissionDO> implements RoleRefPermissionService {
    
    @Autowired
    private RoleRefPermissionDao roleRefPermissionDao;
    

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    public List<RoleRefPermissionDO> getRoleRefPermissionDOListPage(PageRequest pageRequest){
        return null;
    }

    /**
     * 新增数据
     *
     * @param roleRefPermissionDO 实例对象
     * @return 实例对象
     */
    public RoleRefPermissionDO creatOrUpdate(RoleRefPermissionDO roleRefPermissionDO) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param roleRefPermissionDO 实例对象
     * @return 实例对象
     */
    public RoleRefPermissionDO updateRoleRefPermissionDO(RoleRefPermissionDO roleRefPermissionDO) {
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
