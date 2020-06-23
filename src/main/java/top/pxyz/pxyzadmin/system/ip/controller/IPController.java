package top.pxyz.pxyzadmin.system.ip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IPController
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.ip.controller
 * @date 2020-06-22 22:06
 */
@Controller
public class IPController {

    /**
     * ip管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/ip/index";
    }


}
