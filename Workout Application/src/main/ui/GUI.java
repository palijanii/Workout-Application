package ui;


import model.*;
import model.exceptions.NegativeInputException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame  {
    JFrame frame;
    private static final String JSON_PATH = "./data/workout.json";
    private static JsonWriter jw;
    private static JsonReader jr;
    JFrame newWorkOutScreen;

    //Constructor

    public GUI() {
        List<WorkOut> workOuts = new ArrayList<>();

        jw = new JsonWriter(JSON_PATH);
        jr = new JsonReader(JSON_PATH);

        JButton workoutButton = createButton("Create a workout", 0);

        JButton saveButton = createButton("Save", 61);

        JButton loadButton = createButton("Load", 121);

        JButton viewWorkouts = createButton("View Workouts", 181);
        showWorkoutButtons(workOuts, viewWorkouts);

        frame = new JFrame("GymApp");
        frame.setSize(new Dimension(200, 400));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(workoutButton);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(viewWorkouts);
        frame.setVisible(true);
        workoutButton(workoutButton, workOuts);

        saveFile(workOuts, saveButton);

        loadFile(workOuts, loadButton);

        newWorkOutScreen = new JFrame("Workout Created");
        newWorkOutScreen.setSize(new Dimension(1000, 1000));
        newWorkOutScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





    }

    private void showWorkoutButtons(List<WorkOut> workOuts, JButton viewWorkouts) {
        viewWorkouts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame viewWorkoutsWindow = viewWorkoutPage("workouts created", 200, workOuts.size() * 70 + 20);

                JFrame viewExercises = viewExercisePage();

                for (int i = 0; i < workOuts.size(); i++) {
                    JButton btn = createButton(workOuts.get(i).getName(), 10 + 60 * i);
                    btn.setVisible(true);
                    viewWorkoutsWindow.add(btn);
                    int finalI = i;
                    showWorkoutTable(viewExercises, btn, finalI, workOuts);

                }
            }
        });
    }

    private void showWorkoutTable(JFrame viewExercises, JButton btn, int finalI, List<WorkOut> workOuts) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExercises.setVisible(true);
                viewExercises.setTitle(workOuts.get(finalI).getName());
                System.out.println(workOuts.get(finalI).getExercises().size());

                int row = 0;
                for (int i = 0; i < workOuts.get(finalI).getExercises().size(); i++) {
                    Exercise exercise = workOuts.get(finalI).getExercises().get(i);

                    JLabel weightLbl = new JLabel();
                    if (exercise.toString().equals("Machine Exercise")) {
                        MachineExercise me = ((MachineExercise) workOuts.get(finalI).getExercises().get(i));
                        weightLbl.setText(Double.toString(me.getWeight()));
                    } else {
                        weightLbl.setText("N/A");
                    }

                    workoutTable(i, exercise, weightLbl, viewExercises);
                }
            }
        });
    }

    private JFrame viewWorkoutPage(String s, int i2, int i3) {
        JFrame viewWorkoutsWindow = new JFrame(s);
        viewWorkoutsWindow.setSize(new Dimension(i2, i3));
        viewWorkoutsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewWorkoutsWindow.setLayout(null);
        viewWorkoutsWindow.setVisible(true);
        return viewWorkoutsWindow;
    }

    private JFrame viewExercisePage() {
        JFrame viewExercises = new JFrame();
        viewExercises.setSize(new Dimension(1000,1000));
        viewExercises.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewExercises.setLayout(null);
        viewExercises.setVisible(false);
        return viewExercises;
    }

    private void workoutTable(int i, Exercise exercise, JLabel weightLbl, JFrame viewExercises) {
        JLabel typeLbl = new JLabel(exercise.toString());
        JLabel nameLbl = new JLabel(exercise.getExerciseName());
        JLabel repsLbl = new JLabel(Integer.toString(exercise.getReps()));
        JLabel setsLbl = new JLabel(Integer.toString(exercise.getSets()));

        typeLbl.setBounds(10, 10 + 60 * i, 200, 60);
        nameLbl.setBounds(220, 10 + 60 * i, 200, 60);
        repsLbl.setBounds(330, 10 + 60 * i, 200, 60);
        setsLbl.setBounds(440, 10 + 60 * i, 200, 60);
        weightLbl.setBounds(550, 10 + 60 * i, 200, 60);
        typeLbl.setVisible(true);
        nameLbl.setVisible(true);
        repsLbl.setVisible(true);
        setsLbl.setVisible(true);
        weightLbl.setVisible(true);
        viewExercises.add(typeLbl);
        viewExercises.add(nameLbl);
        viewExercises.add(repsLbl);
        viewExercises.add(setsLbl);
        viewExercises.add(weightLbl);
    }

    //Load the saved file
    private void loadFile(List<WorkOut> workOuts, JButton loadButton) {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WorkOuts workouts = null;
                    try {
                        workouts = jr.read();
                    } catch (NegativeInputException negativeInputException) {
                        System.out.println("Invalid input");
                    }
                    workOuts.addAll(workouts.getListOfWorkOuts());
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_PATH);
                }
            }

        });
    }

    //EFFECTS: save the current workout made in program
    private void saveFile(List<WorkOut> workOuts, JButton saveButton) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jw.open();
                    jw.write(new WorkOuts(workOuts));
                    jw.close();
                    System.out.println("Your workout has been saved to " + JSON_PATH + "!");

                } catch (FileNotFoundException exception) {
                    System.out.println(exception.getMessage());
                }

            }
        });
    }

    //EFFECTS: creates a workoutButton action
    private void workoutButton(JButton workoutButton, List<WorkOut> workOuts) {
        workoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWorkoutWindow(workOuts);
                frame.setVisible(false);
            }
        });
    }

    //EFFECTS: creates a new window to name workout
    private void createWorkoutWindow(List<WorkOut> workOuts) {
        JFrame newWorkout;
        newWorkout = new JFrame("GymApp");
        newWorkout.setSize(new Dimension(400, 400));
        newWorkout.setLayout(null);
        newWorkout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newWorkout.setVisible(true);
        nameWorkoutLabel(newWorkout);
        workoutNameInput(newWorkout, workOuts);


    }

    //EFFECTS: enter name of workout
    private void workoutNameInput(JFrame newWorkout, List<WorkOut> workOuts) {
        JTextField userTypeWorkout = new JTextField(20);
        userTypeWorkout.setBounds(0, 50, 150, 30);
        userTypeWorkout.setVisible(true);
        newWorkout.add(userTypeWorkout);
        //TODO: bug: accepts null values for getText?
        if (userTypeWorkout.getText() != null) {
            userTypeWorkout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setBounds(0, 30, 300, 30);
                    panel.setVisible(true);
                    JFrame workoutCreated = newWorkoutScreen(userTypeWorkout, newWorkout);
                    printWorkoutName(workoutCreated, userTypeWorkout);
                    pickExercise(workoutCreated, workOuts);
                    JLabel chooseExType = new JLabel("Body-Weight or Machine Exercises?");
                    chooseExType.setBounds(0, 30, 300, 30);
                    chooseExType.setVisible(true);
                    workoutCreated.add(panel);
                    panel.add(chooseExType);

                    workOuts.add(new WorkOut(userTypeWorkout.getText()));


                }
            });
        }
    }

    //EFFECTS: choose type of exercise by typing allowed strings
    private void pickExercise(JFrame workoutCreated, List<WorkOut> workOuts) {


        JTextField typeExercise = new JTextField(20);
        typeExercise.setBounds(5, 70, 227, 30);
        typeExercise.setVisible(true);
        workoutCreated.add(typeExercise);
        typeExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = typeExercise.getText();
                if (input.equals("Body-Weight") || input.equals("bodyweight")
                        || input.equals("Bodyweight") || input.equals("BodyWeight") || (input.equals("bodyweight"))
                        || input.equals("body weight")) {
                    bodyWeightChosen(workoutCreated, workOuts);
                } else if (input.equals("Machine") || input.equals("machine")) {
                    machineChosen(workoutCreated, workOuts);
                }
            }
        });
    }

    //EFFECTS: Create a frame if user chooses machine exercise and allow them to name exercise, reps, sets, and weight
    //         and click enter values to process the information, or go back to starting screen
    private void machineChosen(JFrame workoutCreated, List<WorkOut> workOuts) {
        JFrame machineScreen = machineScreen(workoutCreated, "creating machine exercise");

        JTextField exNameEnter = exValues(machineScreen, "name Exercise: ", 20);

        JTextField exRepsEnter = exRepsValue(machineScreen);

        JTextField exSetsEnter = exValues(machineScreen, "Sets: ", 120);

        JTextField exWeightEnter = exValues(machineScreen, "Weight: ", 180);

        JButton enterValues = enterValuesButton(machineScreen, "Enter Values", 210);

        JButton goBack = backButton(machineScreen, "Back", 240);

        backButtonClicked(machineScreen, goBack);


        reportPopUpDisplayed(workOuts, exNameEnter, exRepsEnter, exSetsEnter, exWeightEnter, enterValues);
    }

    private void reportPopUpDisplayed(List<WorkOut> workOuts, JTextField exNameEnter, JTextField exRepsEnter,
                                      JTextField exSetsEnter, JTextField exWeightEnter, JButton enterValues) {
        enterValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reps = Integer.parseInt(exRepsEnter.getText());
                int sets = Integer.parseInt(exSetsEnter.getText());
                double weight = Double.parseDouble(exWeightEnter.getText());
                String report = exNameEnter.getText();
                report += "\nReps = " + exRepsEnter.getText();
                report += "\nSets = " + exSetsEnter.getText();
                report += "\nWeight = " + exWeightEnter.getText();

                MachineExercise newMachineEx;
                try {
                    newMachineEx = new MachineExercise(exNameEnter.getText(), reps, sets, weight);
                    workOuts.get(workOuts.size() - 1).addExercise(newMachineEx);
                    JOptionPane.showMessageDialog(null, report);
                } catch (NegativeInputException negativeInputException) {
                    String invalidInput = "Invalid input! Try again";
                    JOptionPane.showMessageDialog(null, invalidInput);
                }
                createNewMachineExercise(reps, sets, exNameEnter);
            }
        });
    }

    private void createNewMachineExercise(int reps, int sets, JTextField exNameEnter) {
        JLabel newBodyExTextName = new JLabel(exNameEnter.getText());
        newBodyExTextName.setBounds(50, 15, 150, 30);
        newBodyExTextName.setVisible(true);
        JLabel newBodyExTextReps = new JLabel(Integer.toString(reps));
        newBodyExTextReps.setBounds(60, 15, 150, 30);
        newBodyExTextReps.setVisible(true);
        JLabel newBodyExTextSets = new JLabel(Integer.toString(sets));
        newBodyExTextSets.setBounds(70, 15, 150, 30);
        JLabel newBodyExTextWeight = new JLabel(Double.toString(sets));
        newBodyExTextWeight.setBounds(70, 15, 150, 30);
        newBodyExTextWeight.setVisible(true);
        JLabel newBodyExText = new JLabel(newBodyExTextName.getText() + " " + "|" + " "
                + newBodyExTextReps.getText()
                + " " + "|" + " " + newBodyExTextSets.getText() + " " + newBodyExTextWeight.getText()
                + " " + "|" + " ");
        newBodyExText.setVisible(true);
        newWorkOutScreen.add(newBodyExText);
    }

    //EFFECTS: action performed when back button is clicked (goes back to starting screen)
    private void backButtonClicked(JFrame machineScreen, JButton goBack) {
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                machineScreen.dispose();

            }
        });
    }

    //EFFECTS: creates the enter values button for user to click and confirm the given information
    private JButton enterValuesButton(JFrame machineScreen, String s, int i) {
        JButton enterValues = new JButton(s);
        enterValues.setBounds(5, i, 150, 30);
        enterValues.setVisible(true);
        machineScreen.add(enterValues);
        return enterValues;
    }

    //EFFECTS: creates a reps text field for user to enter desired reps
    private JTextField exRepsValue(JFrame machineScreen) {
        JLabel exReps = new JLabel("Reps: ");
        exReps.setBounds(5, 70, 227, 30);
        exReps.setVisible(true);
        machineScreen.add(exReps);
        JTextField exRepsEnter = new JTextField(20);
        exRepsEnter.setBounds(100, 70, 150, 30);
        exRepsEnter.setVisible(true);
        machineScreen.add(exRepsEnter);
        return exRepsEnter;
    }

    //EFFECTS: creates name textfield for user to enter name of exercise
    private JTextField exValues(JFrame machineScreen, String s, int i) {
        JLabel exName = new JLabel(s);
        exName.setBounds(5, i, 227, 30);
        exName.setVisible(true);
        machineScreen.add(exName);
        JTextField exNameEnter = new JTextField(20);
        exNameEnter.setBounds(100, i, 150, 30);
        exNameEnter.setVisible(true);
        machineScreen.add(exNameEnter);
        return exNameEnter;
    }

    //EFFECTS: creates a frame for when user selects machine exercise
    private JFrame machineScreen(JFrame workoutCreated, String s) {
        JFrame machineScreen = new JFrame(s);
        machineScreen.setSize(new Dimension(500, 350));
        machineScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        machineScreen.setLayout(null);
        machineScreen.setVisible(true);
        workoutCreated.setVisible(false);
        return machineScreen;
    }


    //EFFECTS: adds textfields on screen for user to enter name of exercise reps sets using helper methods
    private void bodyWeightChosen(JFrame workoutCreated, List<WorkOut> workOuts) {
        JFrame bodyWeightScreen = bodyWeightScreen(workoutCreated, "creating body-weight exercise");

        JTextField exNameEnter = exValues(bodyWeightScreen, "name Exercise: ", 20);

        JTextField exRepsEnter = exValues(bodyWeightScreen, "Reps: ", 70);

        JTextField exSetsEnter = exValues(bodyWeightScreen, "Sets: ", 120);


        JButton enterValues = enterValuesButton(bodyWeightScreen, "Enter Values", 180);

        JButton goBack = backButton(bodyWeightScreen, "Back", 240);

        backButtonClicked(bodyWeightScreen, goBack);

        enterValuesClicked(workOuts, exNameEnter, exRepsEnter, exSetsEnter, enterValues);

    }

    //EFFECTS: action performed when enter values is clicked (info is processed and put into lists of workouts and
    //         exercises
    private void enterValuesClicked(List<WorkOut> workOuts, JTextField exNameEnter, JTextField exRepsEnter,
                                    JTextField exSetsEnter, JButton enterValues) {
        enterValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = exNameEnter.getText();
                report += "\nReps = " + exRepsEnter.getText();
                report += "\nSets = " + exSetsEnter.getText();
//                JOptionPane.showMessageDialog(null, report);
                int reps = Integer.parseInt(exRepsEnter.getText());
                int sets = Integer.parseInt(exSetsEnter.getText());

                BodyWeightExercise newBodyEx;
                try {
                    newBodyEx = new BodyWeightExercise(exNameEnter.getText(), reps, sets);
                    workOuts.get(workOuts.size() - 1).addExercise(newBodyEx);
                    JOptionPane.showMessageDialog(null, report);
                } catch (NegativeInputException negativeInputException) {
                    String invalidInput = "Invalid input!";
                    JOptionPane.showMessageDialog(null, invalidInput);
                }

//                workOuts.get(workOuts.size() - 1).addExercise(newBodyEx);
//                newWorkOutScreen.setVisible(true);
                //createNewBodyWeightExercise(reps, sets, exNameEnter);


            }

        });
    }

//


    //EFFECTS: creates a new window for when bodyWeight is chosen
    private JFrame bodyWeightScreen(JFrame workoutCreated, String s) {
        JFrame bodyWeightScreen = new JFrame(s);
        bodyWeightScreen.setSize(new Dimension(500, 350));
        bodyWeightScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bodyWeightScreen.setLayout(null);
        bodyWeightScreen.setVisible(true);
        workoutCreated.setVisible(false);
        return bodyWeightScreen;
    }



    //EFFECTS: creates the back button and returns a button given a frame, the string for the button, and position
    private JButton backButton(JFrame bodyWeightScreen, String back, int i) {
        JButton goBack = new JButton(back);
        goBack.setBounds(5, i, 150, 30);
        goBack.setVisible(true);
        bodyWeightScreen.add(goBack);
        return goBack;
    }

    //EFFECTS: prints the name of the workout given the users input
    private void printWorkoutName(JFrame workoutCreated, JTextField userTypeWorkout) {
        JLabel workoutNamePrinted = new JLabel(userTypeWorkout.getText());
        workoutNamePrinted.setBounds(5, 0, 150, 30);
        workoutNamePrinted.setVisible(true);
        workoutCreated.add(workoutNamePrinted);
    }

    //EFFECTS: creates a new workout screen once the user has chose a workout name
    private JFrame newWorkoutScreen(JTextField userTypeWorkout, JFrame newWorkout) {
        JFrame workoutCreated = new JFrame(userTypeWorkout.getText());
        workoutCreated.setSize(new Dimension(300, 150));
        workoutCreated.setLayout(null);
        workoutCreated.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        workoutCreated.setVisible(true);
        newWorkout.setVisible(false);
        return workoutCreated;
    }

    //EFFECTS: Creates label "Name your Workout"
    private void nameWorkoutLabel(JFrame newWorkout) {
        JLabel workoutName = new JLabel("Name your workout:");
        newWorkout.add(workoutName);
        workoutName.setVisible(true);
        workoutName.setBounds(5, 0, 200, 60);
        newWorkout.add(workoutName);
    }

    private JButton createButton(String s, int i) {
        JButton createButton;
        createButton = new JButton(s);
        createButton.setBounds(0, i, 200, 60);
        return createButton;
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//
//    }

//    public void playSound(String soundName) {
//        try {
//            AudioInputStream audioInputStream =
//            AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
//            Clip clip = AudioSystem.getClip( );
//            clip.open(audioInputStream);
//            clip.start( );
//        } catch (Exception ex) {
//            System.out.println("Error with playing sound.");
//            ex.printStackTrace( );
//        }
//    }
}




