package com.jk;

import java.awt.*;

public class RoutingGameRunner {
    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            try {
                RoutingGame window = new RoutingGame();
                window.setResizable(false);
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
