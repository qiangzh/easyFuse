package com.maodr.system.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.framework.exception.BusinessException;
import com.maodr.framework.tree.TreeNode;
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

    /**
     *  添加|修改机构
     *  @param orgVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:31:09
     *  @lastModified      
     *  @history
     */
    public String saveOrg(OrgVO orgVO) {
        // 校验机构编码重复
        if (orgDao.checkOrgCodeExist(orgVO)) {
            throw new BusinessException("编码为{0}的机构已存在", new String[] { orgVO.getCode() });
        }

        // 校验机构名称重复    
        if (orgDao.checkOrgNameExist(orgVO)) {
            throw new BusinessException("名称为{0}的机构已存在", new String[] { orgVO.getName() });
        }

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
        List<OrgVO> voList = new ArrayList<OrgVO>();
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
        OrgVO orgVO = this.getOrg(id);
        // 校验机构下是否存在机构
        if (orgDao.checkOrgHasChild(orgVO)) {
            throw new BusinessException("机构{0}下存在其他机构,不能删除", new String[] { orgVO.getName() });
        }

        // 校验机构下是否有人员
        if (orgDao.checkOrgHasEmp(orgVO)) {
            throw new BusinessException("机构{0}下存在人员,不能删除", new String[] { orgVO.getName() });
        }

        orgDao.deleteOrg(id);
    }

    /**
     *  获取组织机构树根节点
     *  @return
     *  @author Administrator
     *  @created 2014年3月2日 上午5:50:07
     *  @lastModified      
     *  @history
     */
    public TreeNode saveOrGetRootOrg() {
        OrgVO orgVO = orgDao.getRootOrg();
        if (orgVO == null) {
            orgVO = new OrgVO();
            orgVO.setCode("0");
            orgVO.setName("组织机构");
            orgVO.setParentID("-1");
            orgVO.setStatus("1"); // 有效
            String id = this.saveOrg(orgVO);
            orgVO.setId(id);
        }
        TreeNode treeNode = new TreeNode(orgVO.getId(), orgVO.getCode(), orgVO.getName());
        return treeNode;
    }

}
