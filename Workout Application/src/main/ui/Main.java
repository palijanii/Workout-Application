package ui;

import model.exceptions.NegativeInputException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws NegativeInputException {
        new GUI();
        try {
            new GymApp();
        } catch (IOException e) {
            System.out.println("We could not find the workout you were looking for.");
        }
    }
}

