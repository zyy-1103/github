package com.road.controller;


import com.road.bean.Parse;
import com.road.service.DataDicItemService;
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
@RequiresRoles("ADMIN")
@Controller
@RequestMapping("/xtgl/datadicitem")
public class DatadictionaryitemController {
    @Autowired
    DataDicItemService service;

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public String getData(){
        return service.getData();
    }

    @ResponseBody
    @RequestMapping(value = "touch",method = RequestMethod.POST)
    public String touch(@RequestBody Parse parse){
        return service.touch(parse);
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String del(@RequestBody String s) {
        return del(s);
    }

    @RequestMapping(value = "xx/{id}",method = RequestMethod.GET)
    public String toXx() {
        return "./dicitemxx";
    }

    @ResponseBody
    @RequestMapping(value = "xx/{id}",method = RequestMethod.POST)
    public String getXx(@PathVariable String id) {
        return service.getXx(id);
    }

    @ResponseBody
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(@RequestBody String s) {
        return service.search(s);
    }
}

