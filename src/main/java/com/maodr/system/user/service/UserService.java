package com.maodr.system.user.service;

import java.util.List;

import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  用户Service接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface UserService {

    /**
     * 
     *  保存User
     *  @param userVO
     *  @return
     *  @author Administrator
     *  @created 2013年12月31日 上午4:39:37
     *  @lastModified       
     *  @history
     */
    public String saveUser(UserVO userVO);
    
    /**
     * 
     *  显示用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<UserVO> listUsers();

    /**
     * 
     *  获取用户信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public UserVO getUser(String id);

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
    public UserVO getUserByUsername(String remoteUser);

}
