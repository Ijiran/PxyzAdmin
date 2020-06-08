package top.pxyz.pxyzadmin.system.menu.controller;

import com.alibaba.fastjson.JSONObject;
import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.page.PageUtils;
import top.pxyz.pxyzadmin.system.menu.service.MenuService;
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
 * @Package top.pxyz.pxyzadmin.system.controller
 * @date 2020-06-05 22:50
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 初始化菜单
     * @return
     */
    @RequestMapping("init")
    @ResponseBody
    public String init(){
        Map<String,Object> map = menuService.initMenus();
        return JSONObject.toJSONString(map);
    }

    /**
     * 菜单管理页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/menu/index";
    }

    /**
     * 获取菜单列表
     * @return
     */
    @RequestMapping("getMenuList")
    @ResponseBody
    public String getMenuList(){
        List<Map<String,String>> list = menuService.getMenuList();
        return PageUtils.toPage(list);
    }

    /**
     * 菜单编辑页面
     * @return
     */
    @RequestMapping("toMenuInfo")
    public Object toMenuInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        Map<String,String> menuInfo = menuService.getMenuById(id);
        Map<String,String> formData = new HashMap<>();
        if("1".equals(type)){//新增
            formData.put("pid",menuInfo.get("id"));
            formData.put("pTitle",menuInfo.get("title"));
        }else{//修改
            formData = menuInfo;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/menu/menuInfo");
        modelAndView.addObject("formData",formData);
        return modelAndView;
    }

    /**
     * 更新菜单状态信息
     * @param request
     * @return
     */
    @RequestMapping("updateMenuStatus")
    @ResponseBody
    public String updateMenuStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        menuService.updateMenuStatus(id,status);
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    /**
     * 保存菜单信息
     * @param request
     * @return
     */
    @RequestMapping(value = "saveMenu")
    @ResponseBody
    public String saveMenu(HttpServletRequest request){
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String icon = request.getParameter("icon");
        String target = request.getParameter("target");
        String sort = request.getParameter("sort");
        String remark = request.getParameter("remark");
        String pid = request.getParameter("pid");
        if(ValidateHelper.isNotEmptyString(id)){
            menuService.updateMenu(id,title,href,icon,target,remark,sort,pid);
        }else{
            menuService.addMenu(id,title,href,icon,target,remark,sort,pid);
        }
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("delMenu")
    @ResponseBody
    public String delMenu(HttpServletRequest request){
        String id = request.getParameter("id");
        menuService.delMenu(id);
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }
}
