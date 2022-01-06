package controller;

import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import utils.MapControl;

import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 10:06
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/create")
    public String v_create(){
        return "admin/add";
    }

    /**
     *增
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Admin admin){
        int code=adminService.create(admin);
        //失败的情况下
        if(code<=0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    /**
     *删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int code=adminService.deleteBatch(ids);
        //失败的情况下
        if(code<=0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    /**
     *改
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Admin admin){
        int code=adminService.update(admin);
        //失败的情况下
        if(code<=0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    /**
     *查
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Admin admin){
        List<Admin> list=adminService.query(admin);
        Integer count=adminService.count(admin);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/detail")
    public String detail(Integer id, ModelMap modelMap){
        Admin admin=adminService.detail(id);
        modelMap.addAttribute("admin",admin);
        return "admin/update";
    }

    @GetMapping("/list")
    public String list(){
        return "admin/list";
    }

}
