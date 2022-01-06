package service;

import entity.Admin;

import java.util.List;


/**
 * @author LZJ on 2022/1/4 10:02
 */
public interface AdminService {
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
    public int delete(Integer id);

    /**
     * 更新用户信息
     * @return
     */
    public int update(Admin admin);

    /**
     * 查询用户
     * @return
     */
    public List<Admin> query(Admin admin);

    /**
     * 获取用户详细信息
     * @return
     */
    public Admin detail(Integer id);

    /**
     *
     * @return
     */
    public int count(Admin admin);

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    public Admin login(String account,String password);

    int deleteBatch(String ids);
}
