/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pets;

import assignment.items.FoodItem;
import assignment.items.ToyItem;
import assignment.items.FoodFactory.FoodType;
import java.util.List;

/**
 * Structure pets must follow
 *
 * @author charlie
 */
public abstract class Pet {

    private String name;
    protected String type;
    //age in months, sleeptime in hours
    protected int happiness, tiredness, hunger;
    protected List<FoodType> cantEat;

    /**
     * constructor for creating new pets filling default values
     *
     * @param name of the pet
     */
    public Pet(String name) {
        this.name = name;
        this.happiness = 50;
        this.tiredness = 25;
        this.hunger = 50;
        this.type = "Unknown";
    }

    /**
     * constructor for creating pets from existing values
     *
     * @param name of the pet
     * @param happiness of the pet
     * @param tiredness of the pet
     * @param hunger of the pet
     * @param type of pet
     */
    public Pet(String name, int happiness, int tiredness, int hunger, String type) {
        this.name = name;
        this.happiness = happiness;
        this.tiredness = tiredness;
        this.hunger = hunger;
        this.type = type;
    }

    /**
     * Change a pets name
     *
     * @param name of the pet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param food item to feed the pet
     * @return whether the pet successfully ate it
     */
    public abstract boolean feed(FoodItem food);

    /**
     * @param toy for pet to play with
     * @return whether the pet enjoyed the toy
     */
    public abstract boolean play(ToyItem toy);

    /**
     * @return name of pet
     */
    public String getName() {
        return name;
    }

    /**
     * @return type of pet
     */
    public String getType() {
        return type;
    }

    /*
    increase tiredness;
     */
    public void gettingTired(int amount) {
        tiredness += amount;
    }

    /*
    increase hunger;
     */
    public void gettingHungry(int amount) {
        hunger += amount;
    }

    /**
     * @return happiness of pet
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * @return tiredness of pet
     */
    public int getTiredness() {
        return tiredness;
    }

    /**
     * @return hunger of pet
     */
    public int getHunger() {
        return hunger;
    }

}
