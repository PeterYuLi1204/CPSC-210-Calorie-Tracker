package ui;

import model.DailyLog;
import model.Diary;
import model.Food;

import java.util.Scanner;

public class Menu {

    private boolean running;
    private final Scanner input;
    private final Diary diary;

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

    // Code is based on the parseInput method in the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: Prints the main menu options and waits for the user's input
    //          to continue program based on the command
    @SuppressWarnings("methodlength")
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
    @SuppressWarnings("methodlength")
    private void parseDailyLogMenuInput(DailyLog dailyLog) {
        dailyLogMenu:
        while (running) {
            printDailyLog(dailyLog);
            printDailyLogMenuInstructions();

            switch (input.nextLine().trim()) {
                case "1":
                    handleAddFood(dailyLog);
                    break;
                case "2":
                    handleRemoveFood(dailyLog);
                    break;
                case "3":
                    handleViewFood(dailyLog);
                    break;
                case "4":
                    handleChangeDate(dailyLog);
                    break;
                case "5":
                    printDailyTotalNutritionalValues(dailyLog);
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
    @SuppressWarnings("methodlength")
    private void parseFoodMenuInput(Food food) {
        foodMenu:
        while (running) {
            printFood(food);
            printFoodMenuInstructions();

            switch (input.nextLine().trim()) {
                case "1":
                    handleEditFoodName(food);
                    break;
                case "2":
                    handleEditFoodCalories(food);
                    break;
                case "3":
                    handleEditFoodFat(food);
                    break;
                case "4":
                    handleEditFoodSugar(food);
                    break;
                case "5":
                    handleEditFoodProtein(food);
                    break;
                case "6":
                    System.out.println("Going back to the daily log menu! \n");
                    break foodMenu;
                default:
                    System.out.println("Sorry, that command isn't recognized. Please try again!\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new daily log to the diary with prompts and user inputs
    private void handleAddNewDailyLog() {
        try {
            System.out.println("Please enter the year:");
            int year = input.nextInt();

            System.out.println("Please enter the month:");
            int month = input.nextInt();

            System.out.println("Please enter the day:");
            int day = input.nextInt();

            diary.addDailyLog(new DailyLog(year, month, day));
            System.out.println("Successfully added! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes a daily log from the diary with prompts and user inputs
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

    // EFFECTS: View a daily log and edit it with prompts and user inputs
    private void handleViewDailyLog() {
        try {
            int dailyLogToView;

            System.out.println("Please type the number of the daily log to view or edit");
            dailyLogToView = input.nextInt() - 1;

            input.nextLine();
            parseDailyLogMenuInput(diary.getDailyLogs().get(dailyLogToView));

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
        }
    }

    // EFFECTS: Create and add a food to the daily log with prompts and user inputs
    private void handleAddFood(DailyLog dailyLog) {
        try {
            System.out.println("Please enter the name of the food:");
            String name = input.nextLine();

            System.out.println("Please enter the calories in the food:");
            int calories = input.nextInt();

            System.out.println("Please enter the grams of fat in the food:");
            double fat = input.nextDouble();

            System.out.println("Please enter the grams of sugar in the food:");
            double sugar = input.nextDouble();

            System.out.println("Please enter the grams of protein in the food:");
            double protein = input.nextDouble();

            if (name.isEmpty() || calories < 0 || fat < 0 || sugar < 0 || protein < 0) {
                throw new Exception();
            }

            dailyLog.addFood(new Food(name, calories, fat, sugar, protein));
            System.out.println("Successfully added! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this, dailyLog
    // EFFECTS: Remove a food to the daily log with prompts and user inputs
    private void handleRemoveFood(DailyLog dailyLog) {
        try {
            System.out.println("Please type the number of the food to delete:");
            int foodToRemove = input.nextInt() - 1;

            dailyLog.removeFood(foodToRemove);
            System.out.println("Successfully deleted! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // EFFECTS: View a food and edit it with prompts and user inputs
    private void handleViewFood(DailyLog dailyLog) {
        try {
            System.out.println("Please type the number of the food to view or edit");
            int foodToView = input.nextInt() - 1;

            input.nextLine();
            parseFoodMenuInput(dailyLog.getFoodLog().get(foodToView));

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
        }
    }

    // MODIFIES: this, dailyLog
    // EFFECTS: Changes the date of the given daily log with prompts and user input
    private void handleChangeDate(DailyLog dailyLog) {
        try {
            System.out.println("Please enter new the year:");
            int year = input.nextInt();

            System.out.println("Please enter the new month:");
            int month = input.nextInt();

            System.out.println("Please enter the new day:");
            int day = input.nextInt();

            dailyLog.setDate(year, month, day);
            System.out.println("Successfully changed the date! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the name of the given food with prompts and user inputs
    private void handleEditFoodName(Food food) {
        try {
            System.out.println("Please enter the new name:");
            String name = input.nextLine();

            if (name.isEmpty()) {
                throw new Exception();
            }

            food.setName(name);
            System.out.println("Successfully changed the name! \n");

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the calories of the given food with prompts and user inputs
    private void handleEditFoodCalories(Food food) {
        try {
            System.out.println("Please enter the new calorie count:");
            int calories = input.nextInt();

            if (calories < 0) {
                throw new Exception();
            }

            food.setCalories(calories);
            System.out.println("Successfully changed the calories! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of fat of the given food with prompts and user inputs
    private void handleEditFoodFat(Food food) {
        try {
            System.out.println("Please enter the new grams of fat:");
            double fat = input.nextDouble();

            if (fat < 0) {
                throw new Exception();
            }

            food.setFat(fat);
            System.out.println("Successfully changed the grams of fat! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of sugar of the given food with prompts and user inputs
    private void handleEditFoodSugar(Food food) {
        try {
            System.out.println("Please enter the new grams of sugar:");
            double sugar = input.nextDouble();

            if (sugar < 0) {
                throw new Exception();
            }

            food.setSugar(sugar);
            System.out.println("Successfully changed the grams of sugar! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of protein of the given food with prompts and user inputs
    private void handleEditFoodProtein(Food food) {
        try {
            System.out.println("Please enter the new grams of protein:");
            double protein = input.nextDouble();

            if (protein < 0) {
                throw new Exception();
            }

            food.setProtein(protein);
            System.out.println("Successfully changed the grams of protein! \n");
            input.nextLine();

        } catch (Exception e) {
            System.out.println("That's not a valid input so please try again! \n");
            input.nextLine();
        }
    }

    // Print methods

    // EFFECTS: Prints the prompts for the main menu
    private void printMainMenuInstructions() {
        System.out.println("Enter 1 to add an entry to your diary");
        System.out.println("Enter 2 to remove an entry from your diary");
        System.out.println("Enter 3 to view or edit an entry in your diary");
        System.out.println("Enter 4 to view average nutritional intake");
        System.out.println("Enter 5 to quit the program");
    }

    // EFFECTS: Prints the prompts for the daily log viewing and editing menu
    private void printDailyLogMenuInstructions() {
        System.out.println("Enter 1 to add a new food to this daily log");
        System.out.println("Enter 2 to remove a new food to this daily log");
        System.out.println("Enter 3 to view or edit a food in this daily log");
        System.out.println("Enter 4 to change the date of this daily log");
        System.out.println("Enter 5 to view total nutritional intake from this daily log");
        System.out.println("Enter 6 to go back to the main menu");
    }

    // EFFECTS: Prints the prompts for the food viewing and editing menu
    private void printFoodMenuInstructions() {
        System.out.println("Enter 1 to edit the name");
        System.out.println("Enter 2 to edit the calories");
        System.out.println("Enter 3 to edit the grams of fat");
        System.out.println("Enter 4 to edit the grams of sugar");
        System.out.println("Enter 5 to edit the grams of protein");
        System.out.println("Enter 6 to go back to viewing the daily log");
    }

    // EFFECTS: Prints out the dairy and all daily logs or an empty message if there are no daily logs
    private void printDiary(Diary diary) {
        System.out.println("Your diary:");

        if (diary.getDailyLogs().size() == 0) {
            System.out.println("You have no daily logs! \n");
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
            System.out.println("No eaten foods recorded yet! \n");
        } else {
            for (Food food : dailyLog.getFoodLog()) {
                System.out.println(" - " + food.getName());
            }
            System.out.println();
        }
    }

    // EFFECTS: Prints out the given daily log with its date
    //          and record of foods eaten along with their nutritional information
    private void printDailyLog(DailyLog dailyLog) {
        System.out.println("Daily log of " + dailyLog.getDate() + ":");

        if (dailyLog.getFoodLog().size() == 0) {
            System.out.println("No eaten foods recorded yet! \n");
        } else {
            int counter = 1;

            for (Food food : dailyLog.getFoodLog()) {
                System.out.println("(" + counter + ")");
                counter++;

                printFood(food);
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
        System.out.println();
    }

    // EFFECTS: Prints the average nutritional values
    private void printAverageNutritionalValues() {
        System.out.println("Average calories per day: " + diary.getAverageCalories());
        System.out.println("Average grams of fat per day: " + diary.getAverageFat() + " g");
        System.out.println("Average grams of sugar per day: " + diary.getAverageSugar() + " g");
        System.out.println("Average grams of protein per day: " + diary.getAverageProtein() + " g");
        System.out.println();
    }

    // EFFECTS: Prints the total nutritional intake values
    private void printDailyTotalNutritionalValues(DailyLog dailyLog) {
        System.out.println("Total calories consumed: " + dailyLog.getTotalCalories());
        System.out.println("Total grams of fat consumed: " + dailyLog.getTotalFat() + " g");
        System.out.println("Total grams of sugar consumed: " + dailyLog.getTotalSugar() + " g");
        System.out.println("Total grams of protein consumed: " + dailyLog.getTotalProtein() + " g");
        System.out.println();
    }
}