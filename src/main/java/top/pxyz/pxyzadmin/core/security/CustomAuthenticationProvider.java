package top.pxyz.pxyzadmin.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.pxyz.pxyzadmin.core.util.encrypt.BCryptUtils;
import top.pxyz.pxyzadmin.core.util.encrypt.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 描述：自定义SpringSecurity的认证器
 **/
@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户输入的用户名
        String username = authentication.getName();
        //用户输入的密码
        String password = authentication.getCredentials().toString();
        //通过自定义的CustomUserDetailsService，以用户输入的用户名查询用户信息
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        //校验用户密码
        String passwordEncrypt = MD5Utils.encrypt(password);
        if(!userDetails.getPassword().equals(passwordEncrypt)){
            System.out.println("密码错误");
            throw new BadCredentialsException("密码错误");
        }
        Object principalToReturn = userDetails;
        //将用户信息塞到SecurityContext中，方便获取当前用户信息
        return this.createSuccessAuthentication(principalToReturn, authentication, userDetails);
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
