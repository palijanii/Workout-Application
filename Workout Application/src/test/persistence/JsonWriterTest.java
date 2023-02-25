package persistence;

import model.BodyWeightExercise;
import model.Exercise;
import model.WorkOut;
import model.WorkOuts;
import model.exceptions.NegativeInputException;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {


    @Test
    public void testJsonWriterOpenInvalidFile() {
        try {
            List<WorkOut> listOfWorkouts = new ArrayList<WorkOut>();
            WorkOuts testWorkouts = new WorkOuts(listOfWorkouts);
            JsonWriter jsonWriterTest = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriterTest.open();

            fail("Exception was not thrown");
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    @Test
    public void testJsonWriterOpenEmptyWorkOutFile() {
        try {
            List<WorkOut> listOfWorkouts = new ArrayList<WorkOut>();
            WorkOuts workouts = new WorkOuts(listOfWorkouts);

            JsonWriter jsonWriterTest = new JsonWriter("./data/testEmptyWorkOut.json");
            jsonWriterTest.open();
            jsonWriterTest.write(workouts);
            jsonWriterTest.close();

            JsonReader jsonReaderTest = new JsonReader("./data/testEmptyWorkOut.json");
            try {
                workouts = jsonReaderTest.read();
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }
            assertEquals(new ArrayList<>(), workouts.getListOfWorkOuts());
            assertEquals(0, workouts.getListOfWorkOuts().size());

        } catch (IOException e) {
            fail("Exception was thrown");
        }

    }


    @Test
    public void testJsonWriterOpenNonEmptyWorkOutFile() {
        try {
            WorkOuts testWorkouts = new WorkOuts(new ArrayList<>());
            WorkOut w1 = new WorkOut("Workout");
            Exercise e1 = null;
            try {
                e1 = new BodyWeightExercise("1", 1, 1);
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(e1);
            w1.setExercises(exercises);
            List<WorkOut> workOuts = new ArrayList<>();
            workOuts.add(w1);

            testWorkouts.setListOfWorkOuts(workOuts);

            JsonWriter jsonWriterTest = new JsonWriter("./data/testNonEmptyWorkOut.json");
            jsonWriterTest.open();
            jsonWriterTest.write(testWorkouts);
            jsonWriterTest.close();

            JsonReader jsonReaderTest = new JsonReader("./data/testNonEmptyWorkOut.json");
            try {
                testWorkouts = jsonReaderTest.read();
            } catch (NegativeInputException e) {
                fail("Exception was thrown");
            }
            assertEquals(testWorkouts.getListOfWorkOuts().size(), 1);
            for (WorkOut w : testWorkouts.getListOfWorkOuts()) {
                checkWorkout(w, "Workout", exercises);
            }


        } catch (IOException e) {
            fail("Exception was thrown");
        }

    }


}
