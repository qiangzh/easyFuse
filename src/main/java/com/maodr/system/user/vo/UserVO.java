package com.maodr.system.user.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maodr.system.role.vo.RoleVO;

/**
 * 
 *  用户VO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
public class UserVO implements Serializable, UserDetails {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private String phoneNumber;

    private boolean enabled = true;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    private List<RoleVO> roleList = new ArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public List<RoleVO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleVO> roleList) {
        this.roleList = roleList;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.add(new GrantedAuthority() {
            public String getAuthority() {
                return "ROLE_USER";
            }
        });
        authorities.add(new GrantedAuthority() {
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        });
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }

}
