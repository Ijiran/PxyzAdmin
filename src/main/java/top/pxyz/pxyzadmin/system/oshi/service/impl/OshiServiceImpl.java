package top.pxyz.pxyzadmin.system.oshi.service.impl;

import org.springframework.stereotype.Service;
import top.pxyz.pxyzadmin.system.oshi.service.OshiService;

import java.util.HashMap;
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
        Map<String,Object> map = new HashMap<>();
        map.put("cpu-core-num","");//cpu核心数
        map.put("cpu-user","");//用户使用率
        map.put("cpu-system","");//系统使用率
        map.put("cpu-free","");//当前空闲率
        map.put("ram-all","");//总内存-内存
        map.put("ram-all-jvm","");//总内存-JVM内存
        map.put("ram-used","");//已用内存-内存
        map.put("ram-used-jvm","");//已用内存-JVM内存
        map.put("ram-residue","");//剩余内存-内存
        map.put("ram-residue-jvm","");//剩余内存-JVM内存
        map.put("ram-used-rate","");//使用率-内存
        map.put("ram-used-rate-jvm","");//使用率-JVM内存
        map.put("client-name","");//服务器名称
        map.put("client-os","");//操作系统
        map.put("client-ip","");//服务器IP
        map.put("client-system","");//系统架构
        map.put("java-name","");//Java名称
        map.put("java-version","");//java版本
        map.put("java-start-time","");//启动时间
        map.put("java-run-time","");//运行时长
        return map;
    }
}
