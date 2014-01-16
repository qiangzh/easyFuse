package com.maodr.system.role.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.system.role.service.RoleService;
import com.maodr.system.role.vo.RoleVO;

/**
 * 
 *  角色Action
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class RoleAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private RoleVO role;

    private List<RoleVO> roles;

    private RoleService roleService;

    public RoleVO getRole() {
        return role;
    }

    public void setRole(RoleVO role) {
        this.role = role;
    }

    public List<RoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVO> roles) {
        this.roles = roles;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 
     *  添加角色
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveRole() {
        roleService.saveRole(role);
        return "reflushListRoles";
    }

    /**
     * 
     *  显示角色
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String listRoles() {
        roles = roleService.listRoles();
        return "listRoles";
    }

    /**
     * 
     *  显示在线角色
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:40:35
     *  @lastModified       
     *  @history
     */
    public String listActiveRoles() {
        return "listActiveRoles";

    }

    /**
     * 
     *  添加角色
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String addRole() {
        return "addRole";
    }

    /**
     * 
     *  编辑角色信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String editRole() {
        HttpServletRequest request = getRequest();
        String id = request.getParameter("id");
        role = roleService.getRole(id);
        return "addRole";
    }

    /**
     * 
     *  登录角色保存修改信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月3日 下午9:33:37
     *  @lastModified       
     *  @history
     */
    public String saveRoleProfile() {
        roleService.saveRole(role);
        return "cancel";
    }

    /**
     * 
     *  注册角色
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveSignup() {
        roleService.saveRole(role);
        return SUCCESS;
    }
}
