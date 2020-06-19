package top.pxyz.pxyzadmin.system.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import top.pxyz.pxyzadmin.system.user.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.user.mapper
 * @date 2020-06-07 9:47
 */
@Repository
public interface UserMapper {

    /**
     * 获取用户列表信息
     * @param username
     * @param email
     * @return
     */
    List<Map<String, String>> getUserList(@Param("username") String username,@Param("email") String email);

    /***
     * 根据id获取用户信息
     * @param id
     * @return
     */
    Map<String, String> getUserById(String id);

    /**
     * 更新用户信息
     * @param map
     */
    void updateUser(Map<String, String> map);

    /**
     * 新增用户信息
     * @param map
     */
    void addUser(Map<String, String> map);

    /**
     * 删除用户操作
     * @param i
     */
    void delUser(String i);

    /**
     * 清空该用户下的绑定角色信息
     * @param userId
     */
    void delRoleBind(String userId);

    /**
     * 新增用户绑定角色信息
     * @param roleId
     * @param userId
     */
    void addRoleBind(@Param("roleId") String roleId, @Param("userId") String userId);

    /**
     * 根据名称查询User用户信息
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 更新密码
     * @param id
     * @param password
     */
    void updatePwd(@Param("id") String id,@Param("password") String password);

}
