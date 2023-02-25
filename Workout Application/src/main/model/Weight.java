package model;

// A class that works with a single weight given by the user

import model.exceptions.NegativeInputException;

public class Weight {
    double weight;

    //REQUIRES: weight in pounds (LBs), weight >= 0
    //MODIFIES:
    //EFFECTS: Creates a single weight
    public Weight(double weight) throws NegativeInputException {
        if (weight <= 0) {
            throw new NegativeInputException();
        }
        this.weight = weight;
    }

    //REQUIRES: weight in pounds (LBs), weight >= 0
    //MODIFIES: this
    //EFFECTS: change a given weight
    public void changeWeight(double newWeight) throws NegativeInputException {
        if (newWeight <= 0) {
            throw new NegativeInputException();
        }
        weight = newWeight;

    }

    //REQUIRES: weight in pounds (LBs), weight >= 0
    //MODIFIES:
    //EFFECTS: returns the weight
    public double getWeight() {
        return weight;
    }
}
