package com.road.controller;


import com.road.bean.Dljcxx;
import com.road.bean.Parse;
import com.road.mapper.DljcxxMapper;
import com.road.mapper.OperatorMapper;
import com.road.service.DljcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Controller
@RequestMapping(value = "/dljcxx")
//@RestController
public class DljcxxController {

    @Autowired
    DljcxxService service;

    @RequestMapping(value = "getData", method = RequestMethod.POST)
    @ResponseBody
    public String getData(@RequestBody String curr, HttpServletRequest request) {
        return service.getData(curr, request);
    }

    @RequestMapping(value = "xx/{id}",method = RequestMethod.GET)
    public String toDljcxxXx(@PathVariable String id) {
        return "./dljcxxxx";
    }

    @ResponseBody
    @RequestMapping(value = "xx/{id}",method = RequestMethod.POST)
    public String getDljcxxXx(@PathVariable String id) {
        return service.getDljcxxXx(id);
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public String del(@RequestBody String strings) {
        return service.del(strings);
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(@RequestBody String s) {
        return service.update(s);
    }

    @ResponseBody
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(@RequestBody String s) {
        return service.search(s);
    }

    @ResponseBody
    @RequestMapping(value = "touch",method = RequestMethod.POST)
    public String touch(@RequestBody Parse dljcxx){
        return service.touch(dljcxx);
    }
}

