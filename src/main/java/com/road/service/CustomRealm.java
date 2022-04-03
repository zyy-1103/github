package com.road.service;

import com.road.bean.Jfodule;
import com.road.bean.Operator;
import com.road.bean.Role;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    OperatorService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        Operator operator = service.getOperatorByName(name);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : operator.getRoles()) {
            info.addRole(role.getJsmc());
            for (Jfodule jfodule : role.getJfodules()) {
                info.addStringPermission(jfodule.getMkmc());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal().toString())) {
            return null;
        }
        String name=authenticationToken.getPrincipal().toString();
        Operator operator = service.getOperatorByName(name);
        if(operator==null){
            return null;
        }else {
            return new SimpleAuthenticationInfo(name, operator.getMm(), getName());
        }

    }
}
