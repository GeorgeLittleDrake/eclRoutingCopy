package com.jk.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Connector implements Comparable{
    private Node[] nodes;
    private double distance;
    private List<String> labelPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connector)) return false;
        Connector connector = (Connector) o;
        return Double.compare(connector.getDistance(), getDistance()) == 0 &&
                Board.equalNodeArray(this.getNodes(), connector.getNodes())
                /*&&
                getLabelPath().equals(connector.getLabelPath())*/;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getDistance()/*, getLabelPath()*/);
        result = 31 * result + Objects.hash(this.getNodes()) + Objects.hash(Board.reverseNodes(this.getNodes()));
                //Objects.hashCode( this.getNodes()[0]) + Objects.hashCode(getNodes()[1]);
        return result;
    }

    public Connector(Node node0, Node node1, double distance, List<String> labelPath) {
        super();
        //this( node0, Node node1,  distance);
        this.nodes = new Node[2];
        this.nodes[0] = node0;
        this.nodes[1] = node1;
        this.distance = distance;
        this.labelPath = labelPath;
        //System.out.println("initialization: "+this.getLabelPath());
    }

    public List<String> getLabelPath() {
        return labelPath;
    }

    public Connector(Node node0, Node node1, double distance) {
        this.distance = distance;
        this.nodes = new Node[2];
        this.nodes[0] = node0;
        this.nodes[1] = node1;
        this.labelPath = new ArrayList<>();
    }

    public Node[] getNodes() {
        return nodes;
    }

    public double getDistance() {
        return distance;
    }

    public void updateDistance(double value, String label){
        this.distance = value;
        //if (! labelPath.contains(label))
        this.labelPath.add(label);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        Connector connector = (Connector) o;
        return (int) (this.getDistance() - connector.getDistance() );
                //Double.valueOf(connector.getDistance()).compareTo( Double.valueOf( this.getDistance()) );
    }
}
