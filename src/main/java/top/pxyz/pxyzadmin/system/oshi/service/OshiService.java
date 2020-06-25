package top.pxyz.pxyzadmin.system.oshi.service;

import java.util.Map;

/**
 * 监控Service接口
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.oshi.service
 * @date 2020-06-24 23:44
 */
public interface OshiService {

    /**
     * 获取监控各项参数
     * @return
     */
    Map<String, Object> getOshiParam();
}
