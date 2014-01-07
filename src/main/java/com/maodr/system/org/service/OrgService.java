package com.maodr.system.org.service;

import java.util.List;

import com.maodr.system.org.vo.OrgVO;

/**
 * 
 *  组织机构Service接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface OrgService {

    /**
     * 
     *  保存Org
     *  @param orgVO
     *  @return
     *  @author Administrator
     *  @created 2013年12月31日 上午4:39:37
     *  @lastModified       
     *  @history
     */
    public String saveOrg(OrgVO orgVO);

    /**
     * 
     *  显示组织机构
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<OrgVO> listOrgs();

    /**
     * 
     *  获取组织机构信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public OrgVO getOrg(String id);

    /**
     * 
     *  获取子组织机构
     *  @param treeNodeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:56:17
     *  @lastModified       
     *  @history
     */
    public List<OrgVO> listSubOrgs(String treeNodeID);

}
