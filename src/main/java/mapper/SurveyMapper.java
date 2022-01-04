package mapper;

import entity.Survey;

import java.util.List;
import java.util.Map;


/**
 * @author LZJ
 * 对问卷表进行管理的mapper
 */
public interface SurveyMapper {
	public int create(Survey pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<Survey> query(Map<String, Object> paramMap);
	public Survey detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
	public List<Integer> queryByState(String state);
	public List<Integer> queryByStateForExec(String state);
}