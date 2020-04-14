package com.jk.shortestpath;

import java.util.Objects;

public class Node {
    private String nodeName;

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getNodeName().equals(node.getNodeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNodeName());
    }
}
