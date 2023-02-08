package ui;

import model.Diary;
import model.Food;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String QUIT_COMMAND = "quit";
    private static final String VIEW_COMMAND = "view";

    private boolean running;
    private Scanner input;
    private Diary diary;

    public Menu() {
        diary = new Diary();
        input = new Scanner(System.in);
        running = true;
    }

    // EFFECTS: Starts the program
    public void startProgram() {
        printMainMenuInstructions();
        handleUserInput();
    }

    // EFFECTS: Closes the program
    public void closeProgram() {
        input.close();
        running = false;
    }

    // Code is based on the handleUserInput method from the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: parses user input until user quits
    public void handleUserInput() {
        String command;

        while (running) {
            if (input.hasNext()) {
                command = input.nextLine().toLowerCase().trim();
                parseMainMenuInput(command);
            }
        }
    }

    // Code is based on the parseInput method from the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: Prints the menu options for the main menu based on the input command
    public void parseMainMenuInput(String command) {
        if (command.length() > 0) {
            switch (command) {
                case QUIT_COMMAND:
                    closeProgram();
                    break;
                case VIEW_COMMAND:
                    break;
                default:
                    System.out.println("Sorry, that command isn't recognized. Please try again!");
            }
        }
    }

    // EFFECTS: Prints the instructions for the main menu
    public void printMainMenuInstructions() {
        System.out.println("Please enter 'view' to view your daily logs or 'quit' to exit the program:");
    }

    // EFFECTS: Prints the name of the given food and all of its nutritional values
    public void printFood(Food food) {
        System.out.println("Name: " + food.getName());
        System.out.println("Calories: " + food.getCalories());
        System.out.println("Grams of fat: " + food.getFat());
        System.out.println("Grams of sugar: " + food.getSugar());
        System.out.println("Grams of protein: " + food.getProtein());
    }

    // EFFECTS: Prints out all the foods eaten in the given and their nutritional information
    public void printFoodLog(List<Food> log) {
        for (Food food : log) {
            printFood(food);
            System.out.println();
        }
    }
}