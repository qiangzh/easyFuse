package com.maodr.system.user.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maodr.framework.base.dao.BaseDaoImpl;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.FuncTreePO;
import com.maodr.system.model.RolePO;
import com.maodr.system.model.UserPO;
import com.maodr.system.model.UserRolePO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  用户Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class UserDaoImpl extends BaseDaoImpl<UserPO, String> implements UserDao {

    public UserDaoImpl() {
        super(UserPO.class);
    }

    public UserDaoImpl(Class<UserPO> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

    /**
     * 
     *  添加用户
     *  @param userVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateUser(UserVO userVO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userVO, userPO);
        userPO = super.save(userPO);
        return userPO.getId();
    }

    /**
     *  登录
     *  @param username
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 上午5:05:28
     *  @lastModified      
     *  @history
     */
    public UserVO loadUserByUsername(String username) {
        List users = getSession().createCriteria(UserPO.class).add(Restrictions.eq("username", username)).list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        }
        else {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(users.get(0), userVO);
            return userVO;
        }
    }

    /**
     * 
     *  获取用户
     *  @param userID
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午1:58:02
     *  @lastModified       
     *  @history
     */
    public UserVO getUser(String userID) {
        UserPO userPO = super.get(userID);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userPO, userVO);
        return userVO;
    }

    /**
     * 
     *  删除用户分配的角色
     *  @param userID
     *  @author Administrator
     *  @created 2014年1月19日 下午1:32:29
     *  @lastModified       
     *  @history
     */
    public void deleteUserRoleByUserID(String userID) {
        String hql = "delete UserRolePO where userID = :userID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        query.executeUpdate();
    }

    /**
     * 
     *  获取用户分配的角色
     *  @param userID
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午1:59:35
     *  @lastModified       
     *  @history
     */
    public List<RoleVO> getUserRole(String userID) {
        List<RoleVO> roleList = new ArrayList<RoleVO>();

        StringBuilder hql = new StringBuilder(50);
        hql.append("select rolePO from RolePO rolePO,UserRolePO userRolePO");
        hql.append(" where rolePO.id = userRolePO.roleID ");
        hql.append("   and userRolePO.userID = :userID");

        Query query = getSession().createQuery(hql.toString());
        query.setParameter("userID", userID);

        List list = query.list();
        RoleVO roleVO = null;
        RolePO rolePO = null;
        for (Iterator iter = (Iterator) list.iterator(); iter.hasNext();) {
            rolePO = (RolePO) iter.next();
            roleVO = new RoleVO();
            BeanUtils.copyProperties(rolePO, roleVO);
            roleList.add(roleVO);
        }
        return roleList;
    }

    /**
     * 
     *  给用户添加角色
     *  @param userID
     *  @param roleList
     *  @author Administrator
     *  @created 2014年1月19日 下午1:32:49
     *  @lastModified       
     *  @history
     */
    public void saveUserRole(String userID, List<RoleVO> roleList) {
        List<UserRolePO> userRolePOList = new ArrayList<UserRolePO>();
        UserRolePO userRolePO = null;
        for (RoleVO roleVO : roleList) {
            userRolePO = new UserRolePO();

            userRolePO.setUserID(userID);
            userRolePO.setRoleID(roleVO.getId());
            userRolePOList.add(userRolePO);

        }
        super.saveBatch(UserRolePO.class, userRolePOList);
    }

    /**
     * 
     *  获取用户授权的功能菜单
     *  @param treeNodeID
     *  @return
     *  @author Administrator
     *  @created 2014年2月15日 上午8:16:00
     *  @lastModified       
     *  @history
     */
    public List<FuncTreeVO> listUserFuncTrees(String userName) {
        List<FuncTreeVO> voList = new ArrayList<FuncTreeVO>();

        StringBuilder hql = new StringBuilder(50);
        hql.append("select funcTreePO from FuncTreePO funcTreePO,RoleFuncTreePO roleFuncTreePO,UserRolePO userRolePO,UserPO userPO");
        hql.append(" where funcTreePO.id = roleFuncTreePO.funcTreeID");
        hql.append("   and roleFuncTreePO.roleID = userRolePO.roleID");
        hql.append("   and userRolePO.userID = userPO.id");
        hql.append("   and userPO.username = :username");
        hql.append(" order by funcTreePO.sort asc");

        Query query = getSession().createQuery(hql.toString());
        query.setParameter("username", userName);

        List list = query.list();
        FuncTreeVO funcTreeVO = null;
        FuncTreePO funcTreePO = null;
        for (Iterator iter = (Iterator) list.iterator(); iter.hasNext();) {
            funcTreePO = (FuncTreePO) iter.next();
            funcTreeVO = new FuncTreeVO();
            BeanUtils.copyProperties(funcTreePO, funcTreeVO);
            voList.add(funcTreeVO);
        }
        return voList;
    }

}
