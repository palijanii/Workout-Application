#GymApp

The **GymApp** is an application that allows the user
to keep track of their workouts by adding the workouts, 
the sets, reps, and weight, in order to track
progressive overload. The user will also
be able to track their own individual weight over time as well as 
measurements such as arm, belly, thigh, waist, etc.

-

# User Stories

- As a user, I want to start a workout
- As a user, I want to be able to add exercises to my workout
- As a user, I want to choose sets, reps, weight for my exercises
 and rest time in between
- As a user, I want to enter my current weight on a given day
- As a user, I want to be able to save my to-do list to file
= As a user, I want to be able to load my to-do list from file
-


# Phase 4: Task 2
Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.

The weight class is a class where user is able to input their body weight in order to keep track of that aspect of their health.
I have added an InvalidWeightException which takes care of the requirement for the weight to be greater than 0. The class has been tested and tests are passing
There is a case for when the exception is expected and another where the exception is not expected
Class: Weight
Method: Constructor, and changeWeight()

As well as the Exercise class. This is a superclass to machine and bodyweight exercises
I have created an exception to be thrown for negative input values for reps and sets
The user is not able to put a negative input for reps sets in gui as well as console




# Phase 4: Task 3
The UML I have drawn displays 
the associations between classes in this GymApp
First off you can see the association between
WorkOut and Exercise. A WorkOut can consist of 
0 to many exercises. This exercise then is implemented 
using writable in order to override the toJson method
that is to be able to read and write files to function
for saving and loading purposes. Exercise is a super class
to BodyWeightExercises as well as MachineExercises.
When I first made this program, I initially found many differences
between the two types of exercises, hence why I made
an abstract form. However, now realizing, it is not necessary
that I have them extend Exercise as a whole, because merely
the only difference between the two is a weight parameter
that machine exercises takes. Had I had more time, I would
get rid of this functionality, as it is not useful, and makes code
in many places complicated to differentiate between the two types.
I would also refactor my console and GUI code alot more neater, because
at the moment it is hard to read and you could argue there is cohesion issues / coupling. 
I also have a class called WorkOuts that consists of many made workouts.
This also seems too much cohesion as WorkOuts could consist in the WorkOut class. On the other hand
I have two classes that are different in functionality from my WorkOut section:
This section deals with the users weight progress: are they getting bulker/skinnier
This functionality is maintained between Anatomy and Weight. Had I had more time, 
I would implement this into the Gui and allow a table to keep track of the users Weight
on a daily basis.

To clarify: The refactoring I would do is keep WorkOuts in WorkOut class rather than create a new class for it
As well as get rid of the BodyWeightExercise and MachineExercise and keep only the Exercise class, as it is not useful
On the other hand, I would also get rid of alot of duplication in the GUI class.
There is a lot of duplication considering creating buttons, frames, labels, etc. I would create classes that would produce
these JFrame aspects, and use those in my code in order to create new objects for it. 