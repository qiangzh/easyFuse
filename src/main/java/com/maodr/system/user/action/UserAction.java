package com.maodr.system.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.framework.exception.BusinessException;
import com.maodr.system.role.service.RoleService;
import com.maodr.system.role.vo.RoleVO;
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

    private UserService userService = (UserService) this.getBean("userService");

    private RoleService roleService = (RoleService) this.getBean("roleService");

    private UserVO user; // 用户信息

    private List<UserVO> users; // 用户列表

    private String[] leftRoles; // 左侧角色列表返回值

    private String[] rightRoles; // 右侧角色列表返回值

    private List<RoleVO> leftRoleList = new ArrayList<RoleVO>(); // 左侧角色列表

    private List<RoleVO> rightRoleList = new ArrayList<RoleVO>(); // 右侧角色列表

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

    public List<RoleVO> getLeftRoleList() {
        return leftRoleList;
    }

    public void setLeftRoleList(List<RoleVO> leftRoleList) {
        this.leftRoleList = leftRoleList;
    }

    public List<RoleVO> getRightRoleList() {
        return rightRoleList;
    }

    public void setRightRoleList(List<RoleVO> rightRoleList) {
        this.rightRoleList = rightRoleList;
    }

    public String[] getLeftRoles() {
        return leftRoles;
    }

    public void setLeftRoles(String[] leftRoles) {
        this.leftRoles = leftRoles;
    }

    public String[] getRightRoles() {
        return rightRoles;
    }

    public void setRightRoles(String[] rightRoles) {
        this.rightRoles = rightRoles;
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
        // 封装UserVO数据
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        RoleVO roleVO = null;
        for (String roleID : rightRoles) {
            roleVO = new RoleVO();
            roleVO.setId(roleID);
            roleList.add(roleVO);
        }
        user.setRoleList(roleList);

        // 保存数据
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
        leftRoleList = roleService.listRoles();
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

        // 右侧角色列表
        rightRoleList = user.getRoleList();

        // 左侧角色列表
        List<RoleVO> allRoleList = roleService.listRoles();
        allRoleList.removeAll(rightRoleList);
        leftRoleList = allRoleList;

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
