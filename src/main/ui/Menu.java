package ui;

import model.DailyLog;
import model.Diary;
import model.Food;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// User interactive menu for the program
public class Menu {

    private static final String LOCATION = "./data/diary.json";
    private boolean running;
    private final Scanner input;
    private Diary diary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Menu() {
        diary = new Diary();
        input = new Scanner(System.in);
        running = true;
    }

    // EFFECTS: Starts the program at the main menu
    public void startProgram() {
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
        parseMainMenuInput();
    }

    // EFFECTS: Stops taking user input and closes the program
    private void closeProgram() {
        input.close();
        System.out.println("Quitting Program");
        running = false;
    }

    // Input Getters

    // EFFECTS: Returns the next integer that the user inputs or -1 if the input is invalid
    private int getIntInput() {
        try {
            int number = input.nextInt();
            input.nextLine();

            return number;
        } catch (Exception e) {
            input.nextLine();
            return -1;
        }
    }

    // EFFECTS: Returns the next double that the user inputs
    private double getDoubleInput() {
        double number = input.nextDouble();
        input.nextLine();

        return number;
    }

    // Input Handlers

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
                    System.out.println("Press enter to continue:");
                    input.nextLine();
                    break;
                case "5":
                    saveDiary();
                    break;
                case "6":
                    loadDiary();
                    break;
                case "7":
                    closeProgram();
                    break;
                default:
                    handleInvalidInput("command");
                    break;
            }
        }
    }

    // Code is based on the parseInput method in the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: Prints the daily log viewing and editing menu options and waits for the user's input
    //          to continue program based on the command
    @SuppressWarnings("methodlength")
    private void parseDailyLogMenuInput(DailyLog dailyLog) {
        System.out.println("Viewing daily log now! \n");
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
                    System.out.println("Press enter to continue:");
                    input.nextLine();
                    break;
                case "6":
                    printFoodsWithMostNutritionalValue(dailyLog);
                    System.out.println("Press enter to continue:");
                    input.nextLine();
                    break;
                case "7":
                    System.out.println("Going back to main menu! \n");
                    break dailyLogMenu;
                default:
                    handleInvalidInput("command");
                    break;
            }
        }
    }

    // Code is based on the parseInput method in the FitLifeGymKiosk program from the LongFormProblemStarters
    // EFFECTS: Prints the food menu options and waits for the user's input
    //          to continue program based on the command
    @SuppressWarnings("methodlength")
    private void parseFoodMenuInput(Food food) {
        System.out.println("Viewing food item now! \n");
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
                    handleInvalidInput("command");
                    break;
            }
        }
    }

    // Menu Options

    // EFFECTS: Prints an error statement for invalid input and waits until user presses enter to continue
    private void handleInvalidInput(String error) {
        System.out.println("That's not a valid " + error + ", press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new daily log to the diary with prompts and user inputs
    private void handleAddNewDailyLog() {
        try {
            System.out.println("Please enter the year:");
            int year = getIntInput();

            System.out.println("Please enter the month:");
            int month = getIntInput();

            System.out.println("Please enter the day:");
            int day = getIntInput();

            if (year < 0) {
                throw new Exception();
            }

            diary.addDailyLog(new DailyLog(year, month, day));
            System.out.println("Successfully added the daily log, press enter to continue:");
            input.nextLine();
        } catch (Exception e) {
            handleInvalidInput("date");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes a daily log from the diary with prompts and user inputs
    private void handleRemoveDailyLog() {
        System.out.println("Please type the number of the daily log to delete:");
        int dailyLogToRemove = getIntInput();

        if (dailyLogToRemove > diary.getDailyLogs().size() || dailyLogToRemove < 0) {
            handleInvalidInput("number for a daily log");
            return;
        }

        diary.removeDailyLog(dailyLogToRemove - 1);
        System.out.println("Successfully deleted the daily log, press enter to continue:");
        input.nextLine();
    }

    // EFFECTS: View a daily log and edit it with prompts and user inputs
    private void handleViewDailyLog() {
        System.out.println("Please type the number of the daily log to view or edit");
        int dailyLogToView = getIntInput();

        if (dailyLogToView > diary.getDailyLogs().size() || dailyLogToView < 0) {
            handleInvalidInput("number for a daily log");
            return;
        }

        parseDailyLogMenuInput(diary.getDailyLogs().get(dailyLogToView - 1));
    }

    // EFFECTS: Create and add a food item to the daily log with prompts and user inputs
    private void handleAddFood(DailyLog dailyLog) {
        System.out.println("Please enter the name of the food:");
        String name = input.nextLine();

        System.out.println("Please enter the calories in the food:");
        int calories = getIntInput();

        System.out.println("Please enter the grams of fat in the food:");
        double fat = getDoubleInput();

        System.out.println("Please enter the grams of sugar in the food:");
        double sugar = getDoubleInput();

        System.out.println("Please enter the grams of protein in the food:");
        double protein = getDoubleInput();

        if (name.isEmpty() || calories < 0 || fat < 0 || sugar < 0 || protein < 0) {
            handleInvalidInput("food");
            return;
        }

        dailyLog.addFood(new Food(name, calories, fat, sugar, protein));
        System.out.println("Successfully added the food item, press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this, dailyLog
    // EFFECTS: Remove a food item from the daily log with prompts and user inputs
    private void handleRemoveFood(DailyLog dailyLog) {
        System.out.println("Please type the number of the food to delete:");
        int foodToRemove = getIntInput();

        if (foodToRemove > dailyLog.getFoods().size() || foodToRemove < 0) {
            handleInvalidInput("number for a food");
            return;
        }

        dailyLog.removeFood(foodToRemove - 1);
        System.out.println("Successfully deleted the food item, press enter to continue:");
        input.nextLine();
    }

    // EFFECTS: View a food and edit it with prompts and user inputs
    private void handleViewFood(DailyLog dailyLog) {
        System.out.println("Please type the number of the food to view or edit");
        int foodToView = getIntInput();

        if (foodToView > dailyLog.getFoods().size() || foodToView < 0) {
            handleInvalidInput("number for a food");
            return;
        }

        parseFoodMenuInput(dailyLog.getFoods().get(foodToView - 1));
    }

    // MODIFIES: this, dailyLog
    // EFFECTS: Changes the date of the given daily log with prompts and user input
    private void handleChangeDate(DailyLog dailyLog) {
        try {
            System.out.println("Please enter new the year:");
            int year = getIntInput();

            System.out.println("Please enter the new month:");
            int month = getIntInput();

            System.out.println("Please enter the new day:");
            int day = getIntInput();

            if (year < 0) {
                throw new Exception();
            }

            dailyLog.setDate(year, month, day);
            System.out.println("Successfully changed the date, press enter to continue \n");
            input.nextLine();
        } catch (Exception e) {
            handleInvalidInput("date");
        }
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the name of the given food with prompts and user inputs
    private void handleEditFoodName(Food food) {
        System.out.println("Please enter the new name:");
        String name = input.nextLine();

        if (name.isEmpty()) {
            handleInvalidInput("name");
            return;
        }

        food.setName(name);
        System.out.println("Successfully changed the name, press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the calories of the given food with prompts and user inputs
    private void handleEditFoodCalories(Food food) {
        System.out.println("Please enter the new calorie count:");
        int calories = getIntInput();

        if (calories < 0) {
            handleInvalidInput("calorie count");
            return;
        }

        food.setCalories(calories);
        System.out.println("Successfully changed the calories, press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of fat of the given food with prompts and user inputs
    private void handleEditFoodFat(Food food) {
        System.out.println("Please enter the new grams of fat:");
        double fat = getDoubleInput();

        if (fat < 0) {
            handleInvalidInput("amount of fat");
            return;
        }

        food.setFat(fat);
        System.out.println("Successfully changed the grams of fat, press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of sugar of the given food with prompts and user inputs
    private void handleEditFoodSugar(Food food) {
        System.out.println("Please enter the new grams of sugar:");
        double sugar = getDoubleInput();

        if (sugar < 0) {
            handleInvalidInput("amount of sugar");
            return;
        }

        food.setSugar(sugar);
        System.out.println("Successfully changed the grams of sugar, press enter to continue:");
        input.nextLine();
    }

    // MODIFIES: this, food
    // EFFECTS: Changes the grams of protein of the given food with prompts and user inputs
    private void handleEditFoodProtein(Food food) {
        System.out.println("Please enter the new grams of protein:");
        double protein = getDoubleInput();

        if (protein < 0) {
            handleInvalidInput("amount of protein");
            return;
        }

        food.setProtein(protein);
        System.out.println("Successfully changed the grams of protein, press enter to continue:");
        input.nextLine();
    }

    // Print methods

    // EFFECTS: Prints the prompts for the main menu
    private void printMainMenuInstructions() {
        System.out.println("Enter 1 to add an entry to your diary");
        System.out.println("Enter 2 to remove an entry from your diary");
        System.out.println("Enter 3 to view or edit an entry in your diary");
        System.out.println("Enter 4 to view average nutritional intake");
        System.out.println("Enter 5 to save the diary");
        System.out.println("Enter 6 to load the diary");
        System.out.println("Enter 7 to quit the program");
    }

    // EFFECTS: Prints the prompts for the daily log viewing and editing menu
    private void printDailyLogMenuInstructions() {
        System.out.println("Enter 1 to add a new food to this daily log");
        System.out.println("Enter 2 to remove a new food to this daily log");
        System.out.println("Enter 3 to view or edit a food in this daily log");
        System.out.println("Enter 4 to change the date of this daily log");
        System.out.println("Enter 5 to view total nutritional intake from this daily log");
        System.out.println("Enter 6 to view foods with highest nutritional values in this daily log");
        System.out.println("Enter 7 to go back to the main menu");
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
                System.out.println("(" + counter++ + ")");
                printDailyLogPreview(dailyLog);
            }
        }
    }

    // EFFECTS: Prints out the given daily log with its date and list of names of foods eaten
    private void printDailyLogPreview(DailyLog dailyLog) {
        System.out.println("Daily log of " + dailyLog.getDate());

        if (dailyLog.getFoods().size() == 0) {
            System.out.println("No eaten foods recorded yet! \n");
        } else {
            for (Food food : dailyLog.getFoods()) {
                System.out.println(" - " + food.getName());
            }
            System.out.println();
        }
    }

    // EFFECTS: Prints out the given daily log with its date
    //          and record of foods eaten along with their nutritional information
    private void printDailyLog(DailyLog dailyLog) {
        System.out.println("Daily log of " + dailyLog.getDate() + ":");

        if (dailyLog.getFoods().size() == 0) {
            System.out.println("No eaten foods recorded yet! \n");
        } else {
            int counter = 1;
            for (Food food : dailyLog.getFoods()) {
                System.out.println("(" + counter++ + ")");
                printFood(food);
            }
        }
    }

    // EFFECTS: Prints the name of the given food along with all of its nutritional values
    private void printFood(Food food) {
        System.out.println("Name: " + food.getName());
        System.out.println("Calories: " + food.getCalories());
        System.out.println("Grams of fat: " + food.getFat() + " g");
        System.out.println("Grams of sugar: " + food.getSugar() + " g");
        System.out.println("Grams of protein: " + food.getProtein() + " g \n");
    }

    // EFFECTS: Prints the average nutritional values
    private void printAverageNutritionalValues() {
        System.out.println("Average calories per day: " + diary.getAverageCalories());
        System.out.println("Average grams of fat per day: " + diary.getAverageFat() + " g");
        System.out.println("Average grams of sugar per day: " + diary.getAverageSugar() + " g");
        System.out.println("Average grams of protein per day: " + diary.getAverageProtein() + " g \n");
    }

    // EFFECTS: Prints the total nutritional intake values
    private void printDailyTotalNutritionalValues(DailyLog dailyLog) {
        System.out.println("Total calories consumed: " + dailyLog.getTotalCalories());
        System.out.println("Total grams of fat consumed: " + dailyLog.getTotalFat() + " g");
        System.out.println("Total grams of sugar consumed: " + dailyLog.getTotalSugar() + " g");
        System.out.println("Total grams of protein consumed: " + dailyLog.getTotalProtein() + " g \n");
    }

    // EFFECTS: Prints the foods with the highest value in each nutritional category
    private void printFoodsWithMostNutritionalValue(DailyLog dailyLog) {
        if (dailyLog.getFoods().size() > 0) {
            System.out.println("Food with most calories:");
            printFood(dailyLog.getFoodWithMostCalories());
            System.out.println("Food with most fat:");
            printFood(dailyLog.getFoodWithMostFat());
            System.out.println("Food with most sugar:");
            printFood(dailyLog.getFoodWithMostSugar());
            System.out.println("Food with most protein:");
            printFood(dailyLog.getFoodWithMostProtein());
        } else {
            System.out.println("There are no foods recorded yet! \n");
        }
    }

    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Saves the diary to file
    public void saveDiary() {
        try {
            jsonWriter.save(diary);
            System.out.println("Successfully saved the diary, press enter to continue:");
            input.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file, press enter to continue:");
            input.nextLine();
        }
    }

    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
    // MODIFIES: this
    // EFFECTS: Loads the diary from file
    public void loadDiary() {
        try {
            diary = jsonReader.load();
            System.out.println("Successfully loaded the diary, press enter to continue:");
            input.nextLine();
        } catch (IOException e) {
            System.out.println("Unable to read from file, press enter to continue:");
            input.nextLine();
        }
    }
}