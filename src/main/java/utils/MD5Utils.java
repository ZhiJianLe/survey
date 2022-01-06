package utils;

import org.springframework.util.DigestUtils;

/**
 * @author LZJ on 2022/1/5 9:44
 * 加密工具类
 */
public class MD5Utils {
    //盐
    private static final String salt="Survey###$$@@";

    /**
     *加密方法
     */
    public static String getMD5(String string){
        String val=string+salt;
        return DigestUtils.md5DigestAsHex(val.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(getMD5("123456"));
    }
}
