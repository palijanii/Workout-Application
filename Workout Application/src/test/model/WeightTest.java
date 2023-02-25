package model;

import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeightTest {

    Weight weightTest;

    @BeforeEach
    public void setup() {
        try {
            weightTest = new Weight(100);
        } catch (NegativeInputException e) {
           fail("Exception was thrown");
        }
    }

    @Test
    public void testWeight() {
        assertTrue(weightTest.getWeight() == 100);
    }


    @Test
    public void testChangeWeightOnceExceptionNotExpected() {
        assertEquals(weightTest.getWeight(), 100);
        try {
            weightTest.changeWeight(200);
        } catch (NegativeInputException e) {
            fail("Exception was thrown!");
        }
        assertEquals(weightTest.getWeight(), 200);
    }

    @Test
    public void testChangeWeightOnceExceptionExpected() {
        assertEquals(weightTest.getWeight(), 100);
        try {
            weightTest.changeWeight(-50);
            fail("Exception was not thrown");
        } catch (NegativeInputException e) {
            // do nothing
        }

    }

    @Test
    public void testChangeWeightMoreThanOnce() {
        assertEquals(weightTest.getWeight(), 100);
        try {
            weightTest.changeWeight(200);
        } catch (NegativeInputException e) {
           fail("Exception was thrown!");
        }
        assertEquals(weightTest.getWeight(), 200);
        try {
            weightTest.changeWeight(100);
        } catch (NegativeInputException e) {
            fail("Exception was thrown!");
        }
        assertEquals(weightTest.getWeight(), 100);
        try {
            weightTest.changeWeight(99.5);
        } catch (NegativeInputException e) {
            fail("Exception was thrown!");
        }
        assertEquals(weightTest.getWeight(), 99.5);
        try {
            weightTest.changeWeight(50);
        } catch (NegativeInputException e) {
            fail("Exception was thrown!");
        }
        assertEquals(weightTest.getWeight(), 50);
    }





}
