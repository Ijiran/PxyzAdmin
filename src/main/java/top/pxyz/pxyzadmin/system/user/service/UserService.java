package top.pxyz.pxyzadmin.system.user.service;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.user.service
 * @date 2020-06-07 9:45
 */
public interface UserService {

    /**
     * 获取用户列表信息
     * @param username
     * @param email
     * @return
     */
    List<Map<String, String>> getUserList(String username, String email);

    /**
     * 根据id获取用户基本信息
     * @param id
     * @return
     */
    Map<String,String> getUserById(String id);

    /**
     * 更新用户信息
     * @param id
     * @param username
     * @param email
     * @param remark
     */
    void updateUser(String id, String username, String email, String remark);

    /**
     * 新增用户信息
     * @param username
     * @param email
     * @param remark
     */
    void addUser(String username, String email, String remark);

    /**
     * 删除用户
     * @param id
     */
    void delUser(String id);
}
