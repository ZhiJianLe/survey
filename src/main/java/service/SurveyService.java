package service;

import entity.AnswerOpt;
import entity.AnswerTxt;
import entity.Survey;

import java.util.List;


/**
 * @author LZJ on 2022/1/4 10:02
 */
public interface SurveyService {
    /**
     * 添加用户
     * @param survey
     * @return
     */
    public int create(Survey survey);

    /**
     * 删除用户
     * @return
     */
    public int delete(Integer id);

    /**
     * 更新用户信息
     * @return
     */
    public int update(Survey survey);

    /**
     * 查询用户
     * @return
     */
    public List<Survey> query(Survey survey);

    /**
     * 获取用户详细信息
     * @return
     */
    public Survey detail(Integer id);

    /**
     *
     * @return
     */
    public int count(Survey survey);

    int deleteBatch(String ids);

    /**
     *更新状态
     */
    void updateState();

    List<Survey> queryAll(Survey param);

    Integer submit(List<AnswerOpt> optList, List<AnswerTxt> txtList);

    List<AnswerOpt> queryAnswerOpt(AnswerOpt answerOpt);
}
