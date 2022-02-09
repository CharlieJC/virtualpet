package assignment;

import assignment.items.FoodFactory;
import assignment.items.GameItem;
import assignment.items.ToyFactory;
import assignment.items.ToyFactory.ToyType;
import assignment.pets.Pet;
import com.google.common.collect.HashMultiset;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Multiset;

/**
 * Main class that holds game objects
 *
 * @author charlie
 */
public class Game {

    private String name;
    private List<Pet> pets;
    private Multiset<GameItem> inventory;
    private int currency;

    /**
     * Constructor for creating a new game
     *
     * @param name of the game for storage purposes
     */
    public Game(String name) {
        this.name = name;
        this.pets = new ArrayList<>();
        this.inventory = HashMultiset.create();
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.DOG_BISCUITS));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.CAT_BISCUITS));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.TUNA));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.TUNA));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.RAW_BONE));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.RAW_BONE));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.RAW_BONE));
        this.inventory.add(FoodFactory.create(FoodFactory.FoodType.CHICKEN));
        this.inventory.add(ToyFactory.create(ToyType.BALL));
        this.inventory.add(ToyFactory.create(ToyType.LASER));
        this.inventory.add(ToyFactory.create(ToyType.STICK));
        this.currency = 500;

    }

    /**
     * Constructor for creating a game object from existing game objects
     *
     * @param name of the game
     * @param pets list of pets
     * @param items list of items
     */
    public Game(String name, List<Pet> pets, Multiset<GameItem> items,int currency) {
        this.name = name;
        this.pets = pets;
        this.inventory = items;
        this.currency = currency;
    }

    /**
     * @return amount of money in game
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * set new amount of money in the game
     * @param currency 
     */
    public void setCurrency(int currency) {
        this.currency = currency;
    }
    

    /**
     * add new pet to list
     * add an underscore after the name until it is unique
     * @param pet
     */
    public void addPet(Pet pet) {

        for (Pet other : pets) {
            if (other.getName().equals(pet.getName())) {
                pet.setName(pet.getName() + "_");
                addPet(pet);
                return;
            }
        }

        this.pets.add(pet);
    }

    /**
     * @return list of pets
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * @return list of all items
     */
    public Multiset<GameItem> getInventory() {
        return inventory;
    }

    /**
     * @return the games name
     */
    public String getName() {
        return name;
    }

}
