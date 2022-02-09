/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen.panel;

import assignment.Game;
import assignment.pets.Dog;
import assignment.pets.Pet;
import assignment.screen.Controllable;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Panel for previewing pets in game
 * @author charlie
 */
public class GamePreviewPanel extends JPanel implements Controllable {

    private JTable table;
    private JPanel buttonPanel = new JPanel();
    private JButton selectPet = new JButton("pet show");
    private JButton newPet = new JButton("new");
    private JButton inventory = new JButton("inventory");

    /**
     * initialize and draw panel
     * @param game 
     */
    public GamePreviewPanel(Game game) {
        super(new BorderLayout());

        String[] columns = new String[]{"Name", "Happiness", "Hunger", "Tiredness", "Type"};
        String[][] data = new String[game.getPets().size()][5];

        int count = 0;
        for (Pet pet : game.getPets()) {
            data[count][0] = pet.getName();
            data[count][1] = "" + pet.getHappiness();
            data[count][2] = "" + pet.getHunger();
            data[count][3] = "" + pet.getTiredness();

            if (pet instanceof Dog) {
                data[count][4] = "Dog";
            } else {
                data[count][4] = "Cat";
            }

            count++;
        }

        buttonPanel.add(selectPet);
        buttonPanel.add(newPet);
        buttonPanel.add(inventory);

        table = new JTable(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        this.add(table.getTableHeader(), BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Add buttons to panel
     * @param listener 
     */
    @Override
    public void initButtons(ActionListener listener) {
        this.selectPet.addActionListener(listener);
        this.newPet.addActionListener(listener);
        this.inventory.addActionListener(listener);
    }
    
    /**
     * refresh data in the pets table
     * @param game 
     */
    public void refreshTable(Game game) {
        String[][] data = new String[game.getPets().size()][5];

        int count = 0;
        for (Pet pet : game.getPets()) {
            data[count][0] = pet.getName();
            data[count][1] = "" + pet.getHappiness();
            data[count][2] = "" + pet.getHunger();
            data[count][3] = "" + pet.getTiredness();

            if (pet instanceof Dog) {
                data[count][4] = "Dog";
            } else {
                data[count][4] = "Cat";
            }

            count++;
        }
        
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                this.table.getModel().setValueAt(data[i][j], i, j);
            }
        }
        
    }

}
