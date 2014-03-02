package com.maodr.system.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
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
        Session sess = getSession();
        Criteria crit = sess.createCriteria(OrgPO.class);
        crit.add(Restrictions.eq("parentID", treeNodeID));
        crit.setMaxResults(10);
        List<OrgPO> poList = crit.list();
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
     *  根据Code获取节点
     *  @return
     *  @author Administrator
     *  @created 2014年1月7日 上午6:53:22
     *  @lastModified       
     *  @history
     */
    public OrgVO getRootOrg() {
        OrgVO orgVO = null;
        List list = getSession().createCriteria(OrgPO.class).add(Restrictions.eq("parentID", "-1")).list();
        if (list != null && !list.isEmpty()) {
            orgVO = new OrgVO();
            BeanUtils.copyProperties(list.get(0), orgVO);
        }
        return orgVO;
    }

}
