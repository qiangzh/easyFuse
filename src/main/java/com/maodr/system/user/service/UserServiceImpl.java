package com.maodr.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.UserPO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.dao.UserDao;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  用户Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String saveUser(UserVO userVO) {
        // 保存或更新User信息
        String userID = userDao.saveOrUpdateUser(userVO);

        // 删除已有角色
        userDao.deleteUserRoleByUserID(userID);

        // 添加新角色
        userDao.saveUserRole(userID, userVO.getRoleList());

        return userID;
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
    public UserVO getUser(String userID) {
        // 获取用户信息
        UserVO userVO = userDao.getUser(userID);

        // 获取用户角色
        List<RoleVO> roleList = userDao.getUserRole(userID);
        userVO.setRoleList(roleList);
        return userVO;

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
    public UserVO getUserByUsername(String username) {
        return (UserVO) userDao.loadUserByUsername(username);
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
        return userDao.listUserFuncTrees(userName);
    }

}
