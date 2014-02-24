package com.maodr.framework.tree;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    private String treeName;

    private TreeNode rootNode;

    private final LinkedHashMap<String, TreeNode> nodes = new LinkedHashMap<String, TreeNode>();

    public Tree(String treeName, TreeNode rootNode) {
        this.treeName = treeName;
        this.rootNode = rootNode;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode findNode(String id) {
        return (TreeNode) nodes.get(id);
    }

    public TreeNode addNode(TreeNode node) {
        return (TreeNode) nodes.put(node.getId(), node);
    }

    public TreeNode removeNode(TreeNode node) {
        return (TreeNode) nodes.remove(node.getId());
    }
    
    public TreeNode[] getChildren(){
        TreeNode[] treeNodes=null;
        if(!nodes.isEmpty()){
            treeNodes = (TreeNode[]) nodes.values().toArray();
            
        }else{
            treeNodes =  new TreeNode[0];
            
        }
        return treeNodes;

        
    }

}