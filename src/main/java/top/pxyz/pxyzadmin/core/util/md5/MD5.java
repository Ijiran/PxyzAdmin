package top.pxyz.pxyzadmin.core.util.md5;

import org.springframework.util.DigestUtils;

/**
 * @author ljp
 * @date 2020/2/12-15:29
 */
public class MD5 {
    public static String MD5(String values){
        return DigestUtils.md5DigestAsHex(values.getBytes());
    }
}
