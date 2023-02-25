package model;
// A body weight exercise, not able to add weight, and is a subclass of exercise

import model.exceptions.NegativeInputException;
import org.json.JSONObject;

public class BodyWeightExercise extends Exercise {

    String exerciseName;
    int reps;
    int sets;

    //REQUIRES: exercise name not null, reps and sets > 0
    //MODIFIES:
    //EFFECTS: creates a body weight exercise, and allows to input the name, reps and sets
    public BodyWeightExercise(String exerciseName, int reps, int sets) throws NegativeInputException {
        super(exerciseName, reps, sets);
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
    }

    @Override
    public int getReps() {
        return super.getReps();
    }

    @Override
    public void setReps(int reps) throws NegativeInputException {
        super.setReps(reps);
    }

    @Override
    public int getSets() {
        return super.getSets();
    }

    @Override
    public void setSets(int sets) throws NegativeInputException {
        super.setSets(sets);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", 1);
        json.put("exerciseName", exerciseName);
        json.put("reps", reps);
        json.put("sets", sets);
        return json;
    }

//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name: ", exerciseName);
//        json.put("reps: ", reps);
//        json.put("sets: ", sets);
//
//        return json;
//    }

    @Override
    public String toString() {
        return "Body Weight Exercise";
    }
}

