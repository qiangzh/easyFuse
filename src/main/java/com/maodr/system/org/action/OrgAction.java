package com.maodr.system.org.action;

import java.util.ArrayList;
import java.util.List;

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
        // 保存组织机构信息
        orgService.saveOrg(org);

        // 设置选中节点
        String selectNodeID = org.getParentID();
        if (org.getId() != null && !"".equals(org.getId())) {
            selectNodeID = org.getId();
        }
        OrgVO selectOrgVO = orgService.getOrg(selectNodeID);
        treeNode = new TreeNode(selectOrgVO.getId(), selectOrgVO.getCode(), selectOrgVO.getName());
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
        OrgVO deleteOrgVO = orgService.getOrg(org.getId());

        // 删除组织结构
        orgService.deleteOrg(org.getId());

        // 设置删除后选中节点
        OrgVO selectOrgVO = orgService.getOrg(deleteOrgVO.getParentID());
        treeNode = new TreeNode(selectOrgVO.getId(), selectOrgVO.getCode(), selectOrgVO.getName());

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
            treeNode = orgService.saveOrGetRootOrg();
        }
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
        org = orgService.getOrg(org.getId());
        return "addOrg";
    }

    /**
     * 
     *  查看组织机构信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String viewOrg() {
        org = orgService.getOrg(treeNode.getId());
        return "viewOrg";
    }

    /**
     * 
     *  获取组织机构
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:54:12
     *  @lastModified       
     *  @history
     */
    public TreeNode getTreeRootNode() {
        TreeNode treeNode = orgService.saveOrGetRootOrg();
        List<TreeNode> childrenList = this.getChildrenNode(treeNode.getId());
        treeNode.setChildren(childrenList);
        return treeNode;
    }

    /**
     * 
     *  递归获取子节点
     *  @param nodeID
     *  @return
     *  @author Administrator
     *  @created 2014年3月2日 上午9:56:53
     *  @lastModified       
     *  @history
     */
    public List<TreeNode> getChildrenNode(String nodeID) {
        List<TreeNode> childrenList = new ArrayList<TreeNode>();
        List<OrgVO> subOrgList = orgService.listSubOrgs(nodeID);
        for (OrgVO orgVO : subOrgList) {
            TreeNode subTreeNode = new TreeNode(orgVO.getId(), orgVO.getCode(), orgVO.getName());
            childrenList.add(subTreeNode);
            List<TreeNode> subTreeChildrenList = this.getChildrenNode(subTreeNode.getId());
            subTreeNode.setChildren(subTreeChildrenList);
        }
        return childrenList;
    }

    /**
     * 
     *  获取子节点列表
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:54:33
     *  @lastModified       
     *  @history
     */

    public String listSubOrgs() {
        if (!"0".equals(treeNode.getId())) {
            OrgVO orgVO = orgService.getOrg(treeNode.getId());
            treeNode = new TreeNode(orgVO.getId(), orgVO.getCode(), orgVO.getName());
        }
        else {
            treeNode = orgService.saveOrGetRootOrg();
        }
        orgs = orgService.listSubOrgs(treeNode.getId());
        return "listSubOrgs";
    }
}
