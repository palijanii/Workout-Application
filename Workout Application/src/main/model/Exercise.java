package model;

//A single exercise, and is a SUPER class of Machine Exercises and Body Weight exercises

import model.exceptions.NegativeInputException;
import org.json.JSONObject;
import persistence.Writable;

public abstract class Exercise implements Writable  {
    //Fields
    int reps;
    int sets;
    String exerciseName;

    //CONSTRUCTOR:
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Creates a single exercise with a given num of reps and sets and weight; throw exception if
    //         reps or sets <= 0
    public Exercise(String exerciseName, int reps, int sets) throws NegativeInputException {
        if (reps <= 0 || sets <= 0) {
            throw new NegativeInputException();
        }
        this.reps = reps;
        this.sets = sets;
        this.exerciseName = exerciseName;

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return number of reps of an exercise
    public int getReps() {
        return reps;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: set (or change) the number of reps of an exercise; throw exception if reps <= 0
    public void setReps(int reps) throws NegativeInputException {
        if (reps <= 0) {
            throw new NegativeInputException();
        }
        this.reps = reps;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return number of sets of an exercise
    public int getSets() {
        return sets;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: set (or change) the number of sets of an exercise; throw exception if sets <= 0
    public void setSets(int sets) throws NegativeInputException {
        if (sets <= 0) {
            throw new NegativeInputException();
        }
        this.sets = sets;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns exercise name
    public String getExerciseName() {
        return exerciseName;
    }

    @Override
    public abstract JSONObject toJson();



}

