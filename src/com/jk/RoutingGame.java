package com.jk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoutingGame extends JFrame {

    private List<ButtonTuple> buttonTuples;
    private Set<String> labelNames;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem menuItem;
    private JPopupMenu popupMenu = new JPopupMenu();
    private LineConnector connector;
    private boolean connectionActivated = false;
    //private int tupSize = 0;
    private RoundButton connectorButton;


    public RoutingGame() {
        super();

        buttonTuples = new ArrayList<>();
        labelNames = new HashSet<>();
        setBounds(300, 200, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel npanel = new JPanel();
        getContentPane().add(npanel, BorderLayout.NORTH);
        npanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        //JPanel cpanel = new JPanel();
        ConnectorsPane cpanel = new ConnectorsPane();
        getContentPane().add(cpanel, BorderLayout.CENTER);
        cpanel.setLayout(null);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        JMenuItem nodeItem = new JMenuItem("New node");
        menuBar.add(fileMenu);
        fileMenu.add(nodeItem);
        setJMenuBar(menuBar);
        nodeItem.addActionListener(e -> {
            System.out.println("Added menu action listener");
            String s = "";
            while (s.isBlank() || labelNames.contains(s)) {
                s = (String) JOptionPane.showInputDialog(
                        this,
                        "Choose non-empty, unique name for your new node:",
                        "Add node",
                        JOptionPane.PLAIN_MESSAGE,
                        createImageIcon("images/middle.gif"),
                        null,
                        "A");
            }
            labelNames.add(s);
            final String nodeName = s;
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            cpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    System.out.println("Added mouse listener");
                    RoundButton roundButton = new RoundButton(nodeName);
                    JPopupMenu popupMenu = new JPopupMenu();
                    //System.out.println("Buttontuples size from inside = " + tupSize);
                    roundButton.setComponentPopupMenu(popupMenu);
                    //popupMenu.add(menuItem);
                    //addPopup(roundButton, popupMenu);
                    menuItem = new JMenuItem("Connect nodes");
                    popupMenu.add(menuItem);
                    menuItem.addActionListener(pop -> {
                        System.out.println("Added popup listener");
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        System.out.print("nonbutton: ");
                        System.out.println("X = " + mouseEvent.getX() + " Y = " + mouseEvent.getY());

                        connectionActivated = true;
                        System.out.print("aroundButton: ");
                        printCoords(roundButton);
                        connectorButton = roundButton;
                    });
                    //popupMenu.add(menuItem);

                    Dimension size = roundButton.getPreferredSize();
                    roundButton.setBounds(mouseEvent.getX() - size.width / 2, mouseEvent.getY() - size.height / 2, size.width, size.height);
                    var p = new Point2D.Double(mouseEvent.getX() + size.width / 2, mouseEvent.getY() + size.height / 2);
                    roundButton.setMiddle(p);
                    buttonTuples.add(new ButtonTuple(roundButton, nodeName, mouseEvent.getX(), mouseEvent.getY()));
                    //tupSize = buttonTuples.size();
                    roundButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ce) {
                            System.out.println("Added roundButton action listener");
                            if (connectionActivated) {
                                RoundButton button = (RoundButton) ce.getSource();
                                double distance =
                                        Point2D.distance(connectorButton.getMiddle().getX(),
                                                connectorButton.getMiddle().getY(),
                                                button.getMiddle().getX(),
                                                button.getMiddle().getY());
                                //Double distanceD = Double.valueOf(distance);
                                long distanceL = (long) (100 * distance);
                                double distanced = distanceL / 100;
                                //distanceD = Double.parseDouble(String.format("%.2f", distance));
                                //roundButton.getMiddle().distance(button.getMiddle());
                                System.out.print("connectorButton: ");
                                printCoords(connectorButton);
                                System.out.print("button: ");
                                printCoords(button);
                                System.out.print("roundButton: ");
                                printCoords(roundButton);
                                System.out.println("Distance: " + distanced );
                                connector = new LineConnector(connectorButton, button, Double.valueOf(distanced).toString());
                                cpanel.add(connector);
                                cpanel.addLine(connectorButton.getMiddle().getX(),
                                        connectorButton.getMiddle().getY(),
                                        button.getMiddle().getX(),
                                        button.getMiddle().getY(),
                                        Double.valueOf(distanced).toString());
                                setCursor(Cursor.getDefaultCursor());
                                cpanel.revalidate();
                                cpanel.repaint();
                                //this.plusk
                                System.out.println("Class name: " + this.getClass().getName());
                                roundButton.removeActionListener(this);
                                connectionActivated = false;
                            }
                        }
                    });


                    setCursor(Cursor.getDefaultCursor());
                    cpanel.add(roundButton);

                    cpanel.revalidate();
                    cpanel.repaint();
                    cpanel.removeMouseListener(this);
                }
            });
        });
        nodeItem = new JMenuItem("Check components");
        nodeItem.addActionListener(e -> {
            listAllComponentsIn(cpanel);
        });
        fileMenu.add(nodeItem);
    }

/*    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }*/

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }

    public void listAllComponentsIn(Container parent) {
        for (Component c : parent.getComponents()) {
            System.out.println(c.toString());

            if (c instanceof Container)
                listAllComponentsIn((Container) c);
        }
    }

    private void printCoords(RoundButton rb) {
        Point2D p2 = rb.getMiddle();
        System.out.println("X = " + p2.getX() + " Y = " + p2.getY());
    }

    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = RoutingGameRunner.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}





