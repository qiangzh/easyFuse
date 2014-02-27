package com.maodr.system.functree.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  功能树VO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
public class FuncTreeVO implements Serializable {
    private static final long serialVersionUID = 3832626162173359412L;

    private String id;

    private String code; // 功能编码

    private String name; // 功能名称

    private String url; // 功能URL

    private String type; // 类型功能点|功能树

    private String sort; // 排序

    private String parentID; // 父节点

    // 附加字段

    private boolean hasChild = false;

    private List<FuncTreeVO> children = new ArrayList<FuncTreeVO>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<FuncTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<FuncTreeVO> children) {
        this.children = children;
    }

}
