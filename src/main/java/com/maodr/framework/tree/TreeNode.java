package com.maodr.framework.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String id;

    private String name;

    private boolean selected;

    private int level;

    private List<TreeNode> children = new ArrayList<TreeNode>();

    private TreeNode() {

    }

    public TreeNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChildren(TreeNode treeNode) {
        this.children.add(treeNode);
    }

}