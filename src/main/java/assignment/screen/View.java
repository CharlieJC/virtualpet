/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * view of the virtual pet game
 * @author charlie
 */
public class View extends JFrame implements Observer, Controllable {

    private JLabel gameName = new JLabel("Please select a game!");
    private JButton back = new JButton("back");
    private JPanel center;

    /**
     * initializing graphics
     */
    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.WHITE);
        north.add(gameName, BorderLayout.WEST);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(Color.WHITE);
        this.back.setEnabled(false);
        south.add(back, BorderLayout.WEST);

        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.setResizable(false);

    }

    /**
     * when notified of a change of the data model
     * @param arg0
     * @param arg1 
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1 == null || !(arg1 instanceof JPanel)) {
            return;
        }

        JPanel panel = (JPanel) arg1;
        if (center != null) {
            this.getContentPane().remove(center);
            this.revalidate();
            this.repaint();
        }
        this.center = panel;
        this.center.setPreferredSize(new Dimension(1000, 400));
        this.add(center, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        this.pack();

    }

    /**
     * initialize buttons with the listener
     * @param listener 
     */
    @Override
    public void initButtons(ActionListener listener) {

        this.back.addActionListener(listener);
        if (center instanceof Controllable) {

            ((Controllable) center).initButtons(listener);
        }
    }

    /**
     * @return center panel
     */
    public JPanel getCenter() {
        return center;
    }

    /**
     * @param text to display in title
     */
    public void setTitle(String text) {
        this.gameName.setText(text);
    }
    
    /**
     * @param enabled whether to enable or disable back button
     */
    public void backButtonEnabled(boolean enabled) {
        this.back.setEnabled(enabled);
    }

}
