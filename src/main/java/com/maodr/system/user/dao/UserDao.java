package com.maodr.system.user.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.maodr.base.dao.BaseDao;
import com.maodr.system.user.model.UserPO;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  用户Dao接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface UserDao extends BaseDao<UserPO, String> {

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
    public String saveOrUpdateUser(UserVO userVO);

    /**
     * 
     *  登录
     *  @param username
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 上午5:04:47
     *  @lastModified       
     *  @history
     */
    public UserDetails loadUserByUsername(String username);

}
