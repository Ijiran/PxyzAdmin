package top.pxyz.pxyzadmin.system.oshi.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.pxyz.pxyzadmin.system.oshi.service.OshiService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.oshi.controller
 * @date 2020-06-24 23:43
 */
@Controller
@RequestMapping("oshi")
public class OshiController {

    @Autowired
    private OshiService oshiService;

    /**
     * 监控管理首页
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "admin/system/oshi/index";
    }

    /**
     * 获取监控各项参数
     * @return
     */
    @RequestMapping("getOshiParam")
    @ResponseBody
    public String getOshiParam(){
        Map<String,Object> oshiMap = oshiService.getOshiParam();
        Map<String,Object> map = new HashMap<>();
        map.put("result","success");
        map.put("data",oshiMap);
        return JSONObject.toJSONString(map);
    }

}
