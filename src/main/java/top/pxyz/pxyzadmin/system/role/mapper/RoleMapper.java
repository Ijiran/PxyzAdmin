package top.pxyz.pxyzadmin.system.role.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.role.mapper
 * @date 2020-06-07 9:49
 */
@Repository
public interface RoleMapper {

    /**
     * 获取角色信息列表
     * @param roleName
     * @return
     */
    List<Map<String, String>> getRoleList(@Param("roleName") String roleName);

    /**
     * 根据id获取角色信息
     * @param id
     * @return
     */
    Map<String, String> getRoleById(String id);

    /**
     * 更新角色信息
     * @param map
     */
    void updateRole(Map<String, String> map);

    /**
     * 添加角色信息
     * @param map
     */
    void addRole(Map<String, String> map);

    /**
     * 删除角色
     * @param id
     */
    void delRole(String id);

    /**
     * 删除该角色的菜单授权
     * @param roleId
     */
    void delMenuBind(String roleId);

    /**
     * 添加角色菜单绑定
     * @param roleId
     * @param menuId
     */
    void addMenuBind(@Param("roleId") String roleId, @Param("menuId") String menuId);

    /**
     * 查询用户绑定的角色
     * @param userId
     * @return
     */
    List<Map<String, String>> getRoleBind(String userId);
}
