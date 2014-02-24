package com.maodr.system.role.vo;

import java.io.Serializable;

/**
 * 
 *  角色_功能对应VO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
public class RoleFuncTreeVO implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String roleID; // required   

    private String funcTreeID; // required

    private String hasPermission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFuncTreeID() {
        return funcTreeID;
    }

    public void setFuncTreeID(String funcTreeID) {
        this.funcTreeID = funcTreeID;
    }

    public String getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(String hasPermission) {
        this.hasPermission = hasPermission;
    }

}
