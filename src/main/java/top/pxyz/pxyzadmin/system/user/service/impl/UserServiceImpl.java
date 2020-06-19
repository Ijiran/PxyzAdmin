package top.pxyz.pxyzadmin.system.user.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.encrypt.BCryptUtils;
import top.pxyz.pxyzadmin.core.util.encrypt.MD5Utils;
import top.pxyz.pxyzadmin.core.util.sequence.GenerateSequenceUtil;
import top.pxyz.pxyzadmin.system.user.bean.User;
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

    //123456
    private static String defaultPwd = "7C4A8D09CA3762AF61E59520943DC26494F8941B";

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
        map.put("password", BCryptUtils.encode("123456"));
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

    /**
     * 保存用户绑定角色信息
     * @param userId
     * @param roleId
     */
    public void saveRoleBind(String userId, String roleId){
        userMapper.delRoleBind(userId);//清空角色菜单绑定
        String[] roles = roleId.split(",");
        for (String role : roles){
            userMapper.addRoleBind(role, userId);
        }
    }

    /**
     * 根据名称查询User用户信息
     * @param username
     * @return
     */
    public User findUserByName(String username){
        return userMapper.findUserByName(username);
    }

    /**
     * 密码重置
     * @param id
     */
    public void resetPwd(String id){
        userMapper.updatePwd(id,defaultPwd);
    }

    /**
     * 检查旧密码是否输入正确
     * @param id
     * @param oldPwd
     * @return
     */
    public boolean checkOldPwd(String id, String oldPwd){
        boolean flag = false;
        String encryptPwd = MD5Utils.encrypt(oldPwd);
        Map<String,String> userInfo = userMapper.getUserById(id);
        if(encryptPwd.equals(userInfo.get("password"))){
            flag = true;
        }
        return flag;
    }

    /**
     * 修改密码
     * @param id
     * @param newPwd
     */
    public void updatePwd(String id, String newPwd){
        userMapper.updatePwd(id,MD5Utils.encrypt(newPwd));
    }

}
