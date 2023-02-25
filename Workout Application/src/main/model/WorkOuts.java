package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// class that has has multiple workouts in a listOfWorkouts
public class WorkOuts implements Writable {
    List<WorkOut> listOfWorkOuts;


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: constructs WorkOuts given a listOfWorkouts
    public WorkOuts(List<WorkOut> listOfWorkOuts) {
        this.listOfWorkOuts = listOfWorkOuts;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the listOfWorkouts
    public List<WorkOut> getListOfWorkOuts() {
        return listOfWorkOuts;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workouts", workoutsToJson());
        return json;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the workouts in a listOfWorkouts as JSON Array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkOut workout : listOfWorkOuts) {
            jsonArray.put(workout.toJson());
        }
        return jsonArray;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: sets the listOfWorkOuts to given parameter
    public void setListOfWorkOuts(List<WorkOut> listOfWorkOuts) {
        this.listOfWorkOuts = listOfWorkOuts;
    }
//
//
//    // EFFECTS: returns exercises in a listOfExercises as a JSON array
//    private JSONArray exercisesToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Exercise e : getExercises()) {
//            jsonArray.put(e.toJsonExercise());
//        }
//
//        return jsonArray;
//    }
}
