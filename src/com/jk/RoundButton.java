package com.jk;

import com.jk.shortestpath.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import static java.awt.Color.WHITE;

public class RoundButton extends JButton {
    private static final int DEFAULT_WIDTH = 66;
    private static final int DEFAULT_HEIGHT = 66;
    private Point2D middle;
    private Shape shape;
    private int radius;
    private int diameter;
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return diameter;
    }

    public Point2D getMiddle() {
        return middle;
    }

    public void setMiddle(Point2D middle) {
        this.middle = middle;
    }

    public RoundButton(String label) {
        super(label);
        node = new Node(label);
        setContentAreaFilled(false);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        int diameter = Math.min(getWidth(), getHeight());
        radius = diameter / 2;

        if (! getModel().isArmed()) {
            g.setColor(WHITE);
        } else {
            g.setColor( new Color(230, 230, 230) );
        }
        g.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter);
        g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        /*int diameter = Math.min(getWidth(), getHeight());
        int radius = diameter / 2;*/
        g.setColor(getForeground() );
        g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter - 1, diameter - 1);
    }

    /*public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }*/

    @Override
    public int getX() {
        return super.getX() + DEFAULT_WIDTH / 2;
    }

    @Override
    public int getY() {
        return super.getY() + DEFAULT_HEIGHT / 2;
    }
}
