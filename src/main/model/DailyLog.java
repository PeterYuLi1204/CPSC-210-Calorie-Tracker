package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Represents a daily log entry in the diary
public class DailyLog {

    private final List<Food> foods;
    private LocalDate date;

    // REQUIRES: year, month, and day must be a valid date
    // EFFECTS: Constructs a new day with an empty record of foods eaten
    public DailyLog(int year, int month, int day) {
        foods = new ArrayList<>();
        date = LocalDate.of(year, month, day);
    }

    // EFFECTS: Compares the dates of the two given daily logs and returns the difference
    //          in the largest quantity of time in which there is a difference starting from years
    public static int compareDailyLogDates(DailyLog log1, DailyLog log2) {
        return log1.getDate().compareTo(log2.getDate());
    }

    // Setters

    // MODIFIES: this
    // EFFECTS: Adds the given food to the record of foods eaten and then logs the event
    public void addFood(Food food) {
        foods.add(food);
        EventLog.getInstance().logEvent(new Event("Added a food to a daily log"));
    }

    // MODIFIES: this
    // EFFECTS: Removes the food at the given position from the record of food eaten and then logs the event
    public void removeFood(int index) {
        foods.remove(index);
        EventLog.getInstance().logEvent(new Event("Removed a food from a daily log"));
    }

    // MODIFIES: this
    // EFFECTS: Removes the given food from the record of food eaten and then logs the event
    public void removeFood(Food food) {
        foods.remove(food);
        EventLog.getInstance().logEvent(new Event("Removed a food from a daily log"));
    }

    // REQUIRES: year, month, and day must be a valid date
    // MODIFIES: this
    // EFFECTS: Changes the date of the daily log to the given date
    public void setDate(int year, int month, int day) {
        date = LocalDate.of(year, month, day);
    }

    // Getters

    // EFFECTS: Returns the record of foods eaten
    public List<Food> getFoods() {
        return foods;
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
        for (Food food : foods) {
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
        for (Food food : foods) {
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
        for (Food food : foods) {
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
        for (Food food : foods) {
            if (mostProtein == null || food.getProtein() > mostProtein.getProtein()) {
                mostProtein = food;
            }
        }
        return mostProtein;
    }

    // EFFECTS: Returns the total number of calories consumed today
    public int getTotalCalories() {
        int total = 0;
        for (Food food : foods) {
            total += food.getCalories();
        }
        return total;
    }

    // EFFECTS: Returns the total grams of fat consumed today rounded to 1 decimal place
    public double getTotalFat() {
        double total = 0.0;
        for (Food food : foods) {
            total += food.getFat();
        }
        return Math.round(total * 10.0) / 10.0;
    }

    // EFFECTS: Returns the total grams of sugar consumed today rounded to 1 decimal place
    public double getTotalSugar() {
        double total = 0.0;
        for (Food food : foods) {
            total += food.getSugar();
        }
        return Math.round(total * 10.0) / 10.0;
    }

    // EFFECTS: Returns the total grams of protein consumed today rounded to 1 decimal place
    public double getTotalProtein() {
        double total = 0.0;
        for (Food food : foods) {
            total += food.getProtein();
        }
        return Math.round(total * 10.0) / 10.0;
    }
}