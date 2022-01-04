package mapper;

import entity.AnswerOpt;

import java.util.List;
import java.util.Map;


/**
 * @author LZJ
 *对调查结果选项进行管理的mapper
 */
public interface AnswerOptMapper {
	/**
	 *创建答案选项
	 */
	public int create(AnswerOpt pi);

	/**
	 *删除答案选项
	 */
	public int delete(Map<String, Object> paramMap);
	/**
	 *更新答案
	 */
	public int update(Map<String, Object> paramMap);
	public List<AnswerOpt> query(Map<String, Object> paramMap);
	public AnswerOpt detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}