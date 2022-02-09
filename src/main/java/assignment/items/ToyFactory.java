/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.items;

/**
 * This factory class creates instances of the different types of food
 *
 * @author charlie
 */
public class ToyFactory {

    /**
     * available types of toys in game
     */
    public enum ToyType {
        STICK {
            @Override
            public ToyItem getInstance() {
                return new ToyItem("Stick", 5, "Dog", 10);
            }

        },
        BALL {
            @Override
            public ToyItem getInstance() {
                return new ToyItem("Ball", 20, "Dog", 30);
            }

        },
        LASER {
            @Override
            public ToyItem getInstance() {
                return new ToyItem("Laser", 30, "Cat", 30);
            }

        };

        public abstract ToyItem getInstance();
    }

    /**
     * creates a ToyItem instance provided by the type
     * @param type ToyType of item
     * @return ToyItem instance
     */
    public static ToyItem create(ToyType type) {
        return type.getInstance();
    }

    /**
     * 
     * @param name readable name of item
     * @return ToyType that contains the readable name in its instance
     */
    public static ToyType typeFromName(String name) {
        for (ToyType type : ToyType.values()) {
            if (type.getInstance().getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
