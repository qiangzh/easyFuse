package com.maodr.system.org.dao;

import java.util.List;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.framework.exception.BusinessException;
import com.maodr.system.model.OrgPO;
import com.maodr.system.org.vo.OrgVO;

/**
 * 
 *  组织机构Dao接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface OrgDao extends BaseDao<OrgPO, String> {

    /**
     * 
     *  添加组织机构
     *  @param orgVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateOrg(OrgVO orgVO);

    /**
     * 
     *  获取子节点
     *  @param treeNodeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月7日 上午6:53:22
     *  @lastModified       
     *  @history
     */
    public List<OrgVO> listSubOrgs(String treeNodeID);

    /**
     * 
     *  获取根节点
     *  @return
     *  @author Administrator
     *  @created 2014年1月7日 上午6:53:22
     *  @lastModified       
     *  @history
     */
    public OrgVO getRootOrg();

    /**
     * 
     *  校验机构编码重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:11
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgCodeExist(OrgVO orgVO);

    /**
     * 
     *  校验机构名称重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:22
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgNameExist(OrgVO orgVO);

    /**
     * 
     *  校验机构下是否存在机构(是否为叶子节点)
     *  @param orgVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午5:05:55
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgHasChild(OrgVO orgVO);

    /**
     * 
     *  校验机构下是否有人员
     *  @param orgVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午5:06:29
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgHasEmp(OrgVO orgVO);

    /**
     * 
     *  删除组织机构
     *  @param id
     *  @author Administrator
     *  @created 2014年3月5日 上午5:39:54
     *  @lastModified       
     *  @history
     */
    public void deleteOrg(String id);

}
