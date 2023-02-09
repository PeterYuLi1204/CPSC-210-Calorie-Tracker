package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyLog {

    private List<Food> foodLog;
    private LocalDate date;

    // REQUIRES: year, month, and day must be a valid date
    // EFFECTS: Constructs a new day with an empty record of foods eaten
    public DailyLog(int year, int month, int day) {
        foodLog = new ArrayList<>();
        date = LocalDate.of(year, month, day);
    }

    // MODIFIES: this
    // EFFECTS: Adds the given food to the record of foods eaten
    public void addFood(Food food) {
        foodLog.add(food);
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: Removes the food at the given position from the record of food eaten
    public void removeFood(int index) {
        foodLog.remove(index);
    }

    // EFFECTS: Compares the dates of the two given logs and returns the difference
    public static int compareDailyLogs(DailyLog log1, DailyLog log2) {
        return log1.getDate().compareTo(log2.getDate());
    }

    // REQUIRES: year, month, and day must be a valid date
    // MODIFIES: this
    // EFFECTS: Changes the date of the daily log to the given date
    public void setDate(int year, int month, int day) {
        date = LocalDate.of(year, month, day);
    }

    // EFFECTS: Returns the record of foods eaten
    public List<Food> getFoodLog() {
        return foodLog;
    }

    // EFFECTS: Returns the date of the daily log
    public LocalDate getDate() {
        return date;
    }

    // REQUIRES: At least one food item in the record of foods eaten
    //EFFECTS: Returns the food in record of foods eaten today with the most calories
    //         or the food with the most calories that appears first in the record in the case of a tie
    public Food getFoodWithMostCalories() {
        Food mostCalories = null;

        for (Food food : foodLog) {
            if (mostCalories == null || food.getCalories() > mostCalories.getCalories()) {
                mostCalories = food;
            }
        }

        return mostCalories;
    }

    // REQUIRES: At least one food item in the record of foods eaten
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of fat
    //         or the food with the most grams of fat that appears first in the record in the case of a tie
    public Food getFoodWithMostFat() {
        Food mostFat = null;

        for (Food food : foodLog) {
            if (mostFat == null || food.getFat() > mostFat.getFat()) {
                mostFat = food;
            }
        }

        return mostFat;
    }

    // REQUIRES: At least one food item in the record of foods eaten
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of sugar
    //         or the food with the most grams of sugar that appears first in the record in the case of a tie
    public Food getFoodWithMostSugar() {
        Food mostSugar = null;

        for (Food food : foodLog) {
            if (mostSugar == null || food.getSugar() > mostSugar.getSugar()) {
                mostSugar = food;
            }
        }

        return mostSugar;
    }

    // REQUIRES: At least one food item in the record of foods eaten
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of protein
    //         or the food with the most grams of protein that appears first in the record in the case of a tie
    public Food getFoodWithMostProtein() {
        Food mostProtein = null;

        for (Food food : foodLog) {
            if (mostProtein == null || food.getProtein() > mostProtein.getProtein()) {
                mostProtein = food;
            }
        }

        return mostProtein;
    }

    // EFFECTS: Returns the total number of calories consumed today
    public int getTotalCalories() {
        int total = 0;

        for (Food food : foodLog) {
            total += food.getCalories();
        }

        return total;
    }

    // EFFECTS: Returns the total grams of fat consumed today
    public double getTotalFat() {
        double total = 0.0;

        for (Food food : foodLog) {
            total += food.getFat();
        }

        return total;
    }

    // EFFECTS: Returns the total grams of sugar consumed today
    public double getTotalSugar() {
        double total = 0.0;

        for (Food food : foodLog) {
            total += food.getSugar();
        }

        return total;
    }

    // EFFECTS: Returns the total grams of protein consumed today
    public double getTotalProtein() {
        double total = 0.0;

        for (Food food : foodLog) {
            total += food.getProtein();
        }

        return total;
    }
}