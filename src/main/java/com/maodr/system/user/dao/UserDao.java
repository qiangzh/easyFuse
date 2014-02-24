package com.maodr.system.user.dao;

import java.util.List;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.UserPO;
import com.maodr.system.role.vo.RoleVO;
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
    public UserVO loadUserByUsername(String username);

    /**
     * 
     *  获取用户信息
     *  @param userID
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午1:58:27
     *  @lastModified       
     *  @history
     */
    public UserVO getUser(String userID);

    /**
     * 
     *  删除用户分配的角色
     *  @param userID
     *  @author Administrator
     *  @created 2014年1月19日 下午1:32:29
     *  @lastModified       
     *  @history
     */
    public void deleteUserRoleByUserID(String userID);

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
    public void saveUserRole(String userID, List<RoleVO> roleList);

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
    public List<RoleVO> getUserRole(String userID);
    
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
    public List<FuncTreeVO> listUserFuncTrees(String userName);

}
