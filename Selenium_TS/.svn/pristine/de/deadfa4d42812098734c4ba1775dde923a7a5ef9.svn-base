package com.netdimen.dao;

import java.util.ArrayList;
import java.util.List;


public class TreeNode<T> {

	private static final String EMPTY_STRING = "";

	private TreeNode<T> parentNode = null;

	private List<TreeNode<T>> childNodes = null;

	private T nodeObject;

	public TreeNode(T nodeObject) {

		this(nodeObject, false);
	}

	public TreeNode(T nodeObject, boolean collapsed) {

		this.parentNode = null;
		this.childNodes = new ArrayList<TreeNode<T>>();
		this.nodeObject = nodeObject;

	}

	public T getNodeObject() {

		return nodeObject;
	}

	public void setNodeObject(T nodeObject) {

		this.nodeObject = nodeObject;
	}

	public TreeNode<T> getParentNode() {

		return parentNode;
	}

	public List<TreeNode<T>> getChildNodes() {

		return childNodes;
	}

	public void remove() {

		if (parentNode != null) {
			parentNode.removeChild(this);
		}
	}

	public void addChild(TreeNode<T> childNode) {

		childNode.parentNode = this;
		if (!childNodes.contains(childNode)) {
			childNodes.add(childNode);
		}
	}

	public void removeChild(TreeNode<T> childNode) {

		if (childNodes.contains(childNode)) {
			childNodes.remove(childNode);
		}
	}

	public TreeNode<T> findTreeNode(T targetNodeObject, boolean searchRecursively) {

		if (nodeObject != null && nodeObject.equals(targetNodeObject)) {
			return this;
		}

		for (TreeNode<T> currentNode : childNodes) {
			if (currentNode.nodeObject != null && currentNode.nodeObject.equals(targetNodeObject)) {
				return currentNode;
			}
			if (searchRecursively) {
				TreeNode<T> result = currentNode.findTreeNode(targetNodeObject, searchRecursively);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	@Override
	public int hashCode() {

		if (nodeObject == null) {
			return EMPTY_STRING.hashCode();
		}
		return nodeObject.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof TreeNode<?>)) {
			return false;
		}
		return this.nodeObject.equals(((TreeNode<?>) obj).nodeObject);
	}

}
