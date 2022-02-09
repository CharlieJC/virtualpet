/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pets;

import assignment.items.FoodFactory;
import assignment.items.FoodItem;
import assignment.items.ToyFactory;
import assignment.items.ToyItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author charl
 */
public class CatTest {
    
    public CatTest() {
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
     * Test of feed method, of class Cat.
     */
    @Test
    public void testFeed() {
        System.out.println("feed");
        FoodItem food = FoodFactory.create(FoodFactory.FoodType.DOG_BISCUITS);
        Cat instance = new Cat("Test");;
        boolean expResult = false;
        boolean result = instance.feed(food);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class Cat.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        ToyItem toy = ToyFactory.create(ToyFactory.ToyType.LASER);
        Cat instance = new Cat("Test");
        boolean expResult = true;
        boolean result = instance.play(toy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
