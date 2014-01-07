package com.maodr.system.org.vo;

import java.io.Serializable;

/**
 * 
 *  组织机构VO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
public class OrgVO implements Serializable {
    private static final long serialVersionUID = 3832626162173359412L;

    private String id;

    private String name;

    private String code;

    private String parentID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

}
