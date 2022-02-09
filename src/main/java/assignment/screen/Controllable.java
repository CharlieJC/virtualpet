/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.screen;

import java.awt.event.ActionListener;

/**
 * interface for panels that require action listeners
 * @author charlie
 */
public interface Controllable {
    
    /**
     * apply the same listener to all buttons
     * @param listener 
     */
    public void initButtons(ActionListener listener);
    
}
