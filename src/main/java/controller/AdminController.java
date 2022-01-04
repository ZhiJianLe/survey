package controller;

import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AdminService;

import java.util.List;

/**
 * @author LZJ on 2022/1/4 10:06
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     *增
     */
    @PostMapping("/create")
    @ResponseBody
    public String create(Admin admin){
        int code=adminService.create(admin);
        //失败的情况下
        if(code<=0) {
            return "error";
        }
        return "success";
    }
    /**
     *删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        int code=adminService.delete(id);
        //失败的情况下
        if(code<=0) {
            return "error";
        }
        return "success";
    }
    /**
     *改
     */
    @PostMapping("/update")
    @ResponseBody
    public String update(Admin admin){
        int code=adminService.update(admin);
        System.out.println("------"+code+"--------");
        //失败的情况下
        if(code<=0) {
            return "error";
        }
        return "success";
    }
    /**
     *查
     */
    @PostMapping("/query")
    @ResponseBody
    public List<Admin> query(Admin admin){
        return adminService.query(admin);
    }
    @PostMapping("/detail")
    @ResponseBody
    public Admin detail(Integer id){
        return adminService.detail(id);
    }
}
