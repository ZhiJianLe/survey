package service;

import entity.Question;

import java.util.List;


/**
 * @author LZJ on 2022/1/4 10:02
 */
public interface QuestionService {
    /**
     * 添加用户
     * @param question
     * @return
     */
    public int create(Question question);

    /**
     * 删除用户
     * @return
     */
    public int delete(Integer id);

    /**
     * 更新用户信息
     * @return
     */
    public int update(Question question);

    /**
     * 查询用户
     * @return
     */
    public List<Question> query(Question question);

    /**
     * 获取用户详细信息
     * @return
     */
    public Question detail(Integer id);

    /**
     *
     * @return
     */
    public int count(Question question);

    int deleteBatch(String ids);

}
