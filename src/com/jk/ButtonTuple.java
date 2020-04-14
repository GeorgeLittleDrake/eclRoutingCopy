package com.jk;

public class ButtonTuple {
    private RoundButton roundButton;
    private String label;
    private int xPos;
    private int yPos;

    public ButtonTuple(RoundButton roundButton, String label, int xPos, int yPos) {
        this.roundButton = roundButton;
        this.label = label;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public RoundButton getRoundButton() {
        return roundButton;
    }

    public void setRoundButton(RoundButton roundButton) {
        this.roundButton = roundButton;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
