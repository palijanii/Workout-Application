package persistence;

import model.Exercise;
import model.WorkOut;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkWorkout(WorkOut workOut, String name, List<Exercise> expectedExercises) {
        assertEquals(name, workOut.getName());
        int counter = 0;
        for (Exercise expectedExercise : workOut.getExercises()) {
            check(expectedExercises.get(counter), expectedExercise);
            counter++;
        }
    }

    private void check(Exercise exercise, Exercise expectedExercise) {
        assertEquals(expectedExercise.getReps(), exercise.getReps());
        assertEquals(expectedExercise.getExerciseName(), exercise.getExerciseName());
        assertEquals(expectedExercise.getSets(), exercise.getSets());
    }


}
