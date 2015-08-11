package com.netdimen.dao;

import java.util.List;

import com.google.common.collect.Lists;

public class TreeNode<T> {

	private static final String EMPTY_STRING = "";

	private TreeNode<T> parentNode = null;

	private List<TreeNode<T>> childNodes = null;

	private T nodeObject;

	public TreeNode(final T nodeObject) {

		this(nodeObject, false);
	}

	public TreeNode(final T nodeObject, final boolean collapsed) {

		this.parentNode = null;
		this.childNodes = Lists.newArrayList();
		this.nodeObject = nodeObject;

	}

	public T getNodeObject() {

		return nodeObject;
	}

	public void setNodeObject(final T nodeObject) {

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

	public void addChild(final TreeNode<T> childNode) {

		childNode.parentNode = this;
		if (!childNodes.contains(childNode)) {
			childNodes.add(childNode);
		}
	}

	public void removeChild(final TreeNode<T> childNode) {

		if (childNodes.contains(childNode)) {
			childNodes.remove(childNode);
		}
	}

	public TreeNode<T> findTreeNode(final T targetNodeObject,
			final boolean searchRecursively) {

		if (nodeObject != null && nodeObject.equals(targetNodeObject)) {
			return this;
		}

		for (final TreeNode<T> currentNode : childNodes) {
			if (currentNode.nodeObject != null
					&& currentNode.nodeObject.equals(targetNodeObject)) {
				return currentNode;
			}
			if (searchRecursively) {
				final TreeNode<T> result = currentNode.findTreeNode(targetNodeObject,
						searchRecursively);
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
	public boolean equals(final Object obj) {

		if (!(obj instanceof TreeNode<?>)) {
			return false;
		}
		return this.nodeObject.equals(((TreeNode<?>) obj).nodeObject);
	}

}
