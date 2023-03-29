//package ui;
//
//import model.DailyLog;
//import model.Diary;
//import model.Food;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//// User interactive menu for the program
//public class CalorieTracker {
//
//    private static final String LOCATION = "./data/diary.json";
//    private Diary diary;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    public CalorieTracker() {
//        diary = new Diary();
//        jsonWriter = new JsonWriter(LOCATION);
//        jsonReader = new JsonReader(LOCATION);
//    }
//
//    // EFFECTS: Stops taking user input and closes the program
//    private void closeProgram() {
//        System.out.println("Quitting Program");
//        running = false;
//    }
//
//    // Menu Options
//
//    // EFFECTS: Prints an error statement for invalid input and waits until user presses enter to continue
//    private void handleInvalidInput(String error) {
//        System.out.println("That's not a valid " + error + ", press enter to continue:");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates and adds a new daily log to the diary
//    private void handleAddNewDailyLog(int year, int month, int day) {
//        try {
//            if (year < 0) {
//                throw new Exception();
//            }
//            diary.addDailyLog(new DailyLog(year, month, day));
//        } catch (Exception e) {
//            handleInvalidInput("date");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Removes a daily log from the diary
//    private void handleRemoveDailyLog(DailyLog dailyLogToRemove) {
//        diary.getDailyLogs().remove(dailyLogToRemove);
//    }
//
//    // EFFECTS: Create and add a food item to the daily log with prompts and user inputs
//    private void handleAddFood(DailyLog dailyLog, String name, int calories, double fat, double sugar, double protein) {
//        if (name.isEmpty() || calories < 0 || fat < 0 || sugar < 0 || protein < 0) {
//            handleInvalidInput("food");
//            return;
//        }
//        dailyLog.addFood(new Food(name, calories, fat, sugar, protein));
//    }
//
//    // MODIFIES: this, dailyLog
//    // EFFECTS: Remove a food item from the daily log with prompts and user inputs
//    private void handleRemoveFood(DailyLog dailyLog, Food foodToRemove) {
//        dailyLog.getFoods().remove(foodToRemove);
//    }
//
//    // EFFECTS: View a food and edit it with prompts and user inputs
//    private void handleViewFood(DailyLog dailyLog) {
//    }
//
//    // MODIFIES: this, dailyLog
//    // EFFECTS: Changes the date of the given daily log with prompts and user input
//    private void handleChangeDate(DailyLog dailyLog, int year, int month, int day) {
//        try {
//            if (year < 0) {
//                throw new Exception();
//            }
//            dailyLog.setDate(year, month, day);
//            System.out.println("Successfully changed the date, press enter to continue \n");
//            input.nextLine();
//        } catch (Exception e) {
//            handleInvalidInput("date");
//        }
//    }
//
//    // MODIFIES: this, food
//    // EFFECTS: Changes the name of the given food with prompts and user inputs
//    private void handleEditFoodName(Food food, String name) {
//        if (name.isEmpty()) {
//            handleInvalidInput("name");
//            return;
//        }
//        food.setName(name);
//        System.out.println("Successfully changed the name, press enter to continue:");
//        input.nextLine();
//    }
//
//    // MODIFIES: this, food
//    // EFFECTS: Changes the calories of the given food with prompts and user inputs
//    private void handleEditFoodCalories(Food food, int calories) {
//        if (calories < 0) {
//            handleInvalidInput("calorie count");
//            return;
//        }
//        food.setCalories(calories);
//        System.out.println("Successfully changed the calories, press enter to continue:");
//        input.nextLine();
//    }
//
//    // MODIFIES: this, food
//    // EFFECTS: Changes the grams of fat of the given food with prompts and user inputs
//    private void handleEditFoodFat(Food food, double fat) {
//        if (fat < 0) {
//            handleInvalidInput("amount of fat");
//            return;
//        }
//        food.setFat(fat);
//        System.out.println("Successfully changed the grams of fat, press enter to continue:");
//        input.nextLine();
//    }
//
//    // MODIFIES: this, food
//    // EFFECTS: Changes the grams of sugar of the given food with prompts and user inputs
//    private void handleEditFoodSugar(Food food, double sugar) {
//        if (sugar < 0) {
//            handleInvalidInput("amount of sugar");
//            return;
//        }
//        food.setSugar(sugar);
//        System.out.println("Successfully changed the grams of sugar, press enter to continue:");
//        input.nextLine();
//    }
//
//    // MODIFIES: this, food
//    // EFFECTS: Changes the grams of protein of the given food with prompts and user inputs
//    private void handleEditFoodProtein(Food food, double protein) {
//        if (protein < 0) {
//            handleInvalidInput("amount of protein");
//            return;
//        }
//        food.setProtein(protein);
//        System.out.println("Successfully changed the grams of protein, press enter to continue:");
//        input.nextLine();
//    }
//
//    // EFFECTS: Prints the average nutritional values
//    private void printAverageNutritionalValues() {
//        System.out.println("Average calories per day: " + diary.getAverageCalories());
//        System.out.println("Average grams of fat per day: " + diary.getAverageFat() + " g");
//        System.out.println("Average grams of sugar per day: " + diary.getAverageSugar() + " g");
//        System.out.println("Average grams of protein per day: " + diary.getAverageProtein() + " g \n");
//    }
//
//    // EFFECTS: Prints the total nutritional intake values
//    private void printDailyTotalNutritionalValues(DailyLog dailyLog) {
//        System.out.println("Total calories consumed: " + dailyLog.getTotalCalories());
//        System.out.println("Total grams of fat consumed: " + dailyLog.getTotalFat() + " g");
//        System.out.println("Total grams of sugar consumed: " + dailyLog.getTotalSugar() + " g");
//        System.out.println("Total grams of protein consumed: " + dailyLog.getTotalProtein() + " g \n");
//    }
//
//    // EFFECTS: Prints the foods with the highest value in each nutritional category
//    private void printFoodsWithMostNutritionalValue(DailyLog dailyLog) {
//        if (dailyLog.getFoods().size() > 0) {
//            System.out.println("Food with most calories:");
//            printFood(dailyLog.getFoodWithMostCalories());
//            System.out.println("Food with most fat:");
//            printFood(dailyLog.getFoodWithMostFat());
//            System.out.println("Food with most sugar:");
//            printFood(dailyLog.getFoodWithMostSugar());
//            System.out.println("Food with most protein:");
//            printFood(dailyLog.getFoodWithMostProtein());
//        } else {
//            System.out.println("There are no foods recorded yet! \n");
//        }
//    }
//
//    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
//    // EFFECTS: Saves the diary to file
//    public void saveDiary() {
//        try {
//            jsonWriter.save(diary);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file, press enter to continue:");
//            input.nextLine();
//        }
//    }
//
//    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
//    // MODIFIES: this
//    // EFFECTS: Loads the diary from file
//    public void loadDiary() {
//        try {
//            diary = jsonReader.load();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file, press enter to continue:");
//            input.nextLine();
//        }
//    }
//}
