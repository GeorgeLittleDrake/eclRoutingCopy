package com.jk;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class LineConnector extends JComponent {
    private RoundButton startButton;
    private RoundButton endButton;
    private String label;
    private int width;// = Math.abs(x1 - x2);
    private int height;// = Math.abs(y1 - y2);
    private double distance;// = startButton.getMiddle().distance(endButton.getMiddle());
    private double x1;// = this.startButton.getMiddle().getX();
    private double y1;// = this.startButton.getMiddle().getY();
    private double x2;// = this.endButton.getMiddle().getX();
    private double y2;// = this.endButton.getMiddle().getY();

    public LineConnector(RoundButton startButton, RoundButton endButton, String label) {
        this.startButton = startButton;
        this.endButton = endButton;
        this.label = label;
        distance = startButton.getMiddle().distance(endButton.getMiddle());
        x1 = this.startButton.getMiddle().getX();
        y1 = this.startButton.getMiddle().getY();
        x2 = this.endButton.getMiddle().getX();
        y2 = this.endButton.getMiddle().getY();
        width = (int) Math.abs(x1 - x2);
        height = (int) Math.abs(y1 - y2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //new Line2D.Double()
        var line = new Line2D.Double(this.startButton.getMiddle(), this.endButton.getMiddle() );
        g2.draw(line);
/*        double distance = startButton.getMiddle().distance(endButton.getMiddle());
        double x1 = this.startButton.getMiddle().getX();
        double y1 = this.startButton.getMiddle().getY();
        double x2 = this.endButton.getMiddle().getX();
        double y2 = this.endButton.getMiddle().getY();*/
        float strX = (float) ( Math.min(x1, x2) + Math.abs(x1 - x2)/2 );
        float strY = (float) (Math.min(y1, y2) + Math.abs(y1 - y2)/2 );
        System.out.println("x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " newX " + strX + " newY " + strY );
        g2.drawString(Double.valueOf(distance).toString(), strX,  strY);
        //g.drawLine(this.startButton.getMiddle().getX(),this.startButton.getMiddle().getY(), this.endButton.getMiddle().getX(), this.endButton.getMiddle().getY());
    }

    public RoundButton getStartButton() {
        return startButton;
    }

    public void setStartButton(RoundButton startButton) {
        this.startButton = startButton;
    }

    public RoundButton getEndButton() {
        return endButton;
    }

    public void setEndButton(RoundButton endButton) {
        this.endButton = endButton;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }
}
