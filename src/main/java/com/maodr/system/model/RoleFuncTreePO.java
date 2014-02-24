package com.maodr.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.maodr.framework.base.model.BaseObject;

/**
 * 
 *  角色_功能对应PO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
@Entity
@Table(name = "T_SYS_ROLE_FUNCTREE")
public class RoleFuncTreePO extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String roleID; // required   

    private String funcTreeID; // required

    public RoleFuncTreePO() {
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "C_ID", unique = true, nullable = false, length = 32)
    public String getId() {
        return id;
    }

    @Column(name = "C_ROLEID", nullable = false)
    public String getRoleID() {
        return roleID;
    }

    @Column(name = "C_FUNCTREEID", nullable = false, length = 50, unique = true)
    public String getFuncTreeID() {
        return funcTreeID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setFuncTreeID(String funcTreeID) {
        this.funcTreeID = funcTreeID;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((funcTreeID == null) ? 0 : funcTreeID.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
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
        RoleFuncTreePO other = (RoleFuncTreePO) obj;
        if (funcTreeID == null) {
            if (other.funcTreeID != null)
                return false;
        }
        else if (!funcTreeID.equals(other.funcTreeID))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (roleID == null) {
            if (other.roleID != null)
                return false;
        }
        else if (!roleID.equals(other.roleID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RoleFuncTreePO [id=" + id + ", roleID=" + roleID + ", funcTreeID=" + funcTreeID + "]";
    }

}
