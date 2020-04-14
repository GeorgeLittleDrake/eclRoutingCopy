package com.jk.algorithms;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private Set<Node> nodes;
    private Set<Node> visited;
    private Set<Neighbour> border;
    private GraphNode sourceNode;

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Neighbour> getBorder() {
        return border;
    }

    public void setBorder(Set<Neighbour> border) {
        this.border = border;
    }

    public GraphNode getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(GraphNode sourceNode) {
        this.sourceNode = sourceNode;
    }

    public Board() {
        nodes = new HashSet<>();
        visited = new HashSet<>();
    }

    public Board(GraphNode sourceNode) {
        this();
        this.sourceNode = sourceNode;
    }

    private void initBorder(){
        this.border = sourceNode.getNeighbours();
    }

    private Neighbour findNearestNode(Set<Neighbour> nodeSet, GraphNode graphNode){
        return null;
    }

    public static Distance findDistanceToGraphnode(GraphNode neighbourNode, GraphNode anotherNode){
        for (var nb : neighbourNode.getNeighbours()) {
            if (nb.getGraphNode().equals(anotherNode))
                return nb.getDistance();
        }
        return null;
    }

    public static Neighbour findNeighbour(GraphNode neighbourNode, GraphNode anotherNode){
        for (var nb : neighbourNode.getNeighbours()) {
            if (nb.getGraphNode().equals(anotherNode))
                return nb;
        }
        return null;
    }

    public void kruskal( GraphNode graphNode){
        while (! border.isEmpty()) {
            Neighbour nearest = findNearestNode(border, graphNode);
            border.remove(nearest);
            for (var neighbour : nearest.getGraphNode().getNeighbours()) {
                if (! visited.contains( (Node) (neighbour.getGraphNode()) ) ){
                    if( border.contains( neighbour ) ) {
                        double indirect = neighbour.getDistance().getValue() + nearest.getDistance().getValue();  //neighbour to nearest + nearest to graphnode
                        Neighbour sourceNodeNeighbour = neighbour.getGraphNode().findNeighbour(graphNode);
                        double direct = sourceNodeNeighbour.getDistance().getValue();
                        if (indirect < direct) {
                            neighbour.getDistance().updateDistance(indirect, nearest.getGraphNode().getNodeName());
                        }
                        //double minimalDistance = ( neighbour.getDistance() > )
                    } else {
                        border.add(neighbour);
                    }

                }
            }
        }
    }
}
