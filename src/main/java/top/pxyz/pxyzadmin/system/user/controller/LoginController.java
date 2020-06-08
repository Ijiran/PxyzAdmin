package top.pxyz.pxyzadmin.system.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ijiran
 * @Package com.xxzx.pxyzadmin.index
 * @date 2020-05-25 23:11
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/login/login")
    public String toLogin(){
        return "login/login";
    }

    /**
     * 跳转后台管理首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "admin/index";
    }

    /**
     * 跳转后台管理首页-默认展示页面
     * @return
     */
    @RequestMapping("/welcome")
    public String toWelcome(){
        return "admin/welcome";
    }

    /**
     * 登录操作
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //登录成功后跳转
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/index");
        return model;
    }

}
