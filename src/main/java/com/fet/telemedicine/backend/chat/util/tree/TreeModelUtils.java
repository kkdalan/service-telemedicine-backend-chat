package com.fet.telemedicine.backend.chat.util.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeModelUtils {

    public static <T> List<TreeModel<T>> flattenModel(TreeModel<T> root) {
	List<TreeModel<T>> result = flatten(root, new ArrayList<TreeModel<T>>());
	return result;
    }

    private static <T> List<TreeModel<T>> flatten(TreeModel<T> node, List<TreeModel<T>> flatList) {
	if (node != null) {
	    flatList.add(node);
	    if (node.hasChildren()) {
		List<TreeModel<T>> children = node.getChildren();
		for (TreeModel<T> child : children) {
		    if (child.getChildren() != null) {
			flatten(child, flatList);
		    }
		}
	    }
	}
	return flatList;
    }

    public static <T> String getTreeDiagram(TreeModel<T> root) {
	if (root.getValue() == null) {
	    return "";
	}

	StringBuilder sb = new StringBuilder();
	sb.append(root.getValue());
	sb.append("\n");

	String pointerRight = "└──";
	String pointerLeft = (root.hasChildren()) ? "├──" : "└──";

	List<TreeModel<T>> children = root.getChildren();
	for (int i = 0; i < children.size(); i++) {
	    TreeModel<T> childNode = (TreeModel<T>) children.get(i);
	    if (i < children.size() - 1) {
		traversePreOrder(sb, "", pointerLeft, childNode, root.hasChildren());
	    } else {
		traversePreOrder(sb, "", pointerRight, childNode, false);
	    }
	}
	return sb.toString();
    }

    private static <T> void traversePreOrder(StringBuilder sb, String padding, String pointer, TreeModel<T> node,
	    boolean hasRightSibling) {
	if (node.getValue() != null) {
	    sb.append(padding);
	    sb.append(pointer);
	    sb.append(node.getValue());
	    sb.append("\n");

	    StringBuilder paddingBuilder = new StringBuilder(padding);
	    if (hasRightSibling) {
		paddingBuilder.append("│  ");
	    } else {
		paddingBuilder.append("   ");
	    }

	    String paddingForAll = paddingBuilder.toString();
	    String pointerRight = "└──";
	    String pointerLeft = (node.hasChildren()) ? "├──" : "└──";

	    List<TreeModel<T>> children = node.getChildren();
	    for (int i = 0; i < children.size(); i++) {
		TreeModel<T> childNode = (TreeModel<T>) children.get(i);
		if (i < children.size() - 1) {
		    traversePreOrder(sb, paddingForAll, pointerLeft, childNode, node.hasChildren());
		} else {
		    traversePreOrder(sb, paddingForAll, pointerRight, childNode, false);
		}
	    }
	}
    }

}
