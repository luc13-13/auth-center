package com.lc.auth.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.auth.center.mapper.RoleDao;
import com.lc.auth.center.model.RoleDO;
import com.lc.auth.center.service.RoleService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lc.auth.center.web.request.PageRequest;
import java.util.List;
/**
 * 权限表(Role)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:30:23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {
    
    @Autowired
    private RoleDao roleDao;
    

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    public List<RoleDO> getRoleDOListPage(PageRequest pageRequest){
        return null;
    }

    public List<RoleDO> getUserRoleList(Long userId){
        return roleDao.queryUserRoleSet(userId);
    }

    /**
     * 新增数据
     *
     * @param roleDO 实例对象
     * @return 实例对象
     */
    public RoleDO creatOrUpdate(RoleDO roleDO) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param roleDO 实例对象
     * @return 实例对象
     */
    public RoleDO updateRoleDO(RoleDO roleDO) {
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
