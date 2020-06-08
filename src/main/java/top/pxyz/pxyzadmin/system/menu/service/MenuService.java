package top.pxyz.pxyzadmin.system.menu.service;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.service
 * @date 2020-06-05 23:37
 */
public interface MenuService {

    /**
     * 获取菜单
     * @return
     */
    List<Map<String, String>> getMenuList();

    /**
     * 初始化菜单
     * @return
     */
    Map<String, Object> initMenus();

    /**
     * 更新菜单信息
     * @param id
     * @param title
     * @param href
     * @param icon
     * @param target
     * @param remark
     * @param sort
     * @param pid
     */
    void updateMenu(String id, String title, String href, String icon, String target, String remark, String sort, String pid);

    /**
     * 更新菜单状态
     * @param id
     * @param status
     */
    void updateMenuStatus(String id, String status);

    /**
     * 根据id获取菜单
     * @param id
     * @return
     */
    Map<String, String> getMenuById(String id);

    /**
     * 新增菜单
     * @param id
     * @param title
     * @param href
     * @param icon
     * @param target
     * @param remark
     * @param sort
     * @param pid
     */
    void addMenu(String id, String title, String href, String icon, String target, String remark, String sort, String pid);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    void delMenu(String id);

    /**
     * 获取角色id绑定中的菜单
     * @param roleId
     * @return
     */
    List<Map<String, String>> findMenuBind(String roleId);

}
