package com.maodr.system.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maodr.base.dao.BaseDaoImpl;
import com.maodr.system.user.model.UserPO;
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
    public UserDetails loadUserByUsername(String username) {
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
}
