package com.jk.shortestpath;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.*;

public class Board {
    private List<Connector> connectors;
    //private Set<Node> nodes;
    private Set<Node> visited;
    private Set<Node> border;
    private Node sourceNode;

    public Board(Node sourceNode) {
        this.sourceNode = sourceNode;
        //nodes = new HashSet<>();
        visited = new HashSet<>();
        border = new HashSet<>();
        connectors = new ArrayList<>();
    }

    private static boolean test(Boolean a) {
        return a;
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<Connector> connectors) {
        this.connectors = connectors;
    }

    public Set<Node> getVisited() {
        return visited;
    }

    public void setVisited(Set<Node> visited) {
        this.visited = visited;
    }

    public Set<Node> getBorder() {
        return this.border;
    }

    public void setBorder(Set<Node> border) {
        this.border = border;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(Node sourceNode) {
        this.sourceNode = sourceNode;
    }

    public static boolean containsAny(Node[] nodes, Set<Node> nodeSet) {
        return Stream.of(nodes).map(nodeSet::contains).anyMatch( a -> a /*Board::test*/  );
    }

    private static Connector findConnector(Node node1, Node node2, List<Connector> connectors) {
        List<Connector> cons = new ArrayList<>();
        for (var connector : connectors) {
            Node[] nodes = connector.getNodes();
            if ((node1.equals(nodes[0]) && node2.equals(nodes[1]))
                    ||
            (node2.equals(nodes[0]) && node1.equals(nodes[1])))
                cons.add(connector);
                    //return connector;
        }
        return cons.stream().min(Connector::compareTo).get();
        //return null;
        /*Stream<Connector> con =
                connectors.stream().map(connector -> {
                    Node[] nodes = connector.getNodes();
                    if ((node1.equals(nodes[0]) && node2.equals(nodes[1]))
                            ||
                            (node2.equals(nodes[0]) && node1.equals(nodes[1])))
                        return connector;
                    else return null;
                }).filter(connector -> {
                    if (connector != null) return true;
                    else return false;
                });
        return con.findFirst().get();*/
    }

    public static boolean equalNodeArray(Node[] arr1, Node[] arr2) {
        if (arr1.length != 2 || arr2.length != 2) return false;
        if (((arr1[0].equals(arr2[0])) && (arr1[1].equals(arr2[1])))
                ||
                ((arr1[1].equals(arr2[0])) && (arr1[0].equals(arr2[1]))))
            return true;
        else return false;
    }

    public static void main(String... args) {
        Set<Node> nodeSet = new HashSet<>();
        String[] darr = {"h", "d"};
        String[] sarr = {"a", "b", "c", "d", "e"};
        for (var i = 0; i < 5; i++) {
            nodeSet.add(new Node(sarr[i]));
        }
        Node x = new Node("h");
        Node n = new Node("c");

        List<Connector> connections = new ArrayList<>();
        Node[] ar1 = new Node[2];
        ar1[0] = new Node("c");
        ar1[1] = new Node("f");
        Connector c0 = new Connector(ar1[0], ar1[1], 100d);
        connections.add(c0);
        System.out.println("contains = " + containsAny(ar1, nodeSet));
        System.out.println("contains 2 = " + containsAny(new Node[]{new Node("a"),new Node("y")}, nodeSet));
        Node[] ar2 = new Node[2];
        ar2[0] = new Node("h");
        ar2[1] = new Node("g");
        Connector c1 = new Connector(ar2[0], ar2[1], 150d);
        connections.add(c1);
        System.out.println("not contains = " + containsAny(ar2, nodeSet));
        Node[] ar3 = new Node[2];
        ar3[0] = new Node("h");
        ar3[1] = new Node("c");
        Connector c2 = new Connector(ar3[0], ar3[1], 90d);
        connections.add(c2);
        Node[] ar4 = new Node[2];
        ar4[0] = new Node("f");
        ar4[1] = new Node("c");
        Connector c3 = new Connector(ar4[0], ar4[1], 100d);
        connections.add(c3);
        Connector res0 = findNearestConnector(nodeSet, n, connections);
        System.out.println("Result new " + res0.getNodes()[0].getNodeName() + " " + res0.getNodes()[1].getNodeName() + " " + res0.getDistance());
        Connector res = findNearestConnectorAlt(nodeSet, n, connections);
        System.out.println("Result alt " + res.getNodes()[0].getNodeName() + " " + res.getNodes()[1].getNodeName() + " " + res.getDistance());
        System.out.println("nie " + someEquals(ar2, n));
        System.out.println("tak " + someEquals(ar1, n));
        System.out.println("tak " + someEquals(ar3, n));
        Set<Node> neighbours = eachNeighbour(n, connections);
        System.out.println("Neighbours: ");
        for (var neighbor : neighbours) {
            System.out.print(neighbor.getNodeName() + " ");
        }
        System.out.println();
        System.out.println("NOWE !!!");
        Connector connector = findConnector(new Node("h"), new Node("g"), connections);
        System.out.print("konektor " + connector.getNodes()[0].getNodeName() + " " + connector.getNodes()[1].getNodeName() + " " + connector.getDistance());
        System.out.println();
        System.out.println("true: " + equalNodeArray(ar1, ar4));
        System.out.println("false: " + equalNodeArray(ar1, ar3));
        System.out.println(ar1[0].getNodeName() + " " + reverseNodes(ar1)[1].getNodeName());

        Node nd0 = new Node("c");
        Node nd1 = new Node("c");

        System.out.println("TRUE: "+nd0.equals(nd1)  + " "+nd1.equals(nd0));
    }

    public static Node[] reverseNodes(Node[] arr) {
        var res = new Node[arr.length];
        int j = 0;
        for (var i = arr.length - 1; i >= 0; i--) {
            res[j++] = arr[i];
        }
        return res;
    }


    public static boolean someEquals(Node[] nodearr, Node n) {
        return Stream.of(nodearr).map(node -> {
            return node.equals(n);
        }).anyMatch(a -> a);
    }

    private static Node getTheOther(Connector connector, Node node) {
        if (!connector.getNodes()[0].equals(node) && !connector.getNodes()[1].equals(node)) return null;
        return connector.getNodes()[0].equals(node) ? connector.getNodes()[1] : connector.getNodes()[0];
    }

    public static Connector findNearestConnectorAlt(Set<Node> nodeSet, Node node, List<Connector> connectors){
        final double[] min = new double[1];
        min[0] = Double.MAX_VALUE;
        return connectors.stream().filter(con -> {
            Node[] nodes = con.getNodes();
            if (someEquals(nodes, node)) {
                if (containsAny(nodes, nodeSet) ) {
                    return true;
                }
                return false;
            }
            return false;
        }).min( Connector::compareTo
                /*(o1, o2) -> { return (int)
                (o1.getDistance() - o2.getDistance());}*/
                ).get();
    }

    private static Connector findNearestConnector(Set<Node> nodeSet, Node node, List<Connector> connectors) {
         Connector minCon = null;
        double min = Double.MAX_VALUE;
        for (var con : connectors) {
            Node[] nodes = con.getNodes();
            if (containsAny(nodes, nodeSet) && someEquals(nodes, node)) {
                double tempmin = con.getDistance();
                if (tempmin < min) {
                    min = tempmin;
                    minCon = con;
                }
            }
        }
        return minCon;
    }

    private static Set<Node> eachNeighbour(Node node, List<Connector> connectors) {
        Set<Node> nodeSet = new HashSet<>();
        connectors.forEach(connector -> {
            Node other = getTheOther(connector, node);
            if (someEquals(connector.getNodes(), other))
                nodeSet.add(other);
        });
        /*for (var conn : connectors){
            if (someEquals(conn.getNodes(), node) )
                nodeSet.add( node);
        }*/
        return nodeSet;
    }

    public void initBorder() {
        //System.out.println("SIZE="+connectors.size());
        var i = 0;
        for (Connector connector : connectors) {
            Node[] nodes = connector.getNodes();
            if (nodes[0].equals(sourceNode)) {
                border.add(nodes[1]);
                continue;
            }
            if (nodes[1].equals(sourceNode)) {
                border.add(nodes[0]);
            }
        }
        //System.out.println("SIZES="+connectors.size()+" "+i);
    }

    public void updateConnectors(Node node) {

    }

    private List<Connector> findObviousConnectors(Node src, Node dst, List<Connector> conns) {
        //List<Connector> resultList = new ArrayList<>();
        return conns.stream().filter(connector -> {
            Node[] nodes = connector.getNodes();
            if (someEquals(nodes, src) && someEquals(nodes, dst))
                return true;
            else
                return false;
        }).collect(Collectors.toList());
        //return resultList;
    }

    private void printConnectors(){
        connectors.forEach(connector -> {
            System.out.println("konektor: " + connector.getNodes()[0].getNodeName() + " " + connector.getNodes()[1].getNodeName() + " " + connector.getDistance() +" " +connector.getLabelPath());
        });

    }

    public Connector kruskal(/*Node node,*/ Node destinationNode) {
        //int counter = 0;
        visited.add(sourceNode);
        while (!border.isEmpty()) {
            /*printConnectors();
            Connector connector = findConnector(new Node("a"), new Node("e"), connectors);
            if (connector!=null)
                System.out.print("konektor " + connector.getNodes()[0].getNodeName() + " " + connector.getNodes()[1].getNodeName() + " " + connector.getDistance());
            System.out.println();*/
            /*System.out.print("Border: ");
            border.forEach(node -> {
                System.out.print(node.getNodeName());
            });
            System.out.println();
            System.out.println("connectors size: "+connectors.size());*/
            Connector nearestConnector = findNearestConnector(border, sourceNode, connectors);
            /*System.out.println("nearestConnector "
                    + nearestConnector.getNodes()[0].getNodeName() + " "
                    + nearestConnector.getNodes()[1].getNodeName() + " "
                    + nearestConnector.getDistance()+ " "+nearestConnector.getLabelPath());*/
            Node nearestNode = getTheOther(nearestConnector, sourceNode);//error

            //System.out.println("Nearest node "+nearestNode.getNodeName());
            visited.add(nearestNode);
            border.remove(nearestNode);
            Set<Node> neighbourNodes = eachNeighbour(nearestNode, connectors);

            /*System.out.println("Neighbour nodes: ");
            neighbourNodes.forEach(node -> System.out.println(node.getNodeName()));*/

            //Connector nearestSourceConnector = findConnector(nearestNode, sourceNode, connectors);

            /*System.out.println("nearestSourceConnector "
                    + nearestSourceConnector.getNodes()[0].getNodeName() + " "
                    + nearestSourceConnector.getNodes()[1].getNodeName() + " "
                    + nearestSourceConnector.getDistance()+ " "+nearestSourceConnector.getLabelPath());*/
            for (Node neighbourNode : neighbourNodes) {
                Connector nearestSourceConnector = findConnector(nearestNode, sourceNode, connectors);
                if (!visited.contains(neighbourNode)) {
                    //System.out.println("Nejbur node "+neighbourNode.getNodeName());
                    Connector neighbourNearestConnector = findConnector(neighbourNode, nearestNode, connectors);
                    /*System.out.println("neighbourNearest "
                            + neighbourNearestConnector.getNodes()[0].getNodeName() + " "
                            + neighbourNearestConnector.getNodes()[1].getNodeName() + " "
                            + neighbourNearestConnector.getDistance()  + " "+neighbourNearestConnector.getLabelPath());*/
                    double indirect = neighbourNearestConnector.getDistance() + nearestSourceConnector.getDistance();
                    if (border.contains(neighbourNode)) {
                        Connector neighbourSourceConnector = findConnector(neighbourNode, sourceNode, connectors);
                        /*System.out.println("If border neighbourSourceConnector "
                                + neighbourSourceConnector.getNodes()[0].getNodeName() + " "
                                + neighbourSourceConnector.getNodes()[1].getNodeName() + " "
                                + neighbourSourceConnector.getDistance()  + " "+neighbourSourceConnector.getLabelPath());*/
                        connectors.add(neighbourSourceConnector);
                        double direct = neighbourSourceConnector.getDistance();
                        if (indirect < direct) {
                            neighbourSourceConnector.updateDistance(indirect, nearestNode.getNodeName());
                        }
                    } else {
                        /*System.out.println("NOBODY WAS HERE");
                        System.out.println("node eee "+neighbourNode.getNodeName());*/
                        border.add(neighbourNode);
                        List<String> labels = nearestSourceConnector.getLabelPath();
                        /*System.out.println("Else nearestSourceConnector "
                                + nearestSourceConnector.getNodes()[0].getNodeName() + " "
                                + nearestSourceConnector.getNodes()[1].getNodeName() + " "
                                + nearestSourceConnector.getDistance() + " "+nearestSourceConnector.getLabelPath());*/
                        //System.out.println("nirest: "+nearestNode.getNodeName());
                        if (! labels.contains(nearestNode.getNodeName()))
                            labels.add(nearestNode.getNodeName());
                        //System.out.println("labels: "+labels+" "+indirect);
                        Connector neighbourSourceNewConnector = new Connector(sourceNode, neighbourNode, indirect, labels);

                        //System.out.println("neighbourSourceNewConnector labels: "+neighbourSourceNewConnector.getLabelPath());
                        /*System.out.println("neighbourSourceNewConnector "
                                + neighbourSourceNewConnector.getNodes()[0].getNodeName() + " "
                                + neighbourSourceNewConnector.getNodes()[1].getNodeName() + " "
                                + neighbourSourceNewConnector.getDistance() + " "+neighbourSourceNewConnector.getLabelPath());*/
                        //if (! connectors.contains(neighbourSourceNewConnector))
                        connectors.add(neighbourSourceNewConnector);
                    }
                }
            }
            //counter ++;
        }
        PriorityQueue<Connector> pq = new PriorityQueue<>(findObviousConnectors(sourceNode, destinationNode, connectors));
        return pq.peek();
    }
}
