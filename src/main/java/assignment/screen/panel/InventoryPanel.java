/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen.panel;

import assignment.items.GameItem;
import assignment.screen.Controllable;
import com.google.common.collect.Multiset;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/**
 * Panel to display inventory
 * @author charlie
 */
public class InventoryPanel extends JPanel implements Controllable {

    private JList items;
    private JPanel buttons = new JPanel();
    private JButton selectItem = new JButton("select");
    private Multiset<GameItem> inventory;

    /**
     * Create components for inventory
     * @param inventory MultiSet of items
     */
    public InventoryPanel(Multiset<GameItem> inventory) {
        super(new GridLayout());

        this.inventory = inventory;
        
        items = new JList(inventory.toArray());
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        items.setLayoutOrientation(JList.VERTICAL);
        items.setBorder(BorderFactory.createTitledBorder("Select an item from the list below!"));

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));

        selectItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(selectItem);

        this.add(items);

        this.add(buttons);
    }

    /**
     * Add listener to all buttons
     * @param listener 
     */
    @Override
    public void initButtons(ActionListener listener) {
        this.selectItem.addActionListener(listener);
    }
    
    /**
     * @return selected item in game
     */
    public GameItem selectedItem() {
        if (items.isSelectionEmpty()) return null;
        return (GameItem) items.getSelectedValue();
    }
    
    /**
     * refresh items in list when changes are made to inventory
     */
    public void refreshItems() {
        items.setListData(inventory.toArray());
    }

}
