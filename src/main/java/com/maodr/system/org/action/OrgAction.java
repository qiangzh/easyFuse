package com.maodr.system.org.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.framework.tree.TreeNode;
import com.maodr.system.org.service.OrgService;
import com.maodr.system.org.vo.OrgVO;

/**
 * 
 *  组织机构Action
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class OrgAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private OrgService orgService = (OrgService) this.getBean("orgService");

    private OrgVO org; // 组织机构

    private List<OrgVO> orgs; // 组织结构列表

    private TreeNode treeNode; // 组织机构树节点

    public OrgVO getOrg() {
        return org;
    }

    public void setOrg(OrgVO org) {
        this.org = org;
    }

    public List<OrgVO> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrgVO> orgs) {
        this.orgs = orgs;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    /**
     * 
     *  添加组织机构
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveOrg() {
        org.setParentID(treeNode.getId());
        orgService.saveOrg(org);
        return "reflushListOrgs";
    }

    /**
     * 
     *  删除组织机构
     *  @return
     *  @author Administrator
     *  @created 2014年1月13日 下午9:20:03
     *  @lastModified       
     *  @history
     */
    public String deleteOrg() {
        org.setParentID(treeNode.getId());
        orgService.deleteOrg(org.getId());
        return "reflushListOrgs";
    }

    /**
     * 
     *  显示组织机构
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String listOrgs() {
        if (treeNode == null) {
            treeNode = new TreeNode("0", "组织机构");
        }
        orgs = orgService.listOrgs();
        return "listOrgs";
    }

    /**
     * 
     *  添加组织机构
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String addOrg() {
        return "addOrg";
    }

    /**
     * 
     *  编辑组织机构信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String editOrg() {
        HttpServletRequest request = getRequest();
        String id = request.getParameter("id");
        org = orgService.getOrg(id);
        return "addOrg";
    }

    /**
     * 
     *  获取组织机构树
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:54:12
     *  @lastModified       
     *  @history
     */
    public TreeNode getTreeRootNode() {
        TreeNode treeNode = new TreeNode("0", "组织机构");
        List<TreeNode> childrenList = this.getChildrenNode(treeNode.getId());
        treeNode.setChildren(childrenList);
        return treeNode;
    }

    public List<TreeNode> getChildrenNode(String nodeID) {
        List<TreeNode> childrenList = new ArrayList<TreeNode>();
        List<OrgVO> subOrgList = orgService.listSubOrgs(nodeID);
        for (OrgVO orgVO : subOrgList) {
            TreeNode subTreeNode = new TreeNode(orgVO.getId(), orgVO.getName());
            childrenList.add(subTreeNode);
            List<TreeNode> subTreeChildrenList = this.getChildrenNode(subTreeNode.getId());
            subTreeNode.setChildren(subTreeChildrenList);
        }
        return childrenList;
    }

    /**
     * 
     *  获取节点列表
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:54:33
     *  @lastModified       
     *  @history
     */

    public String listSubOrgs() {
        if (!"0".equals(treeNode.getId())) {
            OrgVO orgVO = orgService.getOrg(treeNode.getId());
            treeNode = new TreeNode(orgVO.getId(), orgVO.getName());
        }
        else {
            treeNode = new TreeNode("0", "组织机构");
        }
        orgs = orgService.listSubOrgs(treeNode.getId());
        return "listSubOrgs";
    }
}
