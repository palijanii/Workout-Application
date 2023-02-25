package model;

import model.WorkOut;

import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkOutTest {

    WorkOut wTest;


    @BeforeEach
    public void setup() {
        wTest = new WorkOut("workout 1");
    }

    @Test
    public void testWorkOut() {
        WorkOut wTest1 = new WorkOut("workout 1");
        assertTrue(wTest1.getExercises() != null);
    }


    @Test
    public void testAddMachineExerciseToEmptyWorkOutList() {
        Exercise e1 = null;
        try {
            e1 = new MachineExercise("Barbell Bench Press", 8,3,50.5);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(wTest.getExercises().size(),0);
        wTest.addExercise(e1);
        assertEquals(wTest.getExercises().size(), 1);

    }

    @Test
    public void testAddMachineExercisesToNonEmptyWorkOutList() {
        Exercise e1 = null;
        try {
            e1 = new MachineExercise("Barbell Bench Press", 8,3,50.5);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Exercise e2 = null;
        try {
            e2 = new MachineExercise("Incline Barbell Bench Press", 12,3,45.25);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Exercise e3 = null;
        try {
            e3 = new MachineExercise("Tricep cable pull-down", 10,4,33);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        wTest.addExercise(e1);
        assertEquals(wTest.getExercises().size(),1);
        wTest.addExercise(e2);
        wTest.addExercise(e3);
        assertEquals(wTest.getExercises().size(), 3);
    }


    @Test
    public void testAddBodyWeightExerciseToEmptyWorkOutList() {
        Exercise e1 = null;
        try {
            e1 = new BodyWeightExercise("Push Up", 30,2);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        assertEquals(wTest.getExercises().size(),0);
        wTest.addExercise(e1);
        assertEquals(wTest.getExercises().size(), 1);

    }

    @Test
    public void testAddBodyWeightExercisesToNonEmptyWorkOutList() {
        Exercise e1 = null;
        try {
            e1 = new BodyWeightExercise("Push Up", 30,2);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Exercise e2 = null;
        try {
            e2 = new BodyWeightExercise("Tricep Dips", 30,2);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        Exercise e3 = null;
        try {
            e3 = new BodyWeightExercise("Pull Up", 30,2);
        } catch (NegativeInputException e) {
            fail("Exception was thrown");
        }
        wTest.addExercise(e1);
        assertEquals(wTest.getExercises().size(),1);
        wTest.addExercise(e2);
        wTest.addExercise(e3);
        assertEquals(wTest.getExercises().size(), 3);
    }


    @Test
    public void testGetName() {
        assertEquals(wTest.getName(), "workout 1");
        wTest.getName();
    }



}