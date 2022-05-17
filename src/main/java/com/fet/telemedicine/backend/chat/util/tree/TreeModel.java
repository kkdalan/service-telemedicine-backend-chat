package com.fet.telemedicine.backend.chat.util.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeModel<T> {

    T value;
    List<TreeModel<T>> children = new ArrayList<>();
    TreeModel<T> parent;

    public TreeModel(T value) {
	this.value = value;
    }

    public T getValue() {
	return value;
    }

    public void setValue(T value) {
	this.value = value;
    }

    public void add(TreeModel<T> node) {
	node.setParent(this);
	children.add(node);
    }

    public void remove(TreeModel<T> node) {
	if (hasChildren()) {
	    node.setParent(null);
	    children.remove(node);
	}
	throw new UnsupportedOperationException();
    }

    public TreeModel<T> getChild(int i) {
	return children.get(i);
    }

    public List<TreeModel<T>> getChildren() {
	return children;
    }

    public int totalSize() {
	int size = 1;
	if (hasChildren()) {
	    for (TreeModel<T> child : children) {
		size += child.totalSize();
	    }
	    return size;
	}
	return size;
    }

    public int childrenSize() {
	return children != null ? children.size() : 0;
    }

    public boolean hasChildren() {
	return childrenSize() > 0;
    }

    public void setParent(TreeModel<T> parent) {
	this.parent = parent;
    }

    public TreeModel<T> getParent() {
	return parent;
    }

    public void printTree() {
	System.out.println(TreeModelUtils.getTreeDiagram(this));
    }

}
