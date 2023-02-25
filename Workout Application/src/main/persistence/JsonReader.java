package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.exceptions.NegativeInputException;
import org.json.*;

// Represents a reader that reads workouts and exercises from JSON data stored in file
public class JsonReader {
    private String source;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: reads workout from file and returns it;
    //         throws IOException if file can't be read / found
    public WorkOuts read() throws IOException, NegativeInputException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkOuts(jsonObject);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: reads the source from file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }


        return contentBuilder.toString();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: parses workouts from JSON Object and returns it
    public WorkOuts parseWorkOuts(JSONObject jsonObject) throws NegativeInputException {
        ArrayList<WorkOut> listOfWorkouts = new ArrayList<>();
        JSONArray jsonArrayOfWorkouts = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArrayOfWorkouts) {
            JSONObject nextWorkout = (JSONObject) json;
            WorkOut workOut = parseWorkOut(nextWorkout);
            listOfWorkouts.add(workOut);
        }

        WorkOuts workOuts = new WorkOuts(listOfWorkouts);
        return workOuts;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: parses a single workout from JSON Object and returns it
    private WorkOut parseWorkOut(JSONObject jsonObject) throws NegativeInputException {
        List<Exercise> listOfExercise = new ArrayList<>();
        String name = jsonObject.getString("name");
        WorkOut workout = new WorkOut(name);

        JSONArray jsonArrayOfExercise = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArrayOfExercise) {
            JSONObject nextExercise =  (JSONObject) json;
            Exercise exercise = parseExercise(nextExercise);
            listOfExercise.add(exercise);
        }
        return workout;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: parses an exercise from JSON Object and returns it
    private Exercise parseExercise(JSONObject jsonObject) throws NegativeInputException {
        String name = jsonObject.getString("exerciseName");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int type = jsonObject.getInt("type");
        if (type == 1) {
            return new BodyWeightExercise(name, reps, sets);
        } else {
            double weight = jsonObject.getDouble("weight");
            return new MachineExercise(name, reps, sets, weight);
        }
    }
//
//
//    private void addExercises(WorkOut workout, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
//        for (Object json: jsonArray) {
//            JSONObject nextExercise = (JSONObject) json;
//            addExercise(workout, nextExercise);
//        }
//    }
//
//    private void addExercise(WorkOut workout, JSONObject jsonObject) {
//        String name = jsonObject.getString("exerciseName");
//        int sets = jsonObject.getInt("sets");
//        int reps = jsonObject.getInt("reps");
//        int type = jsonObject.getInt("type");
//        if (type == 1) {
//            BodyWeightExercise bodyWeightMachineExercise = new BodyWeightExercise(name, reps, sets);
//            workout.addExercise(bodyWeightMachineExercise);
//        } else if (type == 2) {
//            double weight = jsonObject.getDouble("weight");
//            MachineExercise machineExercise = new MachineExercise(name, reps, sets, weight);
//            workout.addExercise(machineExercise);
//        }
//    }
}
