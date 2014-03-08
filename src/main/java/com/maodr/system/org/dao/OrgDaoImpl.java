package com.maodr.system.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
import com.maodr.framework.util.StringUtil;
import com.maodr.system.model.OrgPO;
import com.maodr.system.org.vo.OrgVO;

/**
 * 
 *  组织机构Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class OrgDaoImpl extends BaseDaoImpl<OrgPO, String> implements OrgDao {

    public OrgDaoImpl() {
        super(OrgPO.class);
    }

    public OrgDaoImpl(Class<OrgPO> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

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
    public String saveOrUpdateOrg(OrgVO orgVO) {
        OrgPO orgPO = new OrgPO();
        BeanUtils.copyProperties(orgVO, orgPO);
        orgPO = super.save(orgPO);
        return orgPO.getId();
    }

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
    public List<OrgVO> listSubOrgs(String treeNodeID) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(OrgPO.class);
        criteria.add(Restrictions.eq("parentID", treeNodeID));
        criteria.add(Restrictions.eq("status", "1")); // 有效
        criteria.setMaxResults(10);
        List<OrgPO> poList = criteria.list();
        List<OrgVO> voList = new ArrayList();
        OrgVO orgVO = null;
        for (OrgPO orgPO : poList) {
            orgVO = new OrgVO();
            BeanUtils.copyProperties(orgPO, orgVO);
            voList.add(orgVO);
        }
        return voList;
    }

    /**
     * 
     *  根据Code获取节点
     *  @return
     *  @author Administrator
     *  @created 2014年1月7日 上午6:53:22
     *  @lastModified       
     *  @history
     */
    public OrgVO getRootOrg() {
        OrgVO orgVO = null;
        Criteria criteria =getSession().createCriteria(OrgPO.class);
        criteria.add(Restrictions.eq("parentID", "-1"));
        criteria.add(Restrictions.eq("status", "1")); // 有效        
        List list = criteria.list();
        if (list != null && !list.isEmpty()) {
            orgVO = new OrgVO();
            BeanUtils.copyProperties(list.get(0), orgVO);
        }
        return orgVO;
    }

    /**
     * 
     *  校验机构编码重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:11
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgCodeExist(OrgVO orgVO) {
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(OrgPO.class);
        if (!StringUtil.isEmpty(orgVO.getId())) {
            criteria.add(Restrictions.ne("id", orgVO.getId()));
        }
        criteria.add(Restrictions.eq("code", orgVO.getCode()));
        criteria.add(Restrictions.eq("status", "1")); // 有效     
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;
    }

    /**
     * 
     *  校验机构名称重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:22
     *  @lastModified       
     *  @history
     */
    public boolean checkOrgNameExist(OrgVO orgVO) {
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(OrgPO.class);
        if (!StringUtil.isEmpty(orgVO.getId())) {
            criteria.add(Restrictions.ne("id", orgVO.getId()));
        }
        criteria.add(Restrictions.eq("name", orgVO.getName()));
        criteria.add(Restrictions.eq("status", "1")); // 有效     
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;
    }

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
    public boolean checkOrgHasChild(OrgVO orgVO) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(OrgPO.class);
        criteria.add(Restrictions.eq("parentID", orgVO.getId()));
        criteria.add(Restrictions.eq("status", "1")); // 有效     
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;
    }

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
    public boolean checkOrgHasEmp(OrgVO orgVO) {
        //TODO　校验机构下是否有人员
        return false;
    }

    /**
     * 
     *  删除组织机构
     *  @param id
     *  @author Administrator
     *  @created 2014年3月5日 上午5:39:54
     *  @lastModified       
     *  @history
     */
    public void deleteOrg(String id) {
        OrgPO orgPO = this.get(id);
        orgPO.setStatus("0"); // 设置为无效
        this.save(orgPO);
    }
}
