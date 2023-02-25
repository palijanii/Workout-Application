package persistence;

import model.WorkOut;
import model.WorkOuts;
import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {




    @Test
    public void testReadWrongFile() {
        JsonReader jsonReaderTest = new JsonReader("./data/WrongFile.json");
        List<WorkOut> listOfWorkouts = new ArrayList<WorkOut>();
        WorkOuts workouts = new WorkOuts(listOfWorkouts);

        try {
            try {
                workouts = jsonReaderTest.read();
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }
            fail("Exception not thrown");
        } catch (IOException e) {
            // do nothing
        }

    }

    @Test
    public void testReadEmptyWorkout() {
        JsonReader jsonReaderTest = new JsonReader("./data/testEmptyWorkOut.json");
        List<WorkOut> listOfWorkouts = new ArrayList<WorkOut>();
        WorkOuts workouts;
        try {
            workouts = new WorkOuts(listOfWorkouts);
            try {
                workouts = jsonReaderTest.read();
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }
            assertEquals(0, workouts.getListOfWorkOuts().size());
        } catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    public void testReadNonEmptyWorkout() {
        JsonReader jsonReaderTest = new JsonReader("./data/testNonEmptyWorkOut.json");
        List<WorkOut> listOfWorkouts = new ArrayList<WorkOut>();
        WorkOuts workouts = new WorkOuts(listOfWorkouts);

        try {
            try {
                workouts = jsonReaderTest.read();
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }

        } catch (IOException e) {
            fail("Exception thrown!");
        }
    }

}
