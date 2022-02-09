/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.items;

/**
 * Main toy item class
 * @author charlie
 */
public class ToyItem extends GameItem{
    
    //dog or cat
    private String typeFor;
    private int enjoyment;
    
     /**
     * main constructor for food items
     * @param name of the item
     * @param value of the item (not being used currently)
     * @param type of animal that enjoys this toy
     * @param enjoyment happiness gained from this toy
     */
    public ToyItem(String name, int value, String type, int enjoyment) {
        super(name, value);
        this.typeFor = type;
        this.enjoyment = enjoyment;
    }

    /**
     * @return string type of animal that enjoys this toy
     */
    public String getTypeFor() {
        return typeFor;
    }

    /**
     * @return amount of happiness to give to the pet
     */
    public int getEnjoyment() {
        return enjoyment;
    }
   
    

    @Override
    public String[] details() {
        return new String[]{name + ": (Toy Item)",
            "- enjoyment:" + enjoyment};
    }
    
    
    
}
