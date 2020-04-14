package com.jk.algorithms;

import java.util.Set;

public class GraphNode extends Node {

    private Set<Neighbour> neighbours;

    public GraphNode(String nodeName) {
        super(nodeName);
    }

    public GraphNode(String nodeName, Set<Neighbour> neighbours) {
        super(nodeName);
        this.neighbours = neighbours;
    }

    public Set<Neighbour> getNeighbours() {
        return neighbours;
    }

    public Distance findDistanceToGraphnode( GraphNode anotherNode){
        for (var nb : this.getNeighbours()) {
            if (nb.getGraphNode().equals(anotherNode))
                return nb.getDistance();
        }
        return null;
    }

    public Neighbour findNeighbour( GraphNode anotherNode){
        for (var nb : this.getNeighbours()) {
            if (nb.getGraphNode().equals(anotherNode))
                return nb;
        }
        return null;
    }
}
