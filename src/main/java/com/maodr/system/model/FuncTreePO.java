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
 *  功能树PO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
@Entity
@Table(name = "T_SYS_FUNCTREE")
public class FuncTreePO extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359413L;

    private String id;

    private String code; // required 

    private String name; // required    

    private String url; // required       

    private String type; // required   

    private String parentID; // required  

    public FuncTreePO() {
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "C_ID", unique = true, nullable = false, length = 32)
    public String getId() {
        return id;
    }

    @Column(name = "C_CODE", nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    @Column(name = "C_NAME", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "C_URL", nullable = false, unique = true)
    public String getUrl() {
        return url;
    }

    @Column(name = "C_TYPE", nullable = false, unique = true)
    public String getType() {
        return type;
    }

    @Column(name = "C_PARENTID", nullable = false)
    public String getParentID() {
        return parentID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        FuncTreePO other = (FuncTreePO) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        }
        else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FuncTreePO [id=" + id + ", code=" + code + ", name=" + name + ", url=" + url + ", type=" + type
                + ", parentID=" + parentID + "]";
    }

}
