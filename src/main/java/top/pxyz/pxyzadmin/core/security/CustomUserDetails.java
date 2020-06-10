package top.pxyz.pxyzadmin.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.pxyz.pxyzadmin.system.user.bean.User;

import java.util.Collection;

/**
 * 描述：自定义UserDetails，使UserDetails具有User的实体结构
 **/
public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(User user){
        if(null != user){
            this.setId(user.getId());
            this.setCreateTime(user.getCreateTime());
            this.setUpdateTime(user.getUpdateTime());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setEmail(user.getEmail());
            this.setRemark(user.getRemark());
            this.setMark(user.getMark());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
