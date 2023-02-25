package model;
// A machine exercise class (able to add weight to the exercise), and is a subclass of exercise

import model.exceptions.NegativeInputException;
import org.json.JSONObject;

public class MachineExercise extends Exercise {
    String exerciseName;
    int reps;
    int sets;
    double weight;

    //REQUIRES: machineName != null, int reps > 0, int sets > 0, weight (unit LBs) > 0
    //MODIFIES:
    //EFFECTS:
    public MachineExercise(String exerciseName, int reps, int sets, double weight) throws NegativeInputException {
        super(exerciseName, reps, sets);
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return the weight of an exercise
    public double getWeight() {
        return weight;
    }

    //REQUIRES: weight > 0
    //MODIFIES:
    //EFFECTS: set (or change) the weight of an exercise
    public void setWeight(double weight) throws NegativeInputException {
        if (weight <= 0) {
            throw new NegativeInputException();
        }
        this.weight = weight;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", 2);
        json.put("exerciseName", exerciseName);
        json.put("reps", reps);
        json.put("sets", sets);
        json.put("weight", weight);
        return json;
    }

//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name: ", exerciseName);
//        json.put("reps: ", reps);
//        json.put("sets: ", sets);
//        json.put("weight: ", weight);
//
//        return json;
//    }


    @Override
    public String toString() {
        return "Machine Exercise";
    }
}
