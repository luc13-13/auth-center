package com.lc.auth.center.config.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.auth.center.bo.UserBO;
import com.lc.auth.center.jwt.AccountProfile;
import com.lc.auth.center.jwt.JwtToken;
import com.lc.auth.center.model.UserDO;
import com.lc.auth.center.service.UserService;
import com.lc.auth.center.jwt.JwtUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户身份认证的核心realm，将其注入 {@link DefaultWebSecurityManager}, 实现自定义的认证逻辑
 *
 * @author: lucheng
 * @data: 2022/4/29 16:16
 * @version: 1.0
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;


    /**
     * 校验是否有权限
     * * 授权用户权限
     * * 授权的方法是在碰到<shiro:hasPermission name=''></shiro:hasPermission>标签的时候调用的
     * * 它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,如果有,里面的内容显示
     * * 如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
     * *
     * * shiro的权限授权是通过继承AuthorizingRealm抽象类，重载doGetAuthorizationInfo();
     * * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
     * * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
     * *
     * * 在这个方法中主要是使用类：SimpleAuthorizationInfo 进行角色的添加和权限的添加。
     * * authorizationInfo.addRole(role.getRole()); authorizationInfo.addStringPermission(p.getPermission());
     * *
     * * 当然也可以添加set集合：roles是从数据库查询的当前用户的角色，stringPermissions是从数据库查询的当前用户对应的权限
     * * authorizationInfo.setRoles(roles); authorizationInfo.setStringPermissions(stringPermissions);
     * *
     * * 就是说如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "perms[权限添加]");
     * * 就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问
     * *
     * * 如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "roles[100002]，perms[权限添加]");
     * * 就说明访问/add这个链接必须要有 "权限添加" 这个权限和具有 "100002" 这个角色才可以访问
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("com.lc.blog.shiro.config.realm#doGetAuthorizationInfo 执行 Shiro 权限获取 ---------------------");
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal != null) {
            UserDO userLogin = BeanUtil.copyProperties(principal,UserDO.class);
            UserBO userBO = UserBO.builder()
                    .id(userLogin.getId()).build();
            userService.getAuthorizationInfo(userBO);
            log.info("");
//            角色
//            Set roles = roleService.findRoleNameByUserId(userLogin.getId());
            authorizationInfo.addRoles(userBO.getRoleSet());
//            权限
//            Set permissions = userService.findPermissionsByUserId(userLogin.getId());
            authorizationInfo.addStringPermissions(userBO.getUPermissionSet());
        }
        log.info("com.lc.blog.shiro.config.realm#doGetAuthorizationInfo 完成权限获取,{}", authorizationInfo.getStringPermissions().toString());
        return authorizationInfo;
    }


    /**
     * AuthenticationToken 已被 {@link JwtToken}继承并重写方法，对传来的token进行校验
     *
     * @param authenticationToken
     * @return 认证成功则返回用户认证信息 {@link AuthenticationInfo} 给 shiro
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwt = (JwtToken) authenticationToken;
        log.info("|进入认证方法==========获取jwtToken:{}", jwt);
        log.info("principle:{}", jwt.getPrincipal());
        String loginName = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        log.info("获取login_name: {}", loginName);
        UserDO userDO = Optional.ofNullable(
                userService
                        .getBaseMapper()
                        .selectOne(new QueryWrapper<UserDO>().eq("login_name", loginName)))
                .filter(o -> o.getInvalid() != 0)
                .orElseThrow(() -> new UnknownAccountException("账户过期或不存在！"));
        AccountProfile accountProfile = AccountProfile.builder().build();
        BeanUtils.copyProperties(userDO, accountProfile);
        log.info("========认证成功|-----accountProfile:{}", accountProfile);
        return new SimpleAuthenticationInfo(accountProfile, jwt.getCredentials(), getName());
    }

    /**
     * 只返回{@link JwtToken},
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
