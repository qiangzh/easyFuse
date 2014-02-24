package com.maodr.system.role.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
import com.maodr.system.model.RoleFuncTreePO;
import com.maodr.system.model.RolePO;
import com.maodr.system.model.UserPO;
import com.maodr.system.role.vo.RoleFuncTreeVO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  角色Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class RoleDaoImpl extends BaseDaoImpl<RolePO, String> implements RoleDao {

    public RoleDaoImpl() {
        super(RolePO.class);
    }

    public RoleDaoImpl(Class<RolePO> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

    /**
     * 
     *  添加角色
     *  @param roleVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateRole(RoleVO roleVO) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(roleVO, rolePO);
        rolePO = super.save(rolePO);
        return rolePO.getId();
    }

    /**
     * 
     *  查看已分配用户
     *  @param roleID
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午2:52:40
     *  @lastModified       
     *  @history
     */
    public List<UserVO> listUserOfRole(String roleID) {
        List<UserVO> userList = new ArrayList<UserVO>();

        StringBuilder hql = new StringBuilder(50);
        hql.append("select userPO from UserPO userPO,UserRolePO userRolePO");
        hql.append(" where userPO.id = userRolePO.userID ");
        hql.append("   and userRolePO.roleID = :roleID");

        Query query = getSession().createQuery(hql.toString());
        query.setParameter("roleID", roleID);

        List list = query.list();
        UserVO userVO = null;
        UserPO userPO = null;
        for (Iterator iter = (Iterator) list.iterator(); iter.hasNext();) {
            userPO = (UserPO) iter.next();
            userVO = new UserVO();
            BeanUtils.copyProperties(userPO, userVO);
            userList.add(userVO);
        }
        return userList;

    }

    /**
     * 
     *  获取角色在该功能上的权限
     *  @param roleID
     *  @param funcTreeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月23日 上午5:38:41
     *  @lastModified       
     *  @history
     */
    public RoleFuncTreeVO getRoleFuncTree(String roleID, String funcTreeID) {
        RoleFuncTreeVO roleFuncTreeVO = null;
        Criteria criteria = getSession().createCriteria(RoleFuncTreePO.class);
        criteria.add(Restrictions.eq("roleID", roleID));
        criteria.add(Restrictions.eq("funcTreeID", funcTreeID));
        RoleFuncTreePO roleFuncTreePO = (RoleFuncTreePO) criteria.uniqueResult();
        if (roleFuncTreePO != null) {
            roleFuncTreeVO = new RoleFuncTreeVO();
            BeanUtils.copyProperties(roleFuncTreePO, roleFuncTreeVO);
        }
        return roleFuncTreeVO;
    }

    /**
     * 
     *  保存角色在该功能上的权限
     *  @param roleFuncTreeVO
     *  @author Administrator
     *  @created 2014年1月23日 上午5:54:30
     *  @lastModified       
     *  @history
     */
    public RoleFuncTreeVO saveRoleFuncTree(RoleFuncTreeVO roleFuncTreeVO) {
        RoleFuncTreePO roleFuncTreePO = new RoleFuncTreePO();
        BeanUtils.copyProperties(roleFuncTreeVO, roleFuncTreePO);
        roleFuncTreePO = super.save(RoleFuncTreePO.class, roleFuncTreePO);
        roleFuncTreeVO.setId(roleFuncTreePO.getId());
        return roleFuncTreeVO;
    }

}