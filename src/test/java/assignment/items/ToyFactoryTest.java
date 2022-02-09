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
public class ToyFactoryTest {
    
    public ToyFactoryTest() {
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
     * Test of create method, of class ToyFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        ToyFactory.ToyType type = ToyFactory.ToyType.BALL;
        ToyItem expResult = new ToyItem("Ball", 20, "Dog", 30);
        ToyItem result = ToyFactory.create(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of typeFromName method, of class ToyFactory.
     */
    @Test
    public void testTypeFromName() {
        System.out.println("typeFromName");
        String name = "Ball";
        ToyFactory.ToyType expResult = ToyFactory.ToyType.BALL;
        ToyFactory.ToyType result = ToyFactory.typeFromName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
