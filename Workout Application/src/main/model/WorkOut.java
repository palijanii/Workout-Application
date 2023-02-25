package model;

//class that holds exercises and allows to add exercises, get a list of exercises, or get a single exercise

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class WorkOut implements Writable {
    //Fields
    String name;
    List<Exercise> exercises;
    List<WorkOut> listOfWorkOuts;

    //CONSTRUCTOR
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Creates a workout
    public WorkOut(String name) {
        this.name = name;
        exercises = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds a single exercise to the list of exercises
    //         most recent added exercise goes to the end of the list
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns a list of exercises
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns name of workout
    public String getName() {
        return name;
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("exercises", exercisesToJson());


        return json;
    }


    // EFFECTS: returns exercises in a listOfExercises as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : getExercises()) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


}
