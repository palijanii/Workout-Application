package model;

import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AnatomyTest {


    Anatomy aTest;

    @BeforeEach
    public void Setup() {
        aTest = new Anatomy();
    }

    @Test
    public void testAnatomy() {
        assertEquals(aTest.length(), 0);
    }


    @Test
    public void testAddWeightToEmptyList() {
        assertEquals(aTest.length(), 0);
        Weight w1 = null;
        try {
            w1 = new Weight(150.5);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        aTest.addWeight(w1);
        assertEquals(aTest.length(), 1);
    }

    @Test
    public void testAddWeightToNonEmptyList() {
        List<Weight> list = aTest.getListOfWeights();
        assertEquals(list.size(), 0);
        Weight w1 = null;
        try {
            w1 = new Weight(150.5);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Weight w2 = null;
        try {
            w2 = new Weight(130);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Weight w3 = null;
        try {
            w3 = new Weight(215.4);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        list.add(w1);
        assertEquals(aTest.length(), 1);
        list.add(w2);
        list.add(w3);
        assertEquals(aTest.length(), 3);
    }


    @Test
    public void testLengthWhenEmpty() {
       assertEquals(aTest.length(), 0);
    }

    @Test
    public void testLengthWhenNotEmpty() {
        Weight w1 = null;
        try {
            w1 = new Weight(150.5);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        aTest.addWeight(w1);
        assertEquals(aTest.length(), 1);
    }

}
