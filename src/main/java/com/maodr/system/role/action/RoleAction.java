package com.maodr.system.role.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.framework.model.LabelValue;
import com.maodr.framework.tree.TreeNode;
import com.maodr.system.functree.service.FuncTreeService;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.role.service.RoleService;
import com.maodr.system.role.vo.RoleFuncTreeVO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.vo.UserVO;

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

    private RoleService roleService = (RoleService) this.getBean("roleService");

    private FuncTreeService funcTreeService = (FuncTreeService) this.getBean("funcTreeService");

    private RoleVO role; // 角色

    private List<UserVO> users; // 用户列表

    private List<RoleVO> roles; // 角色列表

    private List<FuncTreeVO> funcTrees; // 功能树

    private List<LabelValue> chkRolelist = new ArrayList<LabelValue>(); // 权限选中框

    private RoleFuncTreeVO roleFuncTreeVO;

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

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }

    public List<FuncTreeVO> getFuncTrees() {
        return funcTrees;
    }

    public void setFuncTrees(List<FuncTreeVO> funcTrees) {
        this.funcTrees = funcTrees;
    }

    public List<LabelValue> getChkRolelist() {
        return chkRolelist;
    }

    public void setChkRolelist(List<LabelValue> chkRolelist) {
        this.chkRolelist = chkRolelist;
    }

    public RoleFuncTreeVO getRoleFuncTreeVO() {
        return roleFuncTreeVO;
    }

    public void setRoleFuncTreeVO(RoleFuncTreeVO roleFuncTreeVO) {
        this.roleFuncTreeVO = roleFuncTreeVO;
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
        role = roleService.getRole(role.getId());
        return "addRole";
    }
    
    /**
     * 
     *  删除角色
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午6:42:04
     *  @lastModified       
     *  @history
     */
    public String deleteRole() {
        roleService.deleteRole(role.getId());
        return "reflushListRoles";
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

    /**
     * 
     *  查看已分配用户
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午2:50:53
     *  @lastModified       
     *  @history
     */
    public String listUserOfRole() {
        users = roleService.listUserOfRole(role.getId());
        return "listUserOfRole";

    }

    /**
     * 
     *  显示角色权限设置页面
     *  @return
     *  @author Administrator
     *  @created 2014年1月20日 上午5:29:17
     *  @lastModified       
     *  @history
     */
    public String listRoleFuncTree() {
        HttpServletRequest request = getRequest();
        String roleID = request.getParameter("roleFuncTreeVO.roleID");
        roleFuncTreeVO = new RoleFuncTreeVO();
        roleFuncTreeVO.setRoleID(roleID);
        return "listRoleFuncTree";
    }

    /**
     * 
     *  角色权限设置页面
     *  @return
     *  @author Administrator
     *  @created 2014年1月20日 上午5:29:17
     *  @lastModified       
     *  @history
     */
    public String setRolePermission() {
        chkRolelist = new ArrayList<LabelValue>();
        chkRolelist.add(new LabelValue("0", "无权限"));
        chkRolelist.add(new LabelValue("1", "有权限"));

        HttpServletRequest request = getRequest();
        String roleID = request.getParameter("roleFuncTreeVO.roleID");
        String funcTreeID = request.getParameter("roleFuncTreeVO.funcTreeID");
        roleFuncTreeVO = roleService.getRoleFuncTree(roleID, funcTreeID);
        if (roleFuncTreeVO == null) {
            roleFuncTreeVO = new RoleFuncTreeVO();
            roleFuncTreeVO.setRoleID(roleID);
            roleFuncTreeVO.setFuncTreeID(funcTreeID);
            roleFuncTreeVO.setHasPermission("0");
        }
        else {
            roleFuncTreeVO.setHasPermission("1");
        }
        return "setRolePermission";
    }

    /**
     * 
     *  保存角色权限设置
     *  @return
     *  @author Administrator
     *  @created 2014年1月22日 上午4:26:35
     *  @lastModified       
     *  @history
     */
    public String saveRolePermission() {
        roleFuncTreeVO = roleService.saveRoleFuncTree(roleFuncTreeVO);
        return "listRoleFuncTree";
    }

    /**
     * 
     *  获取功能树树
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:54:12
     *  @lastModified       
     *  @history
     */
    public TreeNode getTreeRootNode() {
        TreeNode treeNode = new TreeNode("0", "0", "功能树");
        List<TreeNode> childrenList = this.getChildrenNode(treeNode.getId());
        treeNode.setChildren(childrenList);
        return treeNode;
    }

    /**
     * 
     *  获取子节点
     *  @param nodeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月22日 上午4:14:29
     *  @lastModified       
     *  @history
     */
    public List<TreeNode> getChildrenNode(String nodeID) {
        List<TreeNode> childrenList = new ArrayList<TreeNode>();
        List<FuncTreeVO> subFuncTreeList = funcTreeService.listSubFuncTrees(nodeID);
        for (FuncTreeVO funcTreeVO : subFuncTreeList) {
            TreeNode subTreeNode = new TreeNode(funcTreeVO.getId(), funcTreeVO.getCode(), funcTreeVO.getName());
            childrenList.add(subTreeNode);
            List<TreeNode> subTreeChildrenList = this.getChildrenNode(subTreeNode.getId());
            subTreeNode.setChildren(subTreeChildrenList);
        }
        return childrenList;
    }
}
