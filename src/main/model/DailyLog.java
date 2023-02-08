package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyLog {

    private List<Food> foodLog;
    private Date date;

    // EFFECTS: Constructs a new day with an empty record of foods eaten
    public DailyLog() {
        foodLog = new ArrayList<>();
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

    // EFFECTS: Returns the record of foods eaten
    public List<Food> getFoodLog() {
        return foodLog;
    }

    // TODO
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

    // TODO
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

    // TODO
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

    // TODO
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