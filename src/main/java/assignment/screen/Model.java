/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen;

import assignment.Game;
import assignment.storage.Database;
import java.util.Observable;
import javax.swing.JPanel;
import assignment.storage.Datastore;

/**
 * MVC data model
 * @author charlie
 */
public class Model extends Observable {

    private Datastore datastore;
    private String[] games;
    private Game game;

    /**
     * initialize data required for virtual pet game
     */
    public Model() {
        this.datastore = new Database();
        
        Object[] games = this.datastore.savedGames().toArray();
        this.games = new String[games.length];
        for (int i = 0; i < games.length; i++) {
            this.games[i] = (String) games[i];
        }
    }
    
    /**
     * Handle creation of a new game
     * add underscores if game exists with this name
     * @param name 
     */
    public void newGame(String name) {
        String newName = name;
        for (String usedName : games) {
            if (newName.equals(usedName)){
                newGame(name + "_");
                return;
            }
        }
        this.game = new Game(name);
    }
    
    /**
     * load game from database
     * @param name 
     */
    public void loadGame(String name) {
        this.game = datastore.loadGame(name);
    }

    /**
     * change panel and notify observers
     * @param panel 
     */
    public void changePanel(JPanel panel) {
        this.setChanged();
        this.notifyObservers(panel);
    }

    /**
     * @return datastore for data management purposes 
     */
    public Datastore getDataStore() {
        return datastore;
    }

    /**
     * @return array of names of available games
     */
    public String[] getGames() {
        return games;
    }

    /**
     * @return game currently being played
     */
    public Game getGame() {
        return game;
    }
    
    
    

}
