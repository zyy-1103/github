package com.road.controller;


import com.road.service.XhdlkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/dljcxx/xx/{ssdl}/")
@RequiresPermissions("LOGIN")
public class XhdlkController {
    @Autowired
    XhdlkService service;

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public String getXhdlk(@PathVariable String ssdl,@RequestBody String tableName) {
        return service.getData(ssdl,tableName);
    }

    @RequiresPermissions("INFO_DEL")
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

    @RequestMapping(value = "xhdlk")
    public String toXhdlk() {
    return "../id/xhdlk";
    }

    @RequestMapping(value = "tqlb")
    public String toTqlb() {
    return "../id/tqlb";
    }

    @RequestMapping(value = "ssglbsd")
    public String toBsd() {
    return "../id/ssglbsd";
    }

    @RequestMapping(value = "rxhdx")
    public String toRxhdx() {
    return "../id/rxhdx";
    }

    @RequestMapping(value = "lsydbz")
    public String lsydbz() {
    return "../id/lsydbz";
    }

    @RequestMapping(value = "lnbw")
    public String lnbw() {
    return "../id/lnbw";
    }

    @RequestMapping(value = "jzwcrk")
    public String jzwcrk() {
    return "../id/jzwcrk";
    }

    @RequestMapping(value = "jsz")
    public String jsz() {
    return "../id/jsz";
    }

    @RequestMapping(value = "jshcd")
    public String jshcd() {
    return "../id/jshcd";
    }

    @RequestMapping(value = "glss")
    public String glss() {
        return "../id/glss";
    }

    @RequestMapping(value = "gjzd")
    public String gjzd() {
        return "../id/gjzd";
    }

    @RequestMapping(value = "bzp")
    public String bzp() {
        return "../id/bzp";
    }

    @RequestMapping(value = "/o/{tableName}/{id}",method = RequestMethod.GET)
    public String toAny(@PathVariable String tableName){
        return "../../../id/xx/"+tableName+"xx";
    }

    @ResponseBody
    @RequestMapping(value = "/o/{tableName}/{id}",method = RequestMethod.POST)
    public String getXhdlkXx(@PathVariable String id,@PathVariable String tableName){
        return service.getXhdlkXx(id,tableName);
    }

}

