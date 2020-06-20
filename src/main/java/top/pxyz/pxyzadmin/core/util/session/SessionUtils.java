package top.pxyz.pxyzadmin.core.util.session;

import org.springframework.security.core.context.SecurityContextHolder;
import top.pxyz.pxyzadmin.core.security.CustomUserDetails;

/**
 * Session 工具类 获取当前登录用户信息
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.core.util.session
 * @date 2020-06-20 22:18
 */
public class SessionUtils {

    /**
     * 获取当前用户信息
     * @return
     */
    public static CustomUserDetails getUserInfo(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }

    /**
     * 获取用户id
     * @return
     */
    public static String getUserId(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId();
    }

    /**
     * 获取username
     * @return
     */
    public static String getUserName(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    /**
     * 获取用户邮箱
     * @return
     */
    public static String getUserEmail(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getEmail();
    }

}
