package com.maodr.system.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.system.model.OrgPO;
import com.maodr.system.org.dao.OrgDao;
import com.maodr.system.org.vo.OrgVO;

/**
 * 
 *  组织机构Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class OrgServiceImpl implements OrgService {

    private OrgDao orgDao;

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    public String saveOrg(OrgVO orgVO) {
        return orgDao.saveOrUpdateOrg(orgVO);
    }

    /**
     * 
     *  显示组织机构
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<OrgVO> listOrgs() {
        List<OrgPO> poList = orgDao.getAllDistinct();
        List<OrgVO> voList = new ArrayList();
        OrgVO orgVO;
        for (OrgPO orgPO : poList) {
            orgVO = new OrgVO();
            BeanUtils.copyProperties(orgPO, orgVO);
            voList.add(orgVO);
        }
        return voList;
    }

    /**
     * 
     *  获取组织机构信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public OrgVO getOrg(String id) {
        OrgPO orgPO = orgDao.get(id);
        OrgVO orgVO = new OrgVO();
        BeanUtils.copyProperties(orgPO, orgVO);
        return orgVO;

    }

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
    public List<OrgVO> listSubOrgs(String treeNodeID) {
        return orgDao.listSubOrgs(treeNodeID);

    }

    /**
    * 
    *  删除组织机构
    *  @param org
    *  @author Administrator
    *  @created 2014年1月13日 下午9:20:50
    *  @lastModified       
    *  @history
    */
    public void deleteOrg(String id) {
        orgDao.remove(id);
    }

}
