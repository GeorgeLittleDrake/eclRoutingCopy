package com.jk.algorithms;

public class Neighbour {
    private GraphNode graphNode;
    private Distance distance;

    public Neighbour(GraphNode graphNode, Distance distance) {
        this.graphNode = graphNode;
        this.distance = distance;
    }

    public GraphNode getGraphNode() {
        return graphNode;
    }

    public Distance getDistance() {
        return distance;
    }
}
