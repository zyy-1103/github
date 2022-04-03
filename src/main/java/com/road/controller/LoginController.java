package com.road.controller;

import com.mysql.cj.util.StringUtils;
import com.road.bean.Operator;
import com.road.bean.OperatorModule;
import com.road.mapper.OperatorMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    OperatorMapper mapper;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Operator operator, HttpServletRequest request) {
        System.out.println("login");
        if (StringUtils.isEmptyOrWhitespaceOnly(operator.getDlm()) || StringUtils.isEmptyOrWhitespaceOnly(operator.getMm())) {
            return "请输入账号密码";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(operator.getDlm(), operator.getMm());
        Integer id = mapper.getIdByName(operator.getDlm());
        try {
            subject.login(token);
            subject.getSession().setAttribute("id", id);
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
            return "用户名不存在";
        } catch (AuthenticationException e) {
            System.out.println("账号或密码错误");
            return "账号或密码错误";
        } catch (AuthorizationException e) {
            System.out.println("没有权限");
            return "没有权限";
        }
        return "1";
    }

    @RequiresRoles("COMMON")
    @RequestMapping("index")
    public String index() {
        return "index success";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping("admin")
    public String admin() {
        return "admin success";
    }

    @RequiresPermissions("INFO_QUERY")
    @RequestMapping("del")
    public String del(){
        return "del success";
    }
}
