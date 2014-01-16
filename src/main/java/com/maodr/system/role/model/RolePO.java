package com.maodr.system.role.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.maodr.framework.base.model.BaseObject;

/**
 * 
 *  角色PO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class RolePO extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String roleCode; // 编码

    private String roleName; // 角色名

    public RolePO() {
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "C_ID", unique = true, nullable = false, length = 32)
    public String getId() {
        return id;
    }

    @Column(name = "C_ROLECODE", nullable = false, length = 50, unique = true)
    public String getRoleCode() {
        return roleCode;
    }

    @Column(name = "C_ROLENAME", nullable = false, length = 50, unique = true)
    public String getRoleName() {
        return roleName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RolePO other = (RolePO) obj;
        if (roleCode == null) {
            if (other.roleCode != null)
                return false;
        }
        else if (!roleCode.equals(other.roleCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RolePO [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + "]";
    }

}
