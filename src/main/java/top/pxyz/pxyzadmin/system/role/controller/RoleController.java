package top.pxyz.pxyzadmin.system.role.controller;

import com.alibaba.fastjson.JSONObject;
import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.page.PageUtils;
import top.pxyz.pxyzadmin.system.menu.service.MenuService;
import top.pxyz.pxyzadmin.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.role.controller
 * @date 2020-06-07 9:50
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 跳转权限管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/role/index";
    }

    /**
     * 跳转角色菜单绑定页
     * @return
     */
    @RequestMapping("toMenuBind")
    public Object toMenuBind(HttpServletRequest request){
        String id = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/role/menuBind");
        modelAndView.addObject("id",id);
        return modelAndView;
    }

    /**
     * 获取角色列表
     * @param request
     * @return
     */
    @RequestMapping("getRoleList")
    @ResponseBody
    public String getRoleList(HttpServletRequest request){
        String roleName = request.getParameter("roleName");
        List<Map<String,String>> list = roleService.getRoleList(roleName);
        return PageUtils.toPage(list);
    }

    /**
     * 获取角色列表（不供表格使用）
     * @param request
     * @return
     */
    @RequestMapping("getRoles")
    @ResponseBody
    public String getRoles(HttpServletRequest request){
        List<Map<String,String>> list = roleService.getRoleList("");
        return JSONObject.toJSONString(list);
    }

    /**
     * 跳转角色信息编辑页面
     * @param request
     * @return
     */
    @RequestMapping("toRoleInfo")
    public Object toRoleInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/role/roleInfo");
        if(ValidateHelper.isNotEmptyString(id)){
            modelAndView.addObject("formData",roleService.getRoleById(id));
        }
        return modelAndView;
    }

    /**
     * 保存角色信息
     * @param request
     * @return
     */
    @RequestMapping(value = "saveRole")
    @ResponseBody
    public String saveRole(HttpServletRequest request){
        String id = request.getParameter("id");
        String roleName = request.getParameter("role_name");
        String remark = request.getParameter("remark");
        if(ValidateHelper.isNotEmptyString(id)){
            roleService.updateRole(id,roleName,remark);
        }else{
            roleService.addRole(roleName,remark);
        }
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    /**
     * 删除角色
     * @param request
     * @return
     */
    @RequestMapping("delRole")
    @ResponseBody
    public String delRole(HttpServletRequest request){
        String id = request.getParameter("id");
        roleService.delRole(id);
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    /**
     * 获取当前角色已经绑定过的菜单
     * @param request
     * @return
     */
    @RequestMapping("getMenuBind")
    @ResponseBody
    public String getMenuBind(HttpServletRequest request){
        String id = request.getParameter("id");
        List<Map<String,String>> list = menuService.findMenuBind(id);
        return JSONObject.toJSONString(list);
    }

    /**
     * 为角色绑定菜单
     * @param request
     * @return
     */
    @RequestMapping("saveMenuBind")
    @ResponseBody
    public String saveMenuBind(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        String menuIds = request.getParameter("menuIds");
        roleService.saveMenuBind(roleId,menuIds);
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

}
