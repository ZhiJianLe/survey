package service.impl;

import entity.Admin;
import mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;
import utils.BeanMapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LZJ on 2022/1/4 10:04
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    /**
     * 添加用户
     *
     * @param admin
     * @return
     */
    @Override
    public int create(Admin admin) {
        return adminMapper.create(admin);
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
        adminMapper.delete(map);
        return adminMapper.delete(map);
    }

    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
    @Override
    public int update(Admin admin) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",admin.getId());
            map.put("updateAccount",admin.getAccount());
            map.put("updateName",admin.getName());
            map.put("updatePassword",admin.getPassword());
            map.put("updatePhone",admin.getPhone());
            map.put("updateRemark",admin.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMapForUpdate(admin);
        map.put("id",admin.getId());
        return adminMapper.update(map);
    }

    /**
     * 查询用户
     *
     * @param
     * @return
     */
    @Override
    public List<Admin> query(Admin admin) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",admin.getId());
            map.put("updateAccount",admin.getAccount());
            map.put("updateName",admin.getName());
            map.put("updatePassword",admin.getPassword());
            map.put("updatePhone",admin.getPhone());
            map.put("updateRemark",admin.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMap(admin);
        return adminMapper.query(map);
    }

    /**
     * 获取用户详细信息
     *
     * @param
     * @return
     */
    @Override
    public Admin detail(Integer id) {
        Map<String,Object>map=new HashMap<>();
        map.put("id",id);
        return adminMapper.detail(map);
    }

    /**
     * @param
     * @return
     */
    @Override
    public int count(Admin admin) {
        /*
            Map<String,Object>map=new HashMap<>();
            map.put("id",admin.getId());
            map.put("updateAccount",admin.getAccount());
            map.put("updateName",admin.getName());
            map.put("updatePassword",admin.getPassword());
            map.put("updatePhone",admin.getPhone());
            map.put("updateRemark",admin.getRemark());
        */
        Map<String,Object>map=BeanMapUtils.beanToMap(admin);
        return adminMapper.count(map);
    }
}
