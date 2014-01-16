package com.maodr.system.role.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 *  角色VO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String roleCode;

    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
