package com.maodr.system.org.dao;

import java.util.List;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.system.org.model.OrgPO;
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

}
