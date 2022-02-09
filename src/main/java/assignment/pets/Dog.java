/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pets;

import assignment.items.FoodItem;
import assignment.items.FoodFactory.FoodType;
import assignment.items.ToyItem;
import java.util.Arrays;

/**
 * class for pet dogs
 * @author charlie
 */
public class Dog extends Pet {

    /**
     * constructor to create new dogs from default values
     * @param name 
     */
    public Dog(String name) {
        super(name);
        this.type = "Dog";
        this.cantEat = Arrays.asList(new FoodType[]{FoodType.CAT_BISCUITS});
    }

    /**
     * constructor to create dog from existing values
     * @param name of the dog
     * @param happiness of the dog
     * @param tiredness of the dog
     * @param hunger of the dog
     */
    public Dog(String name, int happiness, int tiredness, int hunger) {
        super(name);
        this.happiness = happiness;
        this.tiredness = tiredness;
        this.hunger = hunger;
        this.type = "Dog";
        this.cantEat = Arrays.asList(new FoodType[]{FoodType.CAT_BISCUITS});
    }

    /**
     * Check if dog can eat the given food
     * @param food to feed the dog
     * @return whether dog ate the food
     */
    @Override
    public boolean feed(FoodItem food) {
        for (FoodType type : this.cantEat) {
            if (type.toString().replace("_", " ").equalsIgnoreCase(food.getName())) {
                System.out.println("You can't feed dogs " + food.getName());
                return false;
            }
        }

        System.out.println(food.getName() + "'s happiness increased and hunger decreased!");
        this.hunger -= food.getNutrition();
        this.happiness += food.getTastiness();
        return true;
    }

    /**
     * Check if dog can eat the given food
     *
     * @param toy too play with the dog
     * @return whether dog played with the toy
     */
    @Override
    public boolean play(ToyItem toy) {
        if (this.type.equals(toy.getTypeFor())) {
            this.happiness += toy.getEnjoyment();
            System.out.println(this.getName() + "'s happiness increased!");
            return true;
        } else {
            System.out.println(toy.getName() + " is not a " + this.getType() + " toy!");
            return false;
        }
    }

}
