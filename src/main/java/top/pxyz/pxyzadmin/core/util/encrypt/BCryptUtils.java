package top.pxyz.pxyzadmin.core.util.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * security加密
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.core.util.encrypt
 * @date 2020-06-11 0:43
 */
public class BCryptUtils {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String encode(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncrypt = passwordEncoder.encode(password);
        return passwordEncrypt;
    }

    public static void main(String[] args) {
        String s = BCryptUtils.encode("123456");
        System.out.println(s);
    }
}
