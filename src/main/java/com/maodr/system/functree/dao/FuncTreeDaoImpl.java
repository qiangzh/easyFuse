package com.maodr.system.functree.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
import com.maodr.system.functree.model.FuncTreePO;
import com.maodr.system.functree.vo.FuncTreeVO;

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
        crit.setMaxResults(10);
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

}
