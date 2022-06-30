package com.lc.auth.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.auth.center.service.RoleService;
import com.lc.auth.center.service.UserService;
import com.lc.auth.center.web.request.PageRequest;
import com.lc.auth.center.bo.UserBO;
import com.lc.auth.center.model.UserDO;
import com.lc.auth.center.mapper.UserDao;
import com.lc.auth.center.model.ex.UserDOex;
import com.lc.auth.center.service.UPermissionService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 顾客账号信息表(User)表服务接口
 * @author lucheng
 * @date 2022-04-29 14:33:57
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao,UserDO> implements UserService {
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UPermissionService uPermissionService;
    

    /**
     * 查询多条数据
     *
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    public List<UserDO> getUserDOListPage(PageRequest pageRequest){
        return null;
    }

    /**
     * 新增数据
     *
     * @param userDO 实例对象
     * @return 实例对象
     */
    public UserDO creatOrUpdate(UserDO userDO) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param userDO 实例对象
     * @return 实例对象
     */
    public UserDO updateUserDO(UserDO userDO) {
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

    @Override
    public void getAuthorizationInfo(UserBO userBO) {
//        // 获取用户 角色集合 Set<String> roleSet
////        List<UserDOex> userDOexList = userDao.queryRolePermission(id);
//        List<RoleDO> roleDOList = roleService.getUserRoleList(userBO.getId());
//        Set<String> roleSet = roleDOList.stream().map(r->r.getRoleDesc()).collect(Collectors.toSet());
//        userBO.setRoleSet(roleSet);
//
//        //获取 roleSet 对应的权限集合
//        List<RoleRefPermissionBO> roleRefPermissionBOS = uPermissionService.getRolePermissionList(roleSet);
//        Set<String> permissionSet = roleRefPermissionBOS.stream().map(RoleRefPermissionBO::getPermissionDesc).collect(Collectors.toSet());
//        userBO.setUPermissionSet(permissionSet);
        List<UserDOex> userDOexList = userDao.queryRolePermission(userBO.getId());
        Set<String> roleSet = userDOexList.stream().map(UserDOex::getRoleDesc).collect(Collectors.toSet());
        log.info("com.lc.blog.shiro.service.impl.UserServiceImpl#94 查询用户角色列表:{}",roleSet);
        Set<String> permissionSet = userDOexList.stream().map(UserDOex::getPermissionDesc).collect(Collectors.toSet());
        log.info("com.lc.blog.shiro.service.impl.UserServiceImpl#96 查询用户权限列表:{}",permissionSet);
        userBO.setRoleSet(roleSet);
        userBO.setUPermissionSet(permissionSet);
    }
}
