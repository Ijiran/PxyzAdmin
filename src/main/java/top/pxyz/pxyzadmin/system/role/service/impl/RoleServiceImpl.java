package top.pxyz.pxyzadmin.system.role.service.impl;

import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.sequence.GenerateSequenceUtil;
import top.pxyz.pxyzadmin.system.role.mapper.RoleMapper;
import top.pxyz.pxyzadmin.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.role.service.impl
 * @date 2020-06-07 9:52
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取角色信息列表
     * @param roleName
     * @return
     */
    @Override
    public List<Map<String, String>> getRoleList(String roleName) {
        return roleMapper.getRoleList(roleName);
    }

    /**
     * 根据id获取角色信息
     * @param id
     * @return
     */
    @Override
    public Map<String, String> getRoleById(String id) {
        return roleMapper.getRoleById(id);
    }

    /**
     * 更新角色信息
     * @param id
     * @param roleName
     * @param remark
     */
    @Override
    public void updateRole(String id, String roleName, String remark) {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("roleName",roleName);
        map.put("remark",remark);
        roleMapper.updateRole(map);
    }

    /**
     * 新增角色信息
     * @param roleName
     * @param remark
     */
    @Override
    public void addRole(String roleName, String remark) {
        Map<String,String> map = new HashMap<>();
        map.put("id", GenerateSequenceUtil.generateSequenceNo());
        map.put("roleName",roleName);
        map.put("remark",remark);
        roleMapper.addRole(map);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void delRole(String id) {
        String[] ids = id.split(",");
        for (String i : ids){
            if(ValidateHelper.isNotEmptyString(i)){
                roleMapper.delRole(i);
            }
        }
    }

    /**
     * 为角色绑定菜单
     * @param roleId
     * @param menuIds
     */
    public void saveMenuBind(String roleId, String menuIds){
        roleMapper.delMenuBind(roleId);//清空角色菜单绑定
        String[] menus = menuIds.split(",");
        for (String menuId : menus){
            roleMapper.addMenuBind(roleId, menuId);
        }
    }

}
