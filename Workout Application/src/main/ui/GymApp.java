package ui;

import model.*;
import model.exceptions.NegativeInputException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Represents the GymApp Application
public class GymApp {
    private static final String JSON_PATH = "./data/workout.json";
    private static JsonWriter jw;
    private static JsonReader jr;
    public static List<WorkOut> savedWorkouts;
    public static List<WorkOut> currentWorkouts;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: takes user input and creates console application
    public GymApp() throws IOException, NegativeInputException {
        List<WorkOut> workOuts = new ArrayList<>();
        List<Weight> weights = new ArrayList<>();
        savedWorkouts = new ArrayList<>();
        //workouts = new WorkOuts(savedWorkouts);

        jw = new JsonWriter(JSON_PATH);
        jr = new JsonReader(JSON_PATH);

        while (true) {

            System.out.println("1 Create Workout");
            System.out.println("2 Select Workout");
            System.out.println("3 Track Weight");
            System.out.println("4 save workout to file");
            System.out.println("5 load workout from file");
            System.out.println("6 Exit");
            System.out.print("> ");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            mainUserInputCases(workOuts, weights, input, userInput);
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: case "1": add to the listOfWorkouts by allowing user to create workouts
    //         case "2": allow user to selectWorkout given a workouts
    //         case "3": allow user add to weights to track their weight
    //         case "4": allow user to saveWorkout
    //         case "5": allow user to loadWorkout
    //         case "6" print "Have a good day" and exit.
    //         else: "Invalid entry"
    private static void mainUserInputCases(List<WorkOut> workOuts, List<Weight> weights, Scanner input, String
            userInput) throws NegativeInputException {
        switch (userInput) {
            case "1":
                workOuts.add(createWorkout(input));
                break;
            case "2":
                selectWorkout(workOuts, input);
                break;
            case "3":
                trackWeight(weights, input);
                break;
            case "4":
                saveWorkout(workOuts, input);
                break;
            case "5":
                loadWorkout(workOuts);
                break;
            case "6":
                System.out.println("Have a good day!");
                System.exit(0);

            default:
                System.out.println("Invalid entry");
                System.out.println();
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: load a given workout from a listOfWorkouts
    private static void loadWorkout(List<WorkOut> ws) throws NegativeInputException {

        try {
            WorkOuts workouts = jr.read();
            savedWorkouts = workouts.getListOfWorkOuts();

            for (WorkOut workOut : savedWorkouts) {
                ws.add(workOut);
            }

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }

    }

    //EFFECTS: allow user to select a given workout to save into JSON_PATH
    private static void saveWorkout(List<WorkOut> workOuts, Scanner input) {
        if (selectWorkOutEmpty(workOuts.isEmpty(), "No workouts have been created")) {
            return;
        }

        while (true) {
            System.out.println("Select a workout");

            int i = selectWorkOutPrintOutWorkOuts(workOuts);

            int workOutUserInput = input.nextInt();
            if (selectWorkOutInvalidEntry(i, workOutUserInput)) {
                continue;
            }

            if (workOutUserInput == i + 1) {
                break;
            }
            savedWorkouts.add(workOuts.get(workOutUserInput - 1));

            if (save()) {
                break;
            }
        }
    }

    //EFFECTS: given JSON_PATH, save workout
    //         catch FileNotFoundException
    private static boolean save() {
        try {
            jw.open();
            jw.write(new WorkOuts(savedWorkouts));
            jw.close();
            System.out.println("Your workout has been saved to " + JSON_PATH + "!");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: allow user to create a workout by entering a name for workout
    private static WorkOut createWorkout(Scanner input) {
        System.out.println();
        System.out.print("Enter a name for your workout: ");
        String name = input.nextLine();
        System.out.println();

        return new WorkOut(name);
    }

    //EFFECTS: allow user to select a workout from workouts,
    //         once workout is selected, you can add an exercise or edit
    private static void selectWorkout(List<WorkOut> workOuts, Scanner input) {
        if (selectWorkOutEmpty(workOuts.isEmpty(), "No workouts have been created")) {
            return;
        }

        while (true) {
            System.out.println("Select a workout");

            int i = 0;
            i = selectWorkOutPrintOutWorkOuts(workOuts);

            int workOutUserInput = input.nextInt();
            if (selectWorkOutInvalidEntry(i, workOutUserInput)) {
                continue;
            }

            if (workOutUserInput == i + 1) {
                break;
            }

            System.out.println();
            System.out.println("1 Add exercise");
            System.out.println("2 Edit exercise");

            int exerciseUserInput = input.nextInt();
            if (selectWorkOutInvalidEntry(i, exerciseUserInput)) {
                continue;
            }
            selectWorkOutSwitchCase(workOuts, input, i, exerciseUserInput);
        }
    }

    //EFFECTS: print out the workouts when the user has decided to select a workout
    private static int selectWorkOutPrintOutWorkOuts(List<WorkOut> workOuts) {
        int i;
        for (i = 0; i < workOuts.size(); i++) {
            System.out.println((i + 1) + " " + workOuts.get(i).getName());
        }
        System.out.println((i + 1) + " Exit");
        return i;
    }

    //EFFECTS: case where the user decides to select a workout but there are no workouts
    private static boolean selectWorkOutEmpty(boolean empty, String s) {
        if (empty) {
            System.out.println(s);
            System.out.println();
            return true;
        }
        return false;
    }

    //EFFECTS: case where the user gives an invalid entry when selecting workout
    private static boolean selectWorkOutInvalidEntry(int i, int exerciseUserInput) {
        if (exerciseUserInput > (i + 1) || exerciseUserInput < 1) {
            System.out.println("Invalid entry");
            System.out.println();
            return true;
        }
        return false;
    }

    //EFFECTS: case "1": can add an exercise to selected workout
    //         case "2": can edit an exercise in a selected workout
    private static void selectWorkOutSwitchCase(List<WorkOut> workOuts, Scanner input, int i, int exerciseUserInput) {
        switch (Integer.toString(exerciseUserInput)) {
            case "1":
                workOuts.get(i - 1).addExercise(selectExercise(input));
                break;
            case "2":
                //add edit exercise
                System.out.println("Pick an exercise to modify");
                int j = 0;
                for (j = 0; i < workOuts.get(j).getExercises().size(); j++) {
                    System.out.println((i + 1) + " " + workOuts.get(i).getExercises().get(i).getExerciseName());
                }

                System.out.println("Not implemented yet");
                break;
            default:
                System.out.println("Invalid entry");
        }
    }


    //EFFECTS: user is able to pick between types of exercises
    //         once picked either a body weight or machine, user can
    //         type the name of the exercise, the reps, sets,
    //         and weight (depending on if its a body weight or machine)
    //         cannot select reps or sets less than 1
    private static Exercise selectExercise(Scanner input) {
        System.out.println("Select a type of exercise:");
        System.out.println("1 Body-weight");
        System.out.println("2 Machine");

        input.nextLine();
        String userInput = input.nextLine();
        String exerciseInput;
        int repsInput;
        int setInput;

        while (true) {
            System.out.print("Name the exercise: ");
            exerciseInput = input.nextLine();
            System.out.print("Reps: ");
            repsInput = input.nextInt();
            System.out.print("Sets: ");
            setInput = input.nextInt();

            if (repsInput < 1 || setInput < 1) {
                System.out.println("Number of sets or reps is invalid");
            } else {
                break;
            }
        }

        return selectExerciseWhileCaseSwitchLoop(input, userInput, exerciseInput, repsInput, setInput);
    }


    //EFFECTS: case "1": User selected bodyWeight, so return a new body-weight exercise with given parameters by user
    //         case "2": user selected weight, so allow an input for weight alongside other inputs
    //                   weight cannot be less than 2.5 lbs
    private static Exercise selectExerciseWhileCaseSwitchLoop(Scanner input, String userInput, String exerciseInput,
                                                              int repsInput, int setInput) {
        while (true) {
            switch (userInput) {
                case "1":
                    try {
                        return new BodyWeightExercise(exerciseInput, repsInput, setInput);
                    } catch (NegativeInputException e) {
                        System.out.println("Invalid input");
                    }
                case "2":
                    double weightInput;

                    while (true) {
                        System.out.print("Weight: ");
                        weightInput = input.nextDouble();

                        if (weightInput < 2.5) {
                            System.out.println("Weight is too small");
                        } else {
                            break;
                        }
                    }

                    try {
                        return new MachineExercise(exerciseInput, repsInput, setInput, weightInput);
                    } catch (NegativeInputException e) {
                        System.out.println("Invalid Input");
                    }
                default:
                    System.out.println("Invalid entry");
                    System.out.println();
            }
        }
    }

    //EFFECTS: user can add their weight to a list of weights, or view their progress, or exit
    private static void trackWeight(List<Weight> weights, Scanner input) {
        while (true) {
            trackWeightOptionsPrinted("1 Add weight", "2 View weights", "3 Exit");
            String userInput = input.nextLine();
            if (trackWeightCases(weights, input, userInput)) {
                return;
            }

            input.nextLine();
        }
    }

    private static boolean trackWeightCases(List<Weight> weights, Scanner input, String userInput) {
        switch (userInput) {
            case "1":
                System.out.println("Enter a weight");
                double weight = input.nextDouble();
                try {
                    weights.add(new Weight(weight));
                } catch (NegativeInputException e) {
                    System.out.println("Invalid weight input. Try again.");
                }
                break;
            case "2":
                for (Weight w : weights) {
                    System.out.println(w.getWeight());
                }
                break;
            case "3":
                return true;
            default:
                System.out.println("invalid entry");
        }
        return false;
    }

    private static void trackWeightOptionsPrinted(String s, String s2, String s3) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
    }
}
