/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.items;

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
public class FoodFactoryTest {
    
    public FoodFactoryTest() {
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
     * Test of create method, of class FoodFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        FoodFactory.FoodType type = FoodFactory.FoodType.CHICKEN;
        FoodItem expResult = new FoodItem("Chicken", 15, 10, 10);
        FoodItem result = FoodFactory.create(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of typeFromName method, of class FoodFactory.
     */
    @Test
    public void testTypeFromName() {
        System.out.println("typeFromName");
        String name = "Chicken";
        FoodFactory.FoodType expResult = FoodFactory.FoodType.CHICKEN;
        FoodFactory.FoodType result = FoodFactory.typeFromName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
