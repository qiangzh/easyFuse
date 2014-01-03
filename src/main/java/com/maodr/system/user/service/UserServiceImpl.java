package com.maodr.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maodr.system.user.dao.UserDao;
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
public class UserServiceImpl implements UserService,UserDetailsService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String saveUser(UserVO userVO) {
        return userDao.saveOrUpdateUser(userVO);
    }

    /**
     * 
     *  显示用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<UserVO> listUsers() {
        List<UserPO> poList = userDao.getAllDistinct();
        List<UserVO> voList = new ArrayList();
        UserVO userVO;
        for (UserPO userPO : poList) {
            userVO = new UserVO();
            BeanUtils.copyProperties(userPO, userVO);
            voList.add(userVO);
        }
        return voList;
    }
    
    /**
     * 
     *  获取用户信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public UserVO getUser(String id){
        UserPO userPO = userDao.get(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userPO, userVO);
        return userVO;
        
    }

    /**
     *  登录
     *  @param username
     *  @return
     *  @throws UsernameNotFoundException
     *  @author Administrator
     *  @created 2014年1月3日 上午5:04:25
     *  @lastModified      
     *  @history
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
    
    /**
     * 
     *  获取用户信息
     *  @param remoteUser
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 下午9:07:56
     *  @lastModified       
     *  @history
     */
    public UserVO getUserByUsername(String username){
        return (UserVO) userDao.loadUserByUsername(username);
        
    }

}
