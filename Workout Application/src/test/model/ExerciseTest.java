package model;


import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    Exercise bodyWeightTest;
    Exercise machineTest;


    @BeforeEach
    public void setup() {
        try {
            bodyWeightTest = new BodyWeightExercise("Push Up",20,20);
        } catch (NegativeInputException e) {
            fail("Exception was caught");
        }
        try {
            machineTest = new MachineExercise("Barbell press", 20,20,20);
        } catch (NegativeInputException e) {
            fail("Exception was caught");
        }
    }


    @Test
    public void testGetRepsBodyWeightExercise() {
        assertEquals(20, bodyWeightTest.getReps());
        bodyWeightTest.getReps();
    }
    @Test
    public void testGetRepsMachineExercise() {
        assertEquals(machineTest.getReps(), 20);
        machineTest.getReps();
    }

    @Test
    public void testGetSetsBodyWeightExercise() {
        assertEquals(bodyWeightTest.getSets(), 20);
        bodyWeightTest.getSets();
    }

    @Test
    public void testGetSetsMachineExercise() {
        assertEquals(machineTest.getSets(), 20);
        machineTest.getSets();
    }

    @Test
    public void testSetRepsBodyWeightExerciseNoException() {
        assertEquals(bodyWeightTest.getReps(), 20);
        try {
            bodyWeightTest.setReps(30);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(bodyWeightTest.getReps(), 30);
    }

    @Test
    public void testSetRepsBodyWeightExerciseException() {
        assertEquals(bodyWeightTest.getReps(), 20);
        try {
            bodyWeightTest.setReps(-5);
            fail("Exception was not thrown");
        } catch (NegativeInputException e) {
            // do nothing
        }
        assertEquals(bodyWeightTest.getReps(), 20);
    }


    @Test
    public void testSetRepsMachineExerciseNoException() {
        assertEquals(machineTest.getReps(), 20);
        try {
            machineTest.setReps(30);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(machineTest.getReps(), 30);
    }

    @Test
    public void testSetRepsMachineExerciseException() {
        assertEquals(machineTest.getReps(), 20);
        try {
            machineTest.setReps(-10);
            fail("Exception was not thrown");
        } catch (NegativeInputException e) {
            // do nothing
        }
        assertEquals(machineTest.getReps(), 20);
    }


    @Test
    public void testGetExerciseNameBodyWeight() {
        assertEquals("Push Up", bodyWeightTest.getExerciseName());
        bodyWeightTest.getExerciseName();
    }

    @Test
    public void testGetExerciseNameMachine() {
        assertEquals("Barbell press", machineTest.getExerciseName());
        machineTest.getExerciseName();
    }

    @Test
    public void testGetWeight() {
        MachineExercise mTest = null;
        try {
            mTest = new MachineExercise("Barbell curl", 20,20,20);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(mTest.getWeight(), 20);
        mTest.getWeight();
    }

    @Test
    public void testSetWeightNoException() {
        MachineExercise mTest = null;
        try {
            mTest = new MachineExercise("Barbell curl", 20,20,20);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(mTest.getWeight(), 20);
        try {
            mTest.setWeight(40);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(mTest.getWeight(), 40);
    }

    @Test
    public void testSetWeightException() {
        MachineExercise mTest = null;
        try {
            mTest = new MachineExercise("Barbell curl", 20,20,20);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(mTest.getWeight(), 20);
        try {
            mTest.setWeight(-10);
            fail("Exception was not thrown");
        } catch (NegativeInputException e) {
            // do nothing
        }
        assertEquals(mTest.getWeight(), 20);

    }

    @Test
    public void testSetSetsBodyWeight() {
        assertEquals(bodyWeightTest.getSets(), 20);
        try {
            bodyWeightTest.setSets(40);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(bodyWeightTest.getSets(), 40);
    }


    @Test
    public void testSetSetsMachine() {
        assertEquals(machineTest.getSets(), 20);
        try {
            machineTest.setSets(40);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(machineTest.getSets(), 40);

    }

}
