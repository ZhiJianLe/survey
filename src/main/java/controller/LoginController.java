package controller;

import com.google.common.base.Strings;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import utils.MD5Utils;
import utils.MapControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 21:31
 * 登录页面
 */
@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String v_login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String,Object> map, HttpServletRequest request){
        System.out.println("---登录---");
        String account = map.get("account")+"";
        String password = map.get("password")+"";
        if(Strings.isNullOrEmpty(account)||Strings.isNullOrEmpty(password)){
            return MapControl.getInstance().error("用户名或密码不能为空").getMap();
        }
        String str=MD5Utils.getMD5(password);
        Admin admin = adminService.login(account,str);
        if(admin != null){
            HttpSession session=request.getSession();
            session.setAttribute("admin",admin);
            return MapControl.getInstance().success().getMap();
        }else{
            return MapControl.getInstance().error("用户名或密码错误").getMap();
        }
    }



}
