package top.pxyz.pxyzadmin.system.menu.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.mapper
 * @date 2020-06-05 23:38
 */
@Repository
public interface MenuMapper {

    /**
     * 查询菜单
     * @return
     */
    List<Map<String, String>> findMenus();

    /**
     * 查询菜单列表信息（全部菜单）
     * @return
     */
    List<Map<String, String>> getMenuList();

    /**
     * 更新菜单信息
     */
    void updateMenu(Map<String,String> map);

    /**
     * 更新菜单状态信息
     * @param id
     * @param status
     */
    void updateMenuStatus(@Param("id") String id,@Param("status") String status);

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    Map<String, String> getMenuById(String id);

    /**
     * 新增菜单
     * @param map
     */
    void addMenu(Map<String, String> map);

    /**
     * 删除菜单
     * @param id
     */
    void delMenu(String id);

    /**
     * 获取角色id授权中的菜单
     * @param roleId
     * @return
     */
    List<Map<String, String>> findMenuBind(String roleId);

}
