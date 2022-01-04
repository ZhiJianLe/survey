package mapper;

import entity.Admin;

import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 9:57
 * 对用户进行管理的mapper
 */
public interface AdminMapper {
    /**
     * 添加用户
     * @param admin
     * @return
     */
    public int create(Admin admin);

    /**
     * 删除用户
     * @return
     */
    public int delete(Map<String ,Object>paramMap);

    /**
     * 更新用户信息
     * @return
     */
    public int update(Map<String ,Object>paramMap);

    /**
     * 查询用户
     * @return
     */
    public List<Admin> query(Map<String ,Object>paramMap);

    /**
     * 获取用户详细信息
     * @return
     */
    public Admin detail(Map<String, Object> paramMap);

    /**
     *
     * @return
     */
    public int count(Map<String, Object> paramMap);

}
