/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.storage;

import assignment.Game;
import assignment.items.FoodFactory;
import assignment.items.FoodItem;
import assignment.items.GameItem;
import assignment.items.ToyFactory;
import assignment.items.ToyItem;
import assignment.pets.Cat;
import assignment.pets.Dog;
import assignment.pets.Pet;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 * derby database implementation of Datastore
 *
 * @author charlie
 */
public class Database implements Datastore {

    private static final String URL = "jdbc:derby:VirtualPet;create=true";  //url of the DB host
    private static final String USERNAME = "pdc";  //your DB username
    private static final String PASSWORD = "pdc";   //your DB password

    private Connection conn = null;

    /**
     * create database and tables
     */
    public Database() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Exception connecting to database!");
            }
        }

        createGamesTable();
        createPetsTable();
    }

    /**
     * @return derby connection
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * create table if it doesnt exist
     */
    private void createGamesTable() {
        try {
            DatabaseMetaData databaseMetadata = conn.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, "GAMES", null);

            //if games table doesnt exist
            if (!resultSet.next()) {
                Statement create = conn.createStatement();
                create.executeUpdate("CREATE TABLE games (game_name VARCHAR(20) PRIMARY KEY, money INT, items VARCHAR(255))");
            }

        } catch (SQLException ex) {
            System.err.println("Failed to create games table!");
        }
    }

    /**
     * create table if it doesnt exist
     */
    private void createPetsTable() {
        try {
            DatabaseMetaData databaseMetadata = conn.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, "PETS", null);

            //if games table doesnt exist
            if (!resultSet.next()) {
                Statement create = conn.createStatement();
                create.executeUpdate("CREATE TABLE pets (game_name VARCHAR(20) NOT NULL, pet_name VARCHAR(20) NOT NULL, pet_type VARCHAR(20) NOT NULL, pet_happiness INT, pet_hunger INT, pet_tiredness INT, PRIMARY KEY(game_name,pet_name))");
            }

        } catch (SQLException ex) {
            System.err.println("Failed to create pets table!");
            ex.printStackTrace();
        }
    }

    /**
     * @return String list of games in database
     */
    @Override
    public List<String> savedGames() {
        List<String> games = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();
            rs = query.executeQuery("SELECT game_name FROM games");

            while (rs.next()) {
                games.add(rs.getString("game_name"));
            }

        } catch (SQLException ex) {
            System.err.println("Failed to select games!");
        }
        return games;
    }

    /**
     * save game info and pets to the games and pets tables
     *
     * @param game to save
     * @return true if successful false if fails
     */
    @Override
    public boolean saveGame(Game game) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO games VALUES (?,?,?)");
            statement.setString(1, game.getName());
            statement.setInt(2, game.getCurrency());
            statement.setString(3, itemString(game));
            statement.executeUpdate();
        } catch (SQLException ex) {
            //if duplicate key
            if (ex instanceof DerbySQLIntegrityConstraintViolationException) {
                try {
                    PreparedStatement statement = conn.prepareStatement("UPDATE games SET money=?, items=? WHERE game_name=?");
                    statement.setInt(1, game.getCurrency());
                    statement.setString(2, itemString(game));
                    statement.setString(3, game.getName());
                    statement.executeUpdate();
                } catch (SQLException ex2) {
                    System.err.println("Failed to update saved game!");
                    return false;
                }
            } else {
                System.err.println("Failed to save new game!");
                return false;
            }
        }

        for (Pet pet : game.getPets()) {
            System.out.println("saving pet: " + pet.getName());
            try {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO pets VALUES(?,?,?,?,?,?)");
                statement.setString(1, game.getName());
                statement.setString(2, pet.getName());
                statement.setString(3, pet.getType());
                statement.setInt(4, pet.getHappiness());
                statement.setInt(5, pet.getHunger());
                statement.setInt(6, pet.getTiredness());
                statement.executeUpdate();
            } catch (SQLException ex) {
                if (ex instanceof DerbySQLIntegrityConstraintViolationException) {
                    try {
                        PreparedStatement statement = conn.prepareStatement("UPDATE pets SET pet_happiness=?, pet_hunger=?, pet_tiredness=? WHERE game_name=? AND pet_name=?");
                        statement.setInt(1, pet.getHappiness());
                        statement.setInt(2, pet.getHunger());
                        statement.setInt(3, pet.getTiredness());
                        statement.setString(4, game.getName());
                        statement.setString(5, pet.getName());
                        statement.executeUpdate();
                    } catch (SQLException ex2) {
                        System.err.println("Failed to update saved pet!");
                        return false;
                    }
                } else {
                    System.err.println("Failed to save new pet!");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Load game from games and pets tables
     *
     * @param name
     * @return instance of game
     */
    @Override
    public Game loadGame(String name) {
        List<Pet> pets = new ArrayList<>();
        Multiset<GameItem> items = null;
        int money = 0;

        try {

            PreparedStatement selectGame = conn.prepareStatement("SELECT * FROM games WHERE game_name=?");
            selectGame.setString(1, name);
            ResultSet gamers = selectGame.executeQuery();

            while (gamers.next()) {
                money = gamers.getInt("money");
                items = fromItemString(gamers.getString("items"));
            }

            PreparedStatement selectPets = conn.prepareStatement("SELECT * FROM pets WHERE game_name=?");
            selectPets.setString(1, name);
            ResultSet petrs = selectPets.executeQuery();

            while (petrs.next()) {
                Pet pet = null;
                String type = petrs.getString("pet_type");
                String petName;
                int happiness, hunger, tiredness;
                if (type.equals("Dog")) {
                    petName = petrs.getString("pet_name");
                    happiness = petrs.getInt("pet_happiness");
                    hunger = petrs.getInt("pet_hunger");
                    tiredness = petrs.getInt("pet_tiredness");
                    pet = new Dog(petName, happiness, tiredness, hunger);
                } else if (type.equals("Cat")) {
                    petName = petrs.getString("pet_name");
                    happiness = petrs.getInt("pet_happiness");
                    hunger = petrs.getInt("pet_hunger");
                    tiredness = petrs.getInt("pet_tiredness");
                    pet = new Cat(petName, happiness, tiredness, hunger);
                }
                pets.add(pet);
                if (pet != null) {
                    System.out.println("Saved pet: " + pet.getName());
                }
            }

        } catch (SQLException ex) {
            System.err.println("Failed to load saved game!");
            return null;
        }

        return new Game(name, pets, items, money);

    }

    /**
     * Convert string of item types to multiset of GameItems
     *
     * @param itemsStr
     * @return multiset of GameItems
     */
    private Multiset<GameItem> fromItemString(String itemsStr) {
        Multiset<GameItem> items = HashMultiset.create();
        String[] itemNames = itemsStr.split(":");

        for (String name : itemNames) {
            boolean isFood = true;
            try {
                FoodFactory.FoodType.valueOf(name);
            } catch (IllegalArgumentException ex) {
                isFood = false;
            }

            if (isFood) {
                items.add(FoodFactory.create(FoodFactory.FoodType.valueOf(name)));
            } else {
                items.add(ToyFactory.create(ToyFactory.ToyType.valueOf(name)));
            }
        }

        return items;

    }

    /**
     * Create string of item types from game inventory
     *
     * @param game
     * @return string of item types
     */
    private String itemString(Game game) {
        StringBuilder builder = new StringBuilder();
        for (Multiset.Entry<GameItem> entry : game.getInventory().entrySet()) {
            for (int i = 1; i <= entry.getCount(); i++) {
                if (entry.getElement() instanceof FoodItem) {
                    builder.append(FoodFactory.typeFromName(entry.getElement().getName()).toString());
                    builder.append(":");
                } else if (entry.getElement() instanceof ToyItem) {
                    builder.append(ToyFactory.typeFromName(entry.getElement().getName()).toString());
                    builder.append(":");
                }
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

}
