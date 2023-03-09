package com.poonam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
	String nodeName;
	Node parent;
	List<Node> children;

	public Node(String nodeName, Node parent) {
		this.nodeName = nodeName;
		this.parent = parent;
		children = new ArrayList<Node>();
	}

	public String getNodeName() {
		return nodeName;
	}

	public Node getParent() {
		return parent;
	}

	public void addChildren(Node childNode) {
		children.add(childNode);
	}

	public List<Node> getChildrens() {
		return children;
	}
}
