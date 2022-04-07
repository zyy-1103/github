package com.road.controller;


import com.road.bean.Parse;
import com.road.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Controller
@RequestMapping("/xtgl/role")
@RequiresRoles("ADMIN")
public class RoleController {

    @Autowired
    RoleService service;

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
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(@RequestBody String s) {
        return service.update(s);
    }

    @ResponseBody
    @GetMapping(value = "/t")
    public String canOrNot() {
        return "1";
    }

    @RequestMapping(value = "xx/{id}",method = RequestMethod.GET)
    public String toXx() {
        return "./rolexx";
    }

    @ResponseBody
    @RequestMapping(value = "xx/{id}",method = RequestMethod.POST)
    public String getXx(@PathVariable String id) {
        return service.getXx(id);
    }

    @ResponseBody
    @RequestMapping(value = "touch",method = RequestMethod.POST)
    public String touch(@RequestBody Parse parse) {
        return service.touch(parse);
    }
}

