/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import assignment.pets.Dog;
import assignment.pets.Pet;
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
public class GameTest {
    public GameTest() {
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
     * Test of addPet method, of class Game.
     * Check if underscore feature works
     */
    @Test
    public void testAddPet() {
        System.out.println("addPet");
        Pet pet = new Dog("first");
        Pet pet2 = new Dog("first");
        Game instance = new Game("test");
        instance.addPet(pet);
        instance.addPet(pet2);
        
        boolean expResult = true;
        boolean result = pet2.getName().equals("first_");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
