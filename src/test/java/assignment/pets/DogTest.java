/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pets;

import assignment.items.FoodFactory;
import assignment.items.FoodFactory.FoodType;
import assignment.items.FoodItem;
import assignment.items.ToyFactory;
import assignment.items.ToyFactory.ToyType;
import assignment.items.ToyItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author charl
 */
public class DogTest {
    
    public DogTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of feed method, of class Dog.
     */
    @Test
    public void testFeed() {
        System.out.println("feed");
        FoodItem food = FoodFactory.create(FoodType.DOG_BISCUITS);
        Dog instance = new Dog("Test");;
        boolean expResult = true;
        boolean result = instance.feed(food);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class Dog.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        ToyItem toy = ToyFactory.create(ToyType.LASER);
        Dog instance = new Dog("Test");
        boolean expResult = false;
        boolean result = instance.play(toy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
