package model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<Food> foodLog;

    // EFFECTS: Constructs a new day with an empty record of foods eaten
    public Day() {
        foodLog = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the food to the record of foods eaten
    public void addFood(Food food) {
        foodLog.add(food);
    }

    // TODO 1
    // MODIFIES: this
    // EFFECTS: Removes the food from the record of foods eaten
    public void removeFood(String foodName) {
        // Stub
    }

    // EFFECTS: Prints out all the foods eaten today and their nutritional information
    public void printFoodLog() {
        for (Food food : foodLog) {
            food.printFood();
            System.out.println();
        }
    }

    // EFFECTS: Returns the record of foods eaten
    public List<Food> getFoodLog() {
        return foodLog;
    }

    // TODO 7
    //EFFECTS: Returns the food in record of foods eaten today with the most calories
    public Food getFoodWithMostCalories() {
        return null; // stub
    }

    // TODO 8
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of fat
    public Food getFoodWithMostFat() {
        return null; // stub
    }

    // TODO 9
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of sugar
    public Food getFoodWithMostSugar() {
        return null; // stub
    }

    // TODO 10
    //EFFECTS: Returns the food in record of foods eaten today with the most grams of protein
    public Food getFoodWithMostProtein() {
        return null; // stub
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
        double total = 0;

        for (Food food : foodLog) {
            total += food.getFat();
        }

        return total;
    }

    // EFFECTS: Returns the total grams of sugar consumed today
    public double getTotalSugar() {
        double total = 0;

        for (Food food : foodLog) {
            total += food.getSugar();
        }

        return total;
    }

    // EFFECTS: Returns the total grams of protein consumed today
    public double getTotalProtein() {
        double total = 0;

        for (Food food : foodLog) {
            total += food.getProtein();
        }

        return total;
    }
}
