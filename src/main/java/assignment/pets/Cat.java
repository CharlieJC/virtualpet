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
 * class for pet cats
 *
 * @author charlie
 */
public class Cat extends Pet {

    /**
     * constructor to create new cats from default values
     *
     * @param name
     */
    public Cat(String name) {
        super(name);
        this.type = "Cat";
        this.cantEat = Arrays.asList(new FoodType[]{FoodType.DOG_BISCUITS});

    }

    /**
     * constructor to create cats from existing values
     *
     * @param name of the cat
     * @param happiness of the cat
     * @param tiredness of the cat
     * @param hunger of the cat
     */
    public Cat(String name, int happiness, int tiredness, int hunger) {
        super(name);
        this.happiness = happiness;
        this.tiredness = tiredness;
        this.hunger = hunger;
        this.type = "Cat";
        this.cantEat = Arrays.asList(new FoodType[]{FoodType.DOG_BISCUITS});
    }

    /**
     * Check if cat can eat the given food
     *
     * @param food to feed the cat
     * @return whether cat ate the food
     */
    @Override
    public boolean feed(FoodItem food) {
        for (FoodType type : this.cantEat) {
            if (type.toString().replace("_", " ").equalsIgnoreCase(food.getName())) {
                System.out.println("You can't feed cats " + food.getName());
                return false;
            }
        }

        System.out.println(this.getName() + "'s happiness increased and hunger decreased!");
        this.hunger -= food.getNutrition();
        this.happiness += food.getTastiness();
        return true;
    }

    /**
     * Check if cat can eat the given food
     *
     * @param toy too play with the cat
     * @return whether cat played with the toy
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
