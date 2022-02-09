/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen;

import assignment.items.FoodItem;
import assignment.items.GameItem;
import assignment.items.ToyItem;
import assignment.pets.Cat;
import assignment.pets.Dog;
import assignment.pets.Pet;
import assignment.screen.panel.GamePreviewPanel;
import assignment.screen.panel.GameSelectionPanel;
import assignment.screen.panel.InventoryPanel;
import assignment.screen.panel.PetSelectionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * MVC controller class
 *
 * @author charlie
 */
public class Controller {

    private View view;
    private Model model;
    private Random gen;

    /**
     * Store and setup view and model classes
     *
     * @param view jframe view for this app
     * @param model data model for this app
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.gen = new Random();
        this.model.changePanel(new GameSelectionPanel(this.model.getGames()));
        this.view.initButtons(new ButtonListener());
        this.view.addWindowListener(new WindowClosing());

        this.view.setVisible(true);
    }

    /**
     * Main button listener for this app
     */
    private class ButtonListener implements ActionListener {

        /**
         * for each panel handle the input appropriately
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // Obtain the text displayed on the component.

            if (view.getCenter() instanceof PetSelectionPanel) {
                handlePetSelection(command);
            }

            if (view.getCenter() instanceof GameSelectionPanel) {
                handleGameSelection(command);
            }

            if (view.getCenter() instanceof GamePreviewPanel) {
                handleGamePreview(command);
            }

            if (view.getCenter() instanceof InventoryPanel) {
                handleInventory(command);
            }

        }

        /**
         * handles the input if currently viewing pet selection
         *
         * @param command
         */
        private void handlePetSelection(String command) {
            switch (command) {
                case "new dog":
                    String dogName = (String) JOptionPane.showInputDialog(view, "Please enter a name for your new dog!");
                    if (dogName != null) {
                        model.getGame().addPet(new Dog(dogName));
                        model.changePanel(new GamePreviewPanel(model.getGame()));
                        view.initButtons(this);
                    }
                    break;
                case "new cat":
                    String catName = (String) JOptionPane.showInputDialog(view, "Please enter a name for your new cat!");
                    if (catName != null) {
                        model.getGame().addPet(new Cat(catName));
                        model.changePanel(new GamePreviewPanel(model.getGame()));
                        view.initButtons(this);
                    }
                    break;
            }
        }

        /**
         * handles the input if currently viewing game selection
         *
         * @param command
         */
        private void handleGameSelection(String command) {
            GameSelectionPanel panel = ((GameSelectionPanel) view.getCenter());
            if (command.equals("new")) {

                String name = (String) JOptionPane.showInputDialog(view, "Please enter a name for your new game!");

                if (name != null) {
                    model.newGame(name);
                    model.changePanel(new PetSelectionPanel());
                    view.initButtons(this);
                    view.setTitle(model.getGame().getName());
                }
            } else if (command.equals("play")) {
                if (!panel.selectedGameName().equals("")) {
                    model.loadGame(panel.selectedGameName());
                    model.changePanel(new GamePreviewPanel(model.getGame()));
                    view.initButtons(this);
                    view.setTitle(model.getGame().getName() + " $" + model.getGame().getCurrency());
                }
            }
        }

        /**
         * handles the input if currently viewing game preview
         *
         * @param command
         */
        private void handleGamePreview(String command) {
            GamePreviewPanel panel = ((GamePreviewPanel) view.getCenter());
            switch (command) {
                case "pet show":
                    petShow();
                    panel.refreshTable(model.getGame());
                    view.setTitle(model.getGame().getName() + " $" + model.getGame().getCurrency());
                    break;
                case "new":
                    model.changePanel(new PetSelectionPanel());
                    view.initButtons(this);
                    break;
                case "inventory":
                    view.backButtonEnabled(true);
                    model.changePanel(new InventoryPanel(model.getGame().getInventory()));
                    view.initButtons(this);
                    break;
            }
        }

        /**
         * handles the input if currently viewing inventory
         *
         * @param command
         */
        private void handleInventory(String command) {
            InventoryPanel panel = (InventoryPanel) view.getCenter();
            switch (command) {
                case "back":
                    view.backButtonEnabled(false);
                    model.changePanel(new GamePreviewPanel(model.getGame()));
                    view.initButtons(this);
                    break;
                case "select":
                    useItem(panel);
                    break;
            }
        }

        /**
         * handles pet show functionality
         */
        private void petShow() {
            List<Pet> pets = model.getGame().getPets();
            String[] options = new String[pets.size()];

            for (int i = 0; i < pets.size(); i++) {
                options[i] = pets.get(i).getName();
            }
            String petName = (String) JOptionPane.showInputDialog(view, "Select a pet to take to the pet show!", null, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (petName == null) {
                return;
            }

            Pet pet = null;

            for (Pet p : pets) {
                if (p.getName().equals(petName)) {
                    pet = p;
                }
            }

            if (pet == null) {
                return;
            }

            if (pet.getHappiness() < 50) {
                JOptionPane.showMessageDialog(view, pet.getName() + "is too unhappy to go to the pet show!");
                return;
            }

            if (pet.getHunger() > 50) {
                JOptionPane.showMessageDialog(view, pet.getName() + " is too hungry to go to the pet show!");
                return;
            }

            if (pet.getTiredness() > 50) {
                JOptionPane.showMessageDialog(view, pet.getName() + " is too tired to go to the pet show!");
                return;
            }

            int money = gen.nextInt(50) + 50;
            JOptionPane.showMessageDialog(view, pet.getName() + " won $" + money + " at the show!");
            pet.gettingHungry(10);
            pet.gettingTired(10);

            model.getGame().setCurrency(model.getGame().getCurrency() + money);

        }

        /**
         * handles item use
         *
         * @param panel
         */
        private void useItem(InventoryPanel panel) {
            if (panel.selectedItem() == null) {
                return;
            }
            GameItem item = panel.selectedItem();
            List<Pet> pets = model.getGame().getPets();
            String[] options = new String[pets.size()];

            for (int i = 0; i < pets.size(); i++) {
                options[i] = pets.get(i).getName();
            }
            String petName = (String) JOptionPane.showInputDialog(view, "Select a pet to give " + item.getName() + "to!", null, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (petName == null) {
                return;
            }

            for (Pet pet : pets) {
                if (petName.equals(pet.getName())) {
                    if (item instanceof FoodItem) {
                        if (pet.feed((FoodItem) item)) {
                            JOptionPane.showMessageDialog(view, "Successfully fed " + pet.getName() + " a " + item.getName());
                            model.getGame().getInventory().remove(item);
                        } else {
                            JOptionPane.showMessageDialog(view, "Cannot feed " + pet.getName() + " a " + item.getName());
                        }
                    }

                    if (item instanceof ToyItem) {
                        if (pet.play((ToyItem) item)) {
                            JOptionPane.showMessageDialog(view, pet.getName() + " played with " + item.getName());
                        } else {
                            JOptionPane.showMessageDialog(view, pet.getName() + " refused to play with " + item.getName());
                        }
                    }
                }
            }

            //refresh item list
            panel.refreshItems();
        }

    }

    /**
     * saves game on close
     */
    private class WindowClosing extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            if (model.getGame() == null) {
                return;
            }
            model.getDataStore().saveGame(model.getGame());
        }
    }
}
