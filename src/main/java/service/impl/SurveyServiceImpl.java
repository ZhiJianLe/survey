package service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import entity.AnswerOpt;
import entity.AnswerTxt;
import entity.Survey;
import mapper.AnswerOptMapper;
import mapper.AnswerTxtMapper;
import mapper.SurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SurveyService;
import utils.BeanMapUtils;
import utils.MapParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 10:04
 */
@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private AnswerOptMapper answerOptMapper;
    @Autowired
    private AnswerTxtMapper answerTxtMapper;
    /**
     * 添加用户
     *
     * @param survey
     * @return
     */
    @Override
    public int create(Survey survey) {
        return surveyMapper.create(survey);
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
        surveyMapper.delete(map);
        return surveyMapper.delete(map);
    }

    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
    @Override
    public int update(Survey survey) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",survey.getId());
            map.put("updateAccount",survey.getAccount());
            map.put("updateName",survey.getName());
            map.put("updatePassword",survey.getPassword());
            map.put("updatePhone",survey.getPhone());
            map.put("updateRemark",survey.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMapForUpdate(survey);
        map.put("id",survey.getId());
        return surveyMapper.update(map);
    }

    /**
     * 查询用户
     *
     * @param
     * @return
     */
    @Override
    public List<Survey> query(Survey survey) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",survey.getId());
            map.put("updateAccount",survey.getAccount());
            map.put("updateName",survey.getName());
            map.put("updatePassword",survey.getPassword());
            map.put("updatePhone",survey.getPhone());
            map.put("updateRemark",survey.getRemark());
        */

        //分页查询
        PageHelper.startPage(survey.getPage(),survey.getLimit());
        Map<String,Object>map=MapParameter.getInstance().put(BeanMapUtils.beanToMap(survey)).getMap();
        return surveyMapper.query(map);
    }

    /**
     * 获取用户详细信息
     *
     * @param
     * @return
     */
    @Override
    public Survey detail(Integer id) {
        Map<String,Object>map=new HashMap<>();
        map.put("id",id);
        return surveyMapper.detail(map);
    }

    /**
     * @param
     * @return
     */
    @Override
    public int count(Survey survey) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",survey.getId());
            map.put("updateAccount",survey.getAccount());
            map.put("updateName",survey.getName());
            map.put("updatePassword",survey.getPassword());
            map.put("updatePhone",survey.getPhone());
            map.put("updateRemark",survey.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMap(survey);
        return surveyMapper.count(map);
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
            surveyMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
            flag++;
        }
        return flag;
    }
    /**
     *更新状态
     */
    @Override
    public void updateState() {
        List<Integer> list=surveyMapper.queryByState(Survey.state_create);
        //将创建状态转变为执行中
        for (Integer id:list) {
            surveyMapper.update(MapParameter.getInstance().add("updateState",Survey.state_exec).add("id",id).getMap());
        }
        List<Integer> list1=surveyMapper.queryByStateForExec(Survey.state_exec);
        //将执行中转变为结束
        for (Integer id:list1) {
            surveyMapper.update(MapParameter.getInstance().add("updateState",Survey.state_over).add("id",id).getMap());
        }
    }

    @Override
    public List<Survey> queryAll(Survey survey){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(survey)).getMap();
        return surveyMapper.query(map);
    }

    @Override
    public Integer submit(List<AnswerOpt> opts,List<AnswerTxt> txts){
        int flag = 0;
        for (AnswerOpt opt : opts) {
            flag = answerOptMapper.create(opt);
        }
        for (AnswerTxt txt : txts) {
            flag = answerTxtMapper.create(txt);
        }
        return flag;
    }

    @Override
    public List<AnswerOpt> queryAnswerOpt(AnswerOpt answerOpt){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(answerOpt)).getMap();
        return answerOptMapper.query(map);
    }

}
