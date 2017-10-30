package com.daniel.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.dao.TUserDao;
import com.daniel.entity.Permission;
import com.daniel.entity.Role;
import com.daniel.entity.User;


public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private TUserDao userDao;
//    @Autowired
//    private TRoleDao roleDao;
//    @Autowired
//    private TPermissionDao permissionDao;

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        //查出是否有此用户
        User hasUser = userDao.findByUsername(token.getUsername());
        if (hasUser != null) {
            return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), getName());
        }
        return null;
    }

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");

        User user = (User) principalCollection.getPrimaryPrincipal();
        
        if (user != null) {
        	List<Role> rlist = user.getRoleList();//获取用户角色
        	List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
        	List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
        	for (Role role : rlist) {
        		roleStrlist.add(role.getRolename());
        		List<Permission> plist = role.getPermissionList();//获取用户权限
        		
        		for (Permission uPermission : plist) {
        			perminsStrlist.add(uPermission.getPermissionname());
        		}
        	}

        	//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(roleStrlist); 
            //用户的权限集合
            info.addStringPermissions(perminsStrlist); 

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
