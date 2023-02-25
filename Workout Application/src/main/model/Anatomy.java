package model;

//Class that holds weights of the user given and allows you to manipulate the weight (adding weight to listOfWeight)


import java.util.List;
import java.util.ArrayList;

public class Anatomy {
    //Fields
    List<Weight> listOfWeights;



    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates an anatomy class
    Anatomy() {

        listOfWeights = new ArrayList<Weight>();

    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds a single weight to a list of weights
    public void addWeight(Weight weight) {

        listOfWeights.add(weight);

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns a list of weights
    public List<Weight> getListOfWeights() {
        return listOfWeights;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return size of the list
    public int length() {
        return listOfWeights.size();
    }

}
