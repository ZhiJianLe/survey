package service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import entity.Question;
import entity.QuestionOpt;
import mapper.QuestionMapper;
import mapper.QuestionOptMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.QuestionService;
import utils.BeanMapUtils;
import utils.MapParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 10:04
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionOptMapper questionOptMapper;

    /**
     * 添加用户
     *
     * @param question
     * @return
     */
    @Override
    public int create(Question question) {
        int flag;
        //如果传递的对象中含有id属性，则说明需要进行的是修改操作：此处的修改操作采用的是先删除后添加
        if(question.getId()!=null){
            flag=this.update(question);
            questionOptMapper.delete(MapParameter.getInstance().add("questionId",question.getId()).getMap());
        }else{
            flag=questionMapper.create(question);
        }

        //如果插入成功
        int i=0;
        if(flag>0){
            //获取答案中的选项
            List<QuestionOpt> options=question.getOptions();
            //依次进行插入
            for (QuestionOpt opt:options) {
                //给每个答案赋值问卷ID和问题ID
                opt.setSurveyId(question.getSurveyId());
                opt.setQuestionId(question.getId());
                opt.setOrderby(++i);
                questionOptMapper.create(opt);
            }
        }
        return question.getId();
    }

    /**
     * 删除用户
     *
     * @param
     * @return
     */
    @Override
    public int delete(Integer id) {
        Map<String,Object>map=new HashMap<>();
        map.put("id",id);
        questionMapper.delete(map);
        return questionMapper.delete(map);
    }

    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
    @Override
    public int update(Question question) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",question.getId());
            map.put("updateAccount",question.getAccount());
            map.put("updateName",question.getName());
            map.put("updatePassword",question.getPassword());
            map.put("updatePhone",question.getPhone());
            map.put("updateRemark",question.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMapForUpdate(question);
        map.put("id",question.getId());
        return questionMapper.update(map);
    }

    /**
     * 查询
     *
     * @param
     * @return
     */
    @Override
    public List<Question> query(Question question) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",question.getId());
            map.put("updateAccount",question.getAccount());
            map.put("updateName",question.getName());
            map.put("updatePassword",question.getPassword());
            map.put("updatePhone",question.getPhone());
            map.put("updateRemark",question.getRemark());
        */
        //查询问题
        Map<String,Object>map=MapParameter.getInstance().put(BeanMapUtils.beanToMap(question)).getMap();
        List<Question> questionList=questionMapper.query(map);

        //查询选项
        List<QuestionOpt> optList=questionOptMapper.query(MapParameter.getInstance().add("survey",question.getSurveyId()).getMap());

        //将问题和选项进行组合
        for (Question question1:questionList) {
            //创建一个数组用于组合问题和选项
            List<QuestionOpt> options=new ArrayList<>();
            for(QuestionOpt questionOpt:optList){
                if(question1.getId().equals(questionOpt.getQuestionId())){
                    options.add(questionOpt);
                }
            }
            question1.setOptions(options);
        }

        return questionList;
    }

    /**
     * 获取用户详细信息
     *
     * @param
     * @return
     */
    @Override
    public Question detail(Integer id) {
        Map<String,Object>map=new HashMap<>();
        map.put("id",id);
        return questionMapper.detail(map);
    }

    /**
     * @param
     * @return
     */
    @Override
    public int count(Question question) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",question.getId());
            map.put("updateAccount",question.getAccount());
            map.put("updateName",question.getName());
            map.put("updatePassword",question.getPassword());
            map.put("updatePhone",question.getPhone());
            map.put("updateRemark",question.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMap(question);
        return questionMapper.count(map);
    }

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    @Override
    public int deleteBatch(String ids) {
        int flag=0;
        //将字符串转化为数组
        List<String> list= Splitter.on(",").splitToList(ids);
        for (String str:list) {
            questionMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
            questionOptMapper.delete(MapParameter.getInstance().add("questionId",Integer.parseInt(ids)).getMap());
            flag++;
        }
        return flag;
    }
}
