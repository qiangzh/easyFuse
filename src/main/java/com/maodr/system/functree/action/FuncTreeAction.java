package com.maodr.system.functree.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.framework.tree.TreeNode;
import com.maodr.system.functree.service.FuncTreeService;
import com.maodr.system.functree.vo.FuncTreeVO;

/**
 * 
 *  功能树Action
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class FuncTreeAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private FuncTreeService funcTreeService = (FuncTreeService) this.getBean("funcTreeService");

    private FuncTreeVO funcTree;

    private List<FuncTreeVO> funcTrees; //功能树列表

    private TreeNode treeNode;

    public FuncTreeVO getFuncTree() {
        return funcTree;
    }

    public void setFuncTree(FuncTreeVO funcTree) {
        this.funcTree = funcTree;
    }

    public List<FuncTreeVO> getFuncTrees() {
        return funcTrees;
    }

    public void setFuncTrees(List<FuncTreeVO> funcTrees) {
        this.funcTrees = funcTrees;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }
    
    /**
     * 
     *  添加功能树
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:38:51
     *  @lastModified       
     *  @history
     */
    public String saveFuncTree() {
        funcTree.setParentID(treeNode.getId());
        funcTreeService.saveFuncTree(funcTree);
        return "reflushListFuncTrees";
    }

    /**
     * 
     *  显示功能树
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String listFuncTrees() {
        if(treeNode==null){
            treeNode = new TreeNode("0", "0", "功能树");            
        }
        funcTrees = funcTreeService.listFuncTrees();
        return "listFuncTrees";
    }

    /**
     * 
     *  添加功能树
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String addFuncTree() {
        return "addFuncTree";
    }

    /**
     * 
     *  编辑功能树信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String editFuncTree() {        
        funcTree = funcTreeService.getFuncTree(funcTree.getId());
        return "addFuncTree";
    }
    

    /**
     * 
     *  删除功能树信息
     *  @author Administrator
     *  @created 2014年1月1日 下午3:39:43
     *  @lastModified       
     *  @history
     */
    public String deleteFuncTree() {
        HttpServletRequest request = getRequest();
        String id = request.getParameter("id");
        funcTreeService.deleteFuncTree(id);
        return "reflushListFuncTrees";
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
        List<TreeNode> childrenList =this.getChildrenNode(treeNode.getId());
        treeNode.setChildren(childrenList);
        return treeNode;
    }
    
    public List<TreeNode> getChildrenNode(String nodeID){
        List<TreeNode> childrenList = new ArrayList<TreeNode>();
        List<FuncTreeVO> subFuncTreeList = funcTreeService.listSubFuncTrees(nodeID);
        for(FuncTreeVO funcTreeVO:subFuncTreeList){
            TreeNode subTreeNode = new TreeNode(funcTreeVO.getId(),funcTreeVO.getCode(), funcTreeVO.getName());
            childrenList.add(subTreeNode); 
            List<TreeNode> subTreeChildrenList =this.getChildrenNode(subTreeNode.getId());
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

    public String listSubFuncTrees() {
        if (!"0".equals(treeNode.getId())) {
            FuncTreeVO funcTreeVO = funcTreeService.getFuncTree(treeNode.getId());
            treeNode = new TreeNode(funcTreeVO.getId(), funcTreeVO.getCode(), funcTreeVO.getName());
            funcTree = funcTreeService.getFuncTree(treeNode.getId());
        }
        else {
            treeNode = new TreeNode("0", "0", "功能树");
            funcTree = new FuncTreeVO();
            funcTree.setId("-1");            
        }
        funcTrees = funcTreeService.listSubFuncTrees(treeNode.getId());
        return "listSubFuncTrees";
    }
}
