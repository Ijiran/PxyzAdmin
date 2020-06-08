package top.pxyz.pxyzadmin.system.user.controller;

import com.alibaba.fastjson.JSONObject;
import top.pxyz.pxyzadmin.core.util.base.ValidateHelper;
import top.pxyz.pxyzadmin.core.util.page.PageUtils;
import top.pxyz.pxyzadmin.system.user.service.UserService;
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
 * @Package top.pxyz.pxyzadmin.system.user.controller
 * @date 2020-06-07 9:44
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转用户管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/user/index";
    }

    /**
     * 跳转用户信息编辑页面
     * @param request
     * @return
     */
    @RequestMapping("toUserInfo")
    public Object toUserInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/user/userInfo");
        if(ValidateHelper.isNotEmptyString(id)){
            modelAndView.addObject("formData",userService.getUserById(id));
        }
        return modelAndView;
    }

    /**
     * 跳转角色绑定页面
     * @param request
     * @return
     */
    @RequestMapping("toRoleBind")
    public Object toRoleBind(HttpServletRequest request){
        String id = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/user/roleBind");
        modelAndView.addObject("id",id);
        return modelAndView;
    }

    /**
     * 获取用户列表
     * @param request
     * @return
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public String getUserList(HttpServletRequest request){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        List<Map<String,String>> list = userService.getUserList(username, email);
        return PageUtils.toPage(list);
    }

    /**
     * 保存用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "saveUser")
    @ResponseBody
    public String saveUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String remark = request.getParameter("remark");
        if(ValidateHelper.isNotEmptyString(id)){
            userService.updateUser(id,username,email,remark);
        }else{
            userService.addUser(username,email,remark);
        }
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    /**
     * 删除用户
     * @param request
     * @return
     */
    @RequestMapping("delUser")
    @ResponseBody
    public String delUser(HttpServletRequest request){
        String id = request.getParameter("id");
        userService.delUser(id);
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

}
