package com.maodr.system.functree.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
import com.maodr.framework.util.StringUtil;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.FuncTreePO;

/**
 * 
 *  功能树Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class FuncTreeDaoImpl extends BaseDaoImpl<FuncTreePO, String> implements FuncTreeDao {

    public FuncTreeDaoImpl() {
        super(FuncTreePO.class);
    }

    public FuncTreeDaoImpl(Class<FuncTreePO> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

    /**
     * 
     *  添加功能树
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateFuncTree(FuncTreeVO funcTreeVO) {
        FuncTreePO funcTreePO = new FuncTreePO();
        BeanUtils.copyProperties(funcTreeVO, funcTreePO);
        funcTreePO = super.save(funcTreePO);
        return funcTreePO.getId();
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
    public List<FuncTreeVO> listSubFuncTrees(String treeNodeID) {
        Session sess = getSession();
        Criteria crit = sess.createCriteria(FuncTreePO.class);
        crit.add(Restrictions.eq("parentID", treeNodeID));
        crit.addOrder(Order.asc("sort"));
        List<FuncTreePO> poList = crit.list();
        List<FuncTreeVO> voList = new ArrayList();
        FuncTreeVO funcTreeVO;
        for (FuncTreePO funcTreePO : poList) {
            funcTreeVO = new FuncTreeVO();
            BeanUtils.copyProperties(funcTreePO, funcTreeVO);
            voList.add(funcTreeVO);
        }
        return voList;
    }

    /**
     * 
     *  校验功能编码重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:11
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeCodeExist(FuncTreeVO funcTreeVO) {
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(FuncTreePO.class);
        if (!StringUtil.isEmpty(funcTreeVO.getId())) {
            criteria.add(Restrictions.ne("id", funcTreeVO.getId()));
        }
        criteria.add(Restrictions.eq("code", funcTreeVO.getCode()));
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;

    }

    /**
     * 
     *  校验功能名称重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:22
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeNameExist(FuncTreeVO funcTreeVO) {
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(FuncTreePO.class);
        if (!StringUtil.isEmpty(funcTreeVO.getId())) {
            criteria.add(Restrictions.ne("id", funcTreeVO.getId()));
        }
        criteria.add(Restrictions.eq("name", funcTreeVO.getName()));
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;
    }

    /**
     * 
     *  生成Sort字段用"-"分隔16进制
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月8日 下午12:40:42
     *  @lastModified       
     *  @history
     */
    public String generateFuncTreeSort(FuncTreeVO funcTreeVO) {
        String parentSort = "";
        Long maxNum = 1L;
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(FuncTreePO.class);
        criteria.add(Restrictions.eq("parentID", funcTreeVO.getParentID()));
        criteria.addOrder(Order.desc("sort"));
        criteria.setMaxResults(1);
        List<FuncTreePO> poList = criteria.list();
        if (poList != null && !poList.isEmpty()) {
            // 存在兄弟节点
            FuncTreePO funcTreePO = poList.get(0);
            String brotherSort = funcTreePO.getSort();
            String[] sortPath = brotherSort.split("-");
            if (sortPath.length > 0) {
                for (int i = 0; i < sortPath.length - 1; i++) {
                    parentSort = parentSort + sortPath[i] + "-";
                }
                maxNum = Long.valueOf(sortPath[sortPath.length - 1], 16) + 1;
            }
        }
        else {
            // 不存在兄弟节点
            FuncTreePO parentFuncTreePO = this.get(funcTreeVO.getParentID());
            if (parentFuncTreePO != null) {
                parentSort = parentFuncTreePO.getSort();
            }
        }
        return (parentSort + Long.toHexString(maxNum) + "-").toUpperCase();
    }

    /**
     * 
     *  校验功能下是否存在功能(是否为叶子节点)
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月10日 上午6:13:03
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeHasChild(FuncTreeVO funcTreeVO) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(FuncTreePO.class);
        criteria.add(Restrictions.eq("parentID", funcTreeVO.getId()));
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return total > 0 ? true : false;
    }

    /**
     * 
     *  删除角色功能树关联关系
     *  @param id
     *  @author Administrator
     *  @created 2014年3月10日 上午6:26:36
     *  @lastModified       
     *  @history
     */
    public void deleteRoleFuncTreeByFuncTreeID(String funcTreeID) {
        Session session = getSession();
        String hqlDelete = "delete RoleFuncTreePO po where po.funcTreeID = :funcTreeID";
        Query query = session.createQuery(hqlDelete);
        query.setParameter("funcTreeID", funcTreeID);
        query.executeUpdate();
    }

}
