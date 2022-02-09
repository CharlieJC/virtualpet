package assignment.items;

/**
 * This factory class creates instances of the different types of food
 *
 * @author charlie
 */
public class FoodFactory {

    /**
     * types of food available in game
     */
    public enum FoodType {

        DOG_BISCUITS {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Dog Biscuits", 20, 20, 5);
            }

        },
        CAT_BISCUITS {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Cat Biscuits", 20, 20, 5);
            }

        },
        TUNA {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Tuna", 15, 5, 10);
            }

        },
        CHICKEN {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Chicken", 15, 10, 10);
            }

        },
        RAW_BONE {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Raw Bone", 10, 2, 10);
            }

        },
        SALMON {
            @Override
            public FoodItem getInstance() {
                return new FoodItem("Salmon", 15, 10, 10);
            }

        };

        public abstract FoodItem getInstance();

    }

    /**
     * Create an instance of FoodItem for the type of food provided
     * @param type of food
     * @return FoodItem instance
     */
    public static FoodItem create(FoodType type) {
        return type.getInstance();
    }
    
    /**
     * @param name readable name of item
     * @return FoodType that contains the readable name in its instance
     */
    public static FoodType typeFromName(String name) {
        for (FoodType type : FoodType.values()) {
            if (type.getInstance().getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

}
