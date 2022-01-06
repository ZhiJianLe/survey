package utils;

import entity.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZJ on 2022/1/6 9:14
 */
public class SessionUtils {
    private static final String key="admin";

    public static void setAdmin(HttpServletRequest request, Admin admin){
        request.getSession().setAttribute(key,admin);
    }
    public static Admin getAdmin(HttpServletRequest request){
        if(request.getSession().getAttribute(key) != null){
            return (Admin) request.getSession().getAttribute(key);
        }else{
            return null;
        }
    }
}
