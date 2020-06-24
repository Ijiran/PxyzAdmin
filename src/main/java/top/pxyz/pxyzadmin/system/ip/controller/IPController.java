package top.pxyz.pxyzadmin.system.ip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * IPController
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.ip.controller
 * @date 2020-06-22 22:06
 */
@Controller
@RequestMapping("ip")
public class IPController {

    /**
     * ip管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/ip/index";
    }

    /**
     * 查询IP白名单
     * @param request
     * @return
     */
    @RequestMapping("findWhiteList")
    public String findWhiteList(HttpServletRequest request){

        return null;
    }

}
