/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen.panel;


import assignment.screen.Controllable;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel for creating new pets
 * @author charlie
 */
public class PetSelectionPanel extends JPanel implements Controllable {

    private JButton dog = new JButton("new cat");
    private JButton cat = new JButton("new dog");
    
    /**
     * init buttons
     */
    public PetSelectionPanel() {
        super(new GridLayout());
        this.add(dog);
        this.add(cat);
    }

    /**
     * allocate each button the listener
     * @param listener 
     */
    @Override
    public void initButtons(ActionListener listener) {
       this.dog.addActionListener(listener);
       this.cat.addActionListener(listener);
    }
    
    
}
