package com.maodr.system.org.model;

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
 *  组织机构PO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
@Entity
@Table(name = "T_SYS_ORG")
public class OrgPO extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359413L;

    private String id;

    private String name; // required

    private String code; // required   
    
    private String parentID; // required  

    public OrgPO() {
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "C_ID", unique = true, nullable = false, length = 32)
    public String getId() {
        return id;
    }

    @Column(name = "C_NAME", nullable = false, length = 50, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "C_CODE", nullable = false)
    public String getCode() {
        return code;
    }  

    @Column(name = "C_PARENTID", nullable = false)
    public String getParentID() {
        return parentID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "OrgPO [id=" + id + ", name=" + name + ", code=" + code + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        OrgPO other = (OrgPO) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        }
        else if (!code.equals(other.code))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

}
