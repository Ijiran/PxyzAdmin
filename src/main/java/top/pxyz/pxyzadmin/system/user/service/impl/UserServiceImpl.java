package top.pxyz.pxyzadmin.system.user.service.impl;

import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.sequence.GenerateSequenceUtil;
import top.pxyz.pxyzadmin.system.user.mapper.UserMapper;
import top.pxyz.pxyzadmin.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.user.service.impl
 * @date 2020-06-07 9:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表信息
     * @param username
     * @param email
     * @return
     */
    public List<Map<String, String>> getUserList(String username, String email){
        return userMapper.getUserList(username,email);
    }

    /**
     * 根据id获取用户基本信息
     * @param id
     * @return
     */
    public Map<String,String> getUserById(String id){
        return userMapper.getUserById(id);
    }

    /**
     * 更新用户信息
     * @param id
     * @param username
     * @param email
     * @param remark
     */
    public void updateUser(String id, String username, String email, String remark){
        Map<String,String> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("email", email);
        map.put("remark", remark);
        userMapper.updateUser(map);
    }

    /**
     * 新增用户信息
     * @param username
     * @param email
     * @param remark
     */
    public void addUser(String username, String email, String remark){
        Map<String,String> map = new HashMap<>();
        map.put("id", GenerateSequenceUtil.generateSequenceNo());
        map.put("username", username);
        map.put("email", email);
        map.put("remark", remark);
        userMapper.addUser(map);
    }

    /**
     * 删除用户
     * @param id
     */
    public void delUser(String id){
        String[] ids = id.split(",");
        for (String i : ids){
            if(ValidateHelper.isNotEmptyString(i)){
                userMapper.delUser(i);
            }
        }
    }
}
