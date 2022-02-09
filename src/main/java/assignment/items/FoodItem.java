
package assignment.items;

/**
 * Main food item class
 * @author charlie
 */
public class FoodItem extends GameItem {

    private int nutrition, tastiness;

    /**
     * main constructor for food items
     * @param name of the item
     * @param value of the item (not being used currently)
     * @param nutrition how much it feeds the pet
     * @param tastiness how much happiness the pet gains
     */
    public FoodItem(String name, int value, int nutrition, int tastiness) {
        super(name, value);
        this.nutrition = nutrition;
        this.tastiness = tastiness;
    }

    /**
     * @return nutritional value of food item
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * @return tastiness of food item
     */
    public int getTastiness() {
        return tastiness;
    }

    /**
     * @return structured list of information about this item
     */
    @Override
    public String[] details() {
        return new String[]{name + ": (Food Item)",
            "- nutrition:" + nutrition,
            "- tastiness:" + tastiness};
    }

    
}
