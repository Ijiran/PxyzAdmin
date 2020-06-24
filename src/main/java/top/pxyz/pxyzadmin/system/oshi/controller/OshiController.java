package top.pxyz.pxyzadmin.system.oshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.oshi.controller
 * @date 2020-06-24 23:43
 */
@Controller
@RequestMapping("oshi")
public class OshiController {

    /**
     * 监控管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/oshi/index";
    }

}
