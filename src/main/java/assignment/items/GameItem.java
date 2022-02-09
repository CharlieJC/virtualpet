/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.items;

import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 * structure all game items are required to follow
 *
 * @author charlie
 */
public abstract class GameItem {

    protected final String name;

    protected final int value;

    /**
     * collect name and value of all items, ensure name is between 1-15
     * characters
     *
     * @param name
     * @param value
     */
    public GameItem(String name, int value) {
        Preconditions.checkArgument(name.length() <= 15 || name.length() >= 1, "Item names must be between 1-15 characters");
        this.name = name;
        this.value = value;
    }

    /**
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return value of the item
     */
    public int getValue() {
        return value;
    }


   
    

    /**
     * @return list of details about an item
     */
    public abstract String[] details();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return obj instanceof GameItem
                && ((GameItem) obj).name.equals(this.name)
                && ((GameItem) obj).value == this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for (String s : details()) {
            builder.append(s);
            builder.append(System.lineSeparator());
        }
        
        return builder.toString();
        
    }
    
    

}
