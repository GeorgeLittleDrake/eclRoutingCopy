package com.jk.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Distance {
    private double value;
    private List<String> labels;

    public Distance(double value) {
        this.value = value;
        labels = new ArrayList<>();
    }

    public Distance(double value, List<String> labels) {
        this(value);
        this.labels = labels;
    }

    public double getValue() {
        return value;
    }

    public List<String> getLabels() {
        return labels;
    }
    public void updateDistance(double value, String label){
        this.value += value;
        labels.add(label);
    }
}
