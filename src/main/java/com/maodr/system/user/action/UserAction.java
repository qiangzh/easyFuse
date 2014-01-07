package com.maodr.system.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.system.user.service.UserService;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  用户Action
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class UserAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private UserVO user;

    private List<UserVO> users;

    private UserService userService;

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 
     *  添加用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveUser() {
        userService.saveUser(user);
        return "reflushListUsers";
    }

    /**
     * 
     *  显示用户
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String listUsers() {
        users = userService.listUsers();
        return "listUsers";
    }

    /**
     * 
     *  显示在线用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:40:35
     *  @lastModified       
     *  @history
     */
    public String listActiveUsers() {
        return "listActiveUsers";

    }

    /**
     * 
     *  添加用户
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String addUser() {
        return "addUser";
    }

    /**
     * 
     *  编辑用户信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String editUser() {
        HttpServletRequest request = getRequest();
        String id = request.getParameter("id");
        user = userService.getUser(id);
        user.setConfirmPassword(user.getPassword());
        return "addUser";
    }

    /**
     * 
     *  登录用户修改信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 下午9:33:10
     *  @lastModified       
     *  @history
     */
    public String editUserProfile() {
        HttpServletRequest request = getRequest();
        user = userService.getUserByUsername(request.getRemoteUser());
        user.setConfirmPassword(user.getPassword());
        return "editUserProfile";
    }

    /**
     * 
     *  登录用户保存修改信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 下午9:33:37
     *  @lastModified       
     *  @history
     */
    public String saveUserProfile() {
        userService.saveUser(user);
        return "cancel";
    }

    /**
     * 
     *  注册用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveSignup() {
        userService.saveUser(user);
        return SUCCESS;
    }
}
