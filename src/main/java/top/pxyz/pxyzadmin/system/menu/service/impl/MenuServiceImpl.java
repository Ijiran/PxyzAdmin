package top.pxyz.pxyzadmin.system.menu.service.impl;

import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.sequence.GenerateSequenceUtil;
import top.pxyz.pxyzadmin.core.util.tree.TreeUtil;
import top.pxyz.pxyzadmin.system.menu.mapper.MenuMapper;
import top.pxyz.pxyzadmin.system.menu.service.MenuService;
import top.pxyz.pxyzadmin.system.menu.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.service.impl
 * @date 2020-06-05 23:37
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static String HOME_URL = "首页";

    private static String HOME_TITLE = "/pxyzadmin/welcome";

    private static String LOGO_TITLE = "PXYZ ADMIN";

    private static String LOGO_IMAGE = "images/logo.png";

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单列表
     * @return
     */
    @Override
    public List<Map<String, String>> getMenuList() {
        List<Map<String,String>> menuList = menuMapper.getMenuList();
        return menuList;
    }

    /**
     * 根据id获取菜单
     * @param id
     * @return
     */
    public Map<String, String> getMenuById(String id){
        return menuMapper.getMenuById(id);
    }

    /**
     * 更新菜单状态
     * @param id
     * @param title
     * @param href
     * @param icon
     * @param target
     * @param remark
     * @param sort
     * @param pid
     */
    @Override
    public void updateMenu(String id, String title, String href, String icon, String target, String remark,  String sort, String pid){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("href",href);
        map.put("icon",icon);
        map.put("target",target);
        map.put("remark",remark);
        map.put("sort",sort);
        map.put("pid",pid);
        menuMapper.updateMenu(map);
    }

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
    public void addMenu(String id, String title, String href, String icon, String target, String remark, String sort, String pid){
        Map<String,String> map = new HashMap<>();
        map.put("id", GenerateSequenceUtil.generateSequenceNo());
        map.put("title",title);
        map.put("href",href);
        map.put("icon",icon);
        map.put("target",target);
        map.put("remark",remark);
        map.put("sort",sort);
        map.put("pid",pid);
        menuMapper.addMenu(map);
    }

    /**
     * 更新菜单状态信息
     * @param id
     * @param status
     */
    @Override
    public void updateMenuStatus(String id, String status){
        String[] ids = id.split(",");
        for (String i : ids){
            if(ValidateHelper.isNotEmptyString(i)){
                menuMapper.updateMenuStatus(i,status);
            }
        }
    }

    /**
     * 初始化菜单
     * @return
     */
    @Override
    public Map<String, Object> initMenus() {
        Map<String, Object> map = new HashMap<>(16);
        List<Map<String,String>> menuList = menuMapper.findMenus();
        List<MenuVo> menuInfo = new ArrayList<>();
        for (Map<String,String> m : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(m.get("ID"));
            menuVO.setPid(m.get("PID"));
            menuVO.setHref(m.get("HREF"));
            menuVO.setTitle(m.get("TITLE"));
            menuVO.setIcon(m.get("ICON"));
            menuVO.setTarget(m.get("TARGET"));
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, "0"));
        covertMenu(map);
        return map;
    }

    /**
     * 转换菜单，在查询出的菜单之上增加home、logo节点
     * @param map
     */
    private void covertMenu(Map<String,Object> map){
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        home.put("title",HOME_URL);
        home.put("href",HOME_TITLE);
        logo.put("title",LOGO_TITLE);
        logo.put("image",LOGO_IMAGE);
        map.put("homeInfo", home);
        map.put("logoInfo", logo);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    public void delMenu(String id){
        if(ValidateHelper.isNotEmptyString(id)){
            menuMapper.delMenu(id);
        }
    }

    /**
     * 获取角色id授权中的菜单
     * @param roleId
     * @return
     */
    public List<Map<String, String>> findMenuBind(String roleId){
        return menuMapper.findMenuBind(roleId);
    }

}
