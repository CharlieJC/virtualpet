/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen.panel;

import assignment.screen.Controllable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/**
 * Panel for selecting games
 * @author charlie
 */
public class GameSelectionPanel extends JPanel implements Controllable {

    private JList gameList;
    private JPanel buttons = new JPanel();
    private JButton newGame = new JButton("new");
    private JButton play = new JButton("play");

    /**
     * Draw game selection components
     * @param games list of games available
     */
    public GameSelectionPanel(String[] games) {
        super(new GridLayout());

        gameList = new JList(games);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setLayoutOrientation(JList.VERTICAL);
        gameList.setBorder(BorderFactory.createTitledBorder("Select a game from the list below!"));

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));

        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(newGame);

        buttons.add(Box.createRigidArea(new Dimension(0, 20)));

        play.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(play);

        this.add(gameList);

        this.add(buttons);
        
    }

    /**
     * Add listener to buttons
     * @param listener 
     */
    @Override
    public void initButtons(ActionListener listener) {
        this.newGame.addActionListener(listener);
        this.play.addActionListener(listener);
    }
    
    /**
     * @return selected game from list
     */
    public String selectedGameName() {
        if (gameList.isSelectionEmpty()) return "";
        String text = (String)gameList.getSelectedValue();
        return text;
    }

}
