/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.storage;

import assignment.Game;
import java.util.List;

/**
 * interface of methods for interacting with the chosen data store
 * @author charlie
 */
public interface Datastore {
    public List<String> savedGames();
    public boolean saveGame(Game game);
    public Game loadGame(String name);
}
