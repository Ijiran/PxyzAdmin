package top.pxyz.pxyzadmin.system.role.service;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.role.service
 * @date 2020-06-07 9:52
 */
public interface RoleService {
    /**
     * 获取角色信息列表
     * @param roleName
     * @return
     */
    List<Map<String, String>> getRoleList(String roleName);

    /**
     * 根据id获取角色信息
     * @param id
     * @return
     */
    Map<String,String> getRoleById(String id);

    /**
     * 更新角色信息
     * @param id
     * @param roleName
     * @param remark
     */
    void updateRole(String id, String roleName, String remark);

    /**
     * 添加角色信息
     * @param roleName
     * @param remark
     */
    void addRole(String roleName, String remark);

    /**
     * 删除角色
     * @param id
     */
    void delRole(String id);

    /**
     * 为角色绑定菜单
     * @param roleId
     * @param menuIds
     */
    void saveMenuBind(String roleId, String menuIds);
}
