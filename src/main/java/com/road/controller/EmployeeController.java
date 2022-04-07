package com.road.controller;


import com.road.service.EmployeeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Controller
@RequiresRoles("ADMIN")
@RequestMapping("/xtgl/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public String getData(@RequestBody String s) {
        return service.getData(s);
    }

    @ResponseBody
    @RequestMapping(value = "getPages",method = RequestMethod.POST)
    public String getPages() {
        return service.getPages();
    }

    @RequestMapping(value = "xx/{id}",method = RequestMethod.GET)
    public String toXx(){
        return "./employeexx";
    }

    @RequestMapping(value = "xx/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String getXx(@PathVariable String id){
        return service.getXx(id);
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(@RequestBody String s) {
        return service.update(s);
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String del(@RequestBody String s) {
        return service.del(s);
    }

    @ResponseBody
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(@RequestBody String s) {
        return service.search(s);
    }
}

