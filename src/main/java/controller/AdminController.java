package controller;

import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AdminService;

/**
 * @author LZJ on 2022/1/4 10:06
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/create")
    public void add(){
        Admin admin=new Admin();
        admin.setAccount("system");
        admin.setName("系统管理员");
        admin.setPassword("123456");
        System.out.println("-----------");
        adminService.add(admin);
    }
}
