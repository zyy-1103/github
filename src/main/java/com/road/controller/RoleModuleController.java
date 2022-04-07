package com.road.controller;


import com.road.bean.Parse;
import com.road.bean.RoleModule;
import com.road.service.RoleModuleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Controller
@RequestMapping("/xtgl/rolemodule")
@RequiresRoles("ADMIN")
public class RoleModuleController {

    @Autowired
    RoleModuleService service;

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public String getData(){
        return service.getData();
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String del(@RequestBody String s) {
        return service.del(s);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestBody String s) {
        return service.update(s);
    }

    @ResponseBody
    @RequestMapping(value = "touch",method = RequestMethod.POST)
    public String touch(@RequestBody Parse parse) {
        return service.touch(parse);
    }
}

