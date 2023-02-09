package ui;

import model.DailyLog;
import model.Diary;
import model.Food;

import java.util.Scanner;

public class Menu {

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
        parseMainMenuInput();
    }

    // EFFECTS: Closes the program
    private void closeProgram() {
        input.close();
        System.out.println("Quitting Program");
        running = false;
    }

    @SuppressWarnings("methodlength")
    // Code is based on the parseInput method in the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: Prints the main menu options and waits for the user's input
    //          to continue program based on the command
    private void parseMainMenuInput() {
        while (running) {
            printDiary(diary);
            printMainMenuInstructions();

            switch (input.nextLine().trim()) {
                case "1":
                    handleAddNewDailyLog();
                    break;
                case "2":
                    handleRemoveDailyLog();
                    break;
                case "3":
                    handleViewDailyLog();
                    break;
                case "4":
                    printAverageNutritionalValues();
                    break;
                case "5":
                    closeProgram();
                    break;
                default:
                    System.out.println("Sorry, that command isn't recognized so please try again! \n");
                    break;
            }
        }
    }

    // EFFECTS: Prints the daily log viewing and editing menu options and waits for the user's input
    //          to continue program based on the command
    private void parseDailyLogMenuInput(DailyLog dailyLog) {
        dailyLogMenu:
        while (running) {
            printDailyLog(dailyLog);
            printDailyLogMenuInstructions();

            switch (input.nextLine().trim()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    handleViewFood();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    System.out.println("Going back to main menu! \n");
                    break dailyLogMenu;
                default:
                    System.out.println("Sorry, that command isn't recognized. Please try again!\n");
            }
        }
    }

    // EFFECTS: Prints the food menu options and waits for the user's input
    //          to continue program based on the command
    private void parseFoodMenuInput(Food food) {
        foodMenu:
        while (running) {
            printFood(food);
            printFoodMenuInstructions();

            switch (input.nextLine().trim()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Going back to the daily log menu! \n");
                    break foodMenu;
                default:
                    System.out.println("Sorry, that command isn't recognized. Please try again!\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a new daily log with prompts and user inputs
    private void handleAddNewDailyLog() {
        try {
            int year;
            int month;
            int day;

            System.out.println("Please enter the year:");
            year = input.nextInt();

            System.out.println("Please enter the month:");
            month = input.nextInt();

            System.out.println("Please enter the day:");
            day = input.nextInt();

            diary.addDailyLog(new DailyLog(year, month, day));
            System.out.println("Successfully added! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes a daily log with prompts and user inputs
    private void handleRemoveDailyLog() {
        try {
            System.out.println("Please type the number of the daily log to delete:");
            int dailyLogToRemove = input.nextInt() - 1;

            diary.removeDailyLog(dailyLogToRemove);
            System.out.println("Successfully deleted! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // TODO
    // EFFECTS: View a daily log and edit it with prompts and user inputs
    private void handleViewDailyLog() {
        try {
            System.out.println("Please type the number of the daily log to view or edit");
            int dailyLogToView = input.nextInt() - 1;

            input.nextLine();
            parseDailyLogMenuInput(diary.getDailyLogs().get(dailyLogToView));

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
        }
    }

    // TODO
    // EFFECTS: View a food and edit it with prompts and user inputs
    private void handleViewFood() {
        // stub
    }

    // EFFECTS: Prints the prompts for the main menu
    private void printMainMenuInstructions() {
        System.out.println("\nEnter 1 to add an entry to your diary");
        System.out.println("Enter 2 to remove an entry from your diary");
        System.out.println("Enter 3 to view or edit an entry in your diary");
        System.out.println("Enter 4 to view average nutritional intake");
        System.out.println("Enter 5 to quit the program");
    }

    // EFFECTS: Prints the prompts for the daily log viewing and editing menu
    private void printDailyLogMenuInstructions() {
        System.out.println("\nEnter 1 to add a new food to this daily log");
        System.out.println("Enter 2 to remove a new food to this daily log");
        System.out.println("Enter 3 to view or edit a food in this daily log");
        System.out.println("Enter 4 to change the date of this daily log");
        System.out.println("Enter 5 to view total nutritional intake from this daily log");
        System.out.println("Enter 6 to go back to the main menu");
    }

    // EFFECTS: Prints the prompts for the food viewing and editing menu
    private void printFoodMenuInstructions() {
    }

    // EFFECTS: Prints out the dairy and all daily logs or an empty message if there are no daily logs
    private void printDiary(Diary diary) {
        System.out.println("Your diary:");

        if (diary.getDailyLogs().size() == 0) {
            System.out.println("You have no daily logs!");
        } else {
            int counter = 1;

            for (DailyLog dailyLog : diary.getDailyLogs()) {
                System.out.println("(" + counter + ")");
                counter++;

                printDailyLogPreview(dailyLog);
            }
        }
    }

    // EFFECTS: Prints out the given daily log with its date and list of names of foods eaten
    private void printDailyLogPreview(DailyLog dailyLog) {
        System.out.println("Daily log of " + dailyLog.getDate());

        if (dailyLog.getFoodLog().size() == 0) {
            System.out.println("No eaten foods recorded yet!");
        } else {
            int counter = 1;

            for (Food food : dailyLog.getFoodLog()) {
                System.out.println("(" + counter + ")");
                counter++;

                System.out.println(" - " + food.getName());
            }
        }
    }

    // EFFECTS: Prints out the given daily log with its date
    //          and record of foods eaten along with their nutritional information
    private void printDailyLog(DailyLog dailyLog) {
        System.out.println("Daily log of " + dailyLog.getDate() + ":");

        if (dailyLog.getFoodLog().size() == 0) {
            System.out.println("No eaten foods recorded yet!");
        } else {
            int counter = 1;

            for (Food food : dailyLog.getFoodLog()) {
                System.out.println("(" + counter + ")");
                counter++;

                printFood(food);
                System.out.println();
            }
        }
    }

    // EFFECTS: Prints the name of the given food along with all of its nutritional values
    private void printFood(Food food) {
        System.out.println("Name: " + food.getName());
        System.out.println("Calories: " + food.getCalories());
        System.out.println("Grams of fat: " + food.getFat());
        System.out.println("Grams of sugar: " + food.getSugar());
        System.out.println("Grams of protein: " + food.getProtein());
    }

    // EFFECTS: Prints the average nutritional values
    private void printAverageNutritionalValues() {
        System.out.println("Average calories per day: " + diary.getAverageCalories());
        System.out.println("Average grams of fat per day: " + diary.getAverageFat() + " g");
        System.out.println("Average grams of sugar per day: " + diary.getAverageSugar() + " g");
        System.out.println("Average grams of protein per day: " + diary.getAverageProtein() + " g");
        System.out.println();
    }
}