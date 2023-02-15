package model;

// Represents a food item
public class Food {

    private String name;
    private int calories;
    private double fat;
    private double sugar;
    private double protein;

    // REQUIRES: name not empty and calories, fat, sugar, and protein >= 0.
    // EFFECTS: Constructs a new food with calories, grams of fat, grams of sugar, and grams of protein
    public Food(String name, int calories, double fat, double sugar, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.protein = protein;
    }

    // Setters

    // REQUIRES: name not empty
    // MODIFIES: this
    // EFFECTS: sets the name of the food to the given name
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: calories >= 0
    // MODIFIES: this
    // EFFECTS: sets the calorie count of the food to the given calories
    public void setCalories(int calories) {
        this.calories = calories;
    }

    // REQUIRES: fat >= 0
    // MODIFIES: this
    // EFFECTS: sets the grams of fat of the food to the given grams of fat
    public void setFat(double fat) {
        this.fat = fat;
    }

    // REQUIRES: sugar >= 0
    // MODIFIES: this
    // EFFECTS: sets the grams of sugar of the food to the given grams of sugar
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    // REQUIRES: protein >= 0
    // MODIFIES: this
    // EFFECTS: sets the grams of protein of the food to the given grams of protein
    public void setProtein(double protein) {
        this.protein = protein;
    }

    // Getters

    // EFFECTS: Returns the name of the food
    public String getName() {
        return name;
    }

    // EFFECTS: Returns the calories of the food
    public int getCalories() {
        return calories;
    }

    // EFFECTS: Returns the grams of fat in the food
    public double getFat() {
        return fat;
    }

    // EFFECTS: Returns the grams of sugar in the food
    public double getSugar() {
        return sugar;
    }

    // EFFECTS: Returns the grams of protein in the food
    public double getProtein() {
        return protein;
    }
}