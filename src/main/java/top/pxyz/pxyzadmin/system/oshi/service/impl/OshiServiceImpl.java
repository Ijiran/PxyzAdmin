package top.pxyz.pxyzadmin.system.oshi.service.impl;

import org.springframework.stereotype.Service;
import top.pxyz.pxyzadmin.system.oshi.service.OshiService;

import java.util.Map;

/**
 * 监控Service接口实现
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.oshi.service.impl
 * @date 2020-06-24 23:44
 */
@Service
public class OshiServiceImpl implements OshiService {

    /**
     * 获取监控各项参数
     * @return
     */
    @Override
    public Map<String, Object> getOshiParam() {
        return null;
    }
}
