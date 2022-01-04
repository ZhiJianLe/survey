package mapper;

import entity.AnswerTxt;

import java.util.List;
import java.util.Map;


/**
 * @author LZJ
 * 对调查结果表进行管理的mapper(主要是对单行或者多行文本的结果管理)
 */
public interface AnswerTxtMapper {
	public int create(AnswerTxt pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<AnswerTxt> query(Map<String, Object> paramMap);
	public AnswerTxt detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}