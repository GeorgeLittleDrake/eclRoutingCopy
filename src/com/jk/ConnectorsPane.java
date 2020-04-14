package com.jk;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class ConnectorsPane extends JPanel {
    private static class Line{
        private final double x1;
        private final double y1;
        private final double x2;
        private final double y2;
        private final Color color;
        private final String label;

        public Line(double x1, double y1, double x2, double y2, Color color, String label) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.label = label;
        }

        public double getX1() {
            return x1;
        }

        public double getY1() {
            return y1;
        }

        public double getX2() {
            return x2;
        }

        public double getY2() {
            return y2;
        }

        public Color getColor() {
            return color;
        }

        public String getLabel() {
            return label;
        }
    }

    private final List<Line> lines = new ArrayList<Line>();

    public void addLine(double x1, double x2, double x3, double x4, String label) {
        addLine(x1, x2, x3, x4, Color.BLACK, label);
    }

    /*public void addLine(double x1, double x2, double x3, double x4) {
        addLine(x1, x2, x3, x4, Color.black);
    }*/

    public void addLine(double x1, double x2, double x3, double x4, Color color, String label) {
        lines.add(new Line(x1, x2, x3, x4, color, label));
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line line : lines) {
            double x1 = line.getX1();
            double y1 = line.getY1();
            double x2 = line.getX2();
            double y2 = line.getY2();
            //double distance = Point2D.distance(x1, y1, x2, y2);
            System.out.println("x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " newX " + Math.abs(x2 - x1)/2 + " newY " + Math.abs(y2 - y1) / 2);
            float strX = (float) ( Math.min(x1, x2) + Math.abs(x1 - x2)/2 );
            float strY = (float) (Math.min(y1, y2) + Math.abs(y1 - y2)/2 );
            System.out.println("strX " + strX + " strY " + strY);
            g2.drawString(/*Double.valueOf(distance).toString()*/line.getLabel(), strX,  strY);
            var line2d = new Line2D.Double( line.getX1(), line.getY1(), line.getX2(), line.getY2() );
            g2.draw(line2d);
        }
    }
}
