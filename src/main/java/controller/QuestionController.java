package controller;

import entity.Admin;
import entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;
import utils.MapControl;
import utils.SessionUtils;
import utils.SystemInit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 10:06
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    /**
     *增
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Question question, HttpServletRequest request){
        int code=questionService.create(question);
        //失败的情况下
        if(code<=0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().add("id",code).getMap();
    }
    /**
     *删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int code=questionService.deleteBatch(ids);
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
    public Map<String,Object> update(@RequestBody Question question){
        int code=questionService.update(question);
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
    public Map<String,Object> query(@RequestBody Question question){
        List<Question> list=questionService.query(question);
        Integer count=questionService.count(question);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/detail")
    public String detail(Integer id, ModelMap modelMap){
        Question question=questionService.detail(id);
        modelMap.addAttribute("question",question);
        return "question/update";
    }

    @GetMapping("/list")
    public String list(){
        return "question/list";
    }

    @GetMapping("/question")
    public String question(Integer id, ModelMap modelMap){
        Question question=questionService.detail(id);
        modelMap.addAttribute("question",question);
        return "question/question";
    }
}
