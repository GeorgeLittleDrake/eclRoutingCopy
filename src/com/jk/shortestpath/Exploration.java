package com.jk.shortestpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exploration {
    public static void main(String... args) {

        Node n = new Node("a");
        Node dst = new Node("n");
        List<Connector> connectors = new ArrayList<>();


        Node[] ar0 = new Node[2];
        ar0[0] = new Node("a");
        ar0[1] = new Node("c");
        Connector c0 = new Connector(ar0[0], ar0[1], 5d);
        connectors.add(c0);

        Node[] ar1 = new Node[2];
        ar1[0] = new Node("a");
        ar1[1] = new Node("b");
        Connector c1 = new Connector(ar1[0], ar1[1], 2d);
        connectors.add(c1);

        //System.out.println("EQUALS: "+ ar0[0].equals(ar1[0]) );

        Node[] ar2 = new Node[2];
        ar2[0] = new Node("b");
        ar2[1] = new Node("c");
        Connector c2 = new Connector(ar2[0], ar2[1], 6d);
        connectors.add(c2);

        Node[] ar3 = new Node[2];
        ar3[0] = new Node("b");
        ar3[1] = new Node("e");
        Connector c3 = new Connector(ar3[0], ar3[1], 5d);
        connectors.add(c3);

        Node[] ar4 = new Node[2];
        ar4[0] = new Node("e");
        ar4[1] = new Node("g");
        Connector c4 = new Connector(ar4[0], ar4[1], 1d);
        connectors.add(c4);

        Node[] ar6 = new Node[2];
        ar6[0] = new Node("c");
        ar6[1] = new Node("d");
        Connector c6 = new Connector(ar6[0], ar6[1], 4d);
        connectors.add(c6);

        Node[] ar7 = new Node[2];
        ar7[0] = new Node("d");
        ar7[1] = new Node("g");
        Connector c7 = new Connector(ar7[0], ar7[1], 3d);
        connectors.add(c7);

        Node[] ar8 = new Node[2];
        ar8[0] = new Node("g");
        ar8[1] = new Node("f");
        Connector c8 = new Connector(ar8[0], ar8[1], 2d);
        connectors.add(c8);

        Node[] ar10 = new Node[2];
        ar10[0] = new Node("e");
        ar10[1] = new Node("f");
        Connector c10 = new Connector(ar10[0], ar10[1], 3d);
        connectors.add(c10);

        Node[] ar11 = new Node[2];
        ar11[0] = new Node("d");
        ar11[1] = new Node("i");
        Connector c11 = new Connector(ar11[0], ar11[1], 4d);
        connectors.add(c11);


        Node[] ar12 = new Node[2];
        ar12[0] = new Node("h");
        ar12[1] = new Node("i");
        Connector c12 = new Connector(ar12[0], ar12[1], 1d);
        connectors.add(c12);

        Node[] ar13 = new Node[2];
        ar13[0] = new Node("i");
        ar13[1] = new Node("j");
        Connector c13 = new Connector(ar13[0], ar13[1], 1d);
        connectors.add(c13);

        Node[] ar14 = new Node[2];
        ar14[0] = new Node("f");
        ar14[1] = new Node("j");
        Connector c14 = new Connector(ar14[0], ar14[1], 2d);
        connectors.add(c14);

        Node[] ar15 = new Node[2];
        ar15[0] = new Node("f");
        ar15[1] = new Node("k");
        Connector c15 = new Connector(ar15[0], ar15[1], 3d);
        connectors.add(c15);

        Node[] ar16 = new Node[2];
        ar16[0] = new Node("j");
        ar16[1] = new Node("k");
        Connector c16 = new Connector(ar16[0], ar16[1], 4d);
        connectors.add(c16);

        Node[] ar17 = new Node[2];
        ar17[0] = new Node("k");
        ar17[1] = new Node("l");
        Connector c17 = new Connector(ar17[0], ar17[1], 6d);
        connectors.add(c17);

        Node[] ar18 = new Node[2];
        ar18[0] = new Node("l");
        ar18[1] = new Node("m");
        Connector c18 = new Connector(ar18[0], ar18[1], 5d);
        connectors.add(c18);

        Node[] ar19 = new Node[2];
        ar19[0] = new Node("h");
        ar19[1] = new Node("n");
        Connector c19 = new Connector(ar19[0], ar19[1], 7d);
        connectors.add(c19);

        Node[] ar9 = new Node[2];
        ar9[0] = new Node("i");
        ar9[1] = new Node("m");
        Connector c9 = new Connector(ar9[0], ar9[1], 6d);
        connectors.add(c9);

        Node[] ar5 = new Node[2];
        ar5[0] = new Node("m");
        ar5[1] = new Node("n");
        Connector c5 = new Connector(ar5[0], ar5[1], 5d);
        connectors.add(c5);

        Board board = new Board( n );
        board.setConnectors( connectors);
        board.initBorder();

        System.out.println("BORDER of " + board.getSourceNode().getNodeName() + " " + board.getBorder().size() );
        board.getBorder().forEach(node -> {
            System.out.println(node.getNodeName());
        });
        /*board.getBorder().forEach(o -> {
            System.out.println(o.g);
        });*/

        Connector conn = board.kruskal( dst);
        System.out.println("Result "+ conn.getNodes()[0].getNodeName()+" "+conn.getNodes()[1].getNodeName() + " "+conn.getDistance());
        System.out.println("Path: ");
        conn.getLabelPath().forEach(System.out::print);
        System.out.println();
        System.out.println("Connectors size = "+board.getConnectors().size() );
    }
}
