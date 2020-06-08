package top.pxyz.pxyzadmin.core.util.page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @date 2020-02-09 20:07
 */
public class PageUtils {

    /**
     * 转分页JSON
     * @param list
     * @param page
     * @return
     */
    public static String toPage(List<Map<String,String>> list, Page page){
        page.setCount(list.size());
        page.setData(JSONArray.parseArray(JSON.toJSONString(list)));
        return JSON.toJSONString(page);
    }

    /**
     * 转分页JSON
     * @param list
     * @return
     */
    public static String toPage(List<Map<String,String>> list){
        Page page = new Page();
        page.setCount(list.size());
        page.setData(JSONArray.parseArray(JSON.toJSONString(list)));
        return JSON.toJSONString(page);
    }

    /**
     * 转分页JSON,附带信息
     * @param list
     * @param msg
     * @return
     */
    public static String toPage(List<Map<String,String>> list,String msg){
        Page page = new Page();
        page.setCount(list.size());
        page.setMsg(msg);
        page.setData(JSONArray.parseArray(JSON.toJSONString(list)));
        return JSON.toJSONString(page);
    }

}
