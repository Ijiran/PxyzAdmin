package top.pxyz.pxyzadmin.core.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：自定义WebAuthenticationDetails，将验证码和用户名、密码一同带入AuthenticationProvider中
 **/
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = 6975601077710753878L;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
