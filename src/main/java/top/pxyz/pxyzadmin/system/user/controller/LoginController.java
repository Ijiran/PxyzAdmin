package top.pxyz.pxyzadmin.system.user.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView toLogin(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("msg", "不正确的用户名和密码");
        }
        if (logout != null) {
            model.addObject("msg", "你已经成功退出");
        }
        model.setViewName("login/login");
        return model;
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
     * 清理缓存
     * @return
     */
    @RequestMapping("/clear")
    @ResponseBody
    public String clear(){
        Map<String,String> map = new HashMap<>();
        map.put("result","success");
        return JSONObject.toJSONString(map);
    }

    /**
     * 登录操作
     * @return
     */
    /*@RequestMapping("login")
    public ModelAndView login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //登录成功后跳转
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/index");
        return model;
    }*/

}
