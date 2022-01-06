package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LZJ on 2022/1/5 15:43
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
