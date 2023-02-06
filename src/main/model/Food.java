package model;

public class Food {
    private String name;
    private int calories;
    private double fat;
    private double sugar;
    private double protein;

    // REQUIRES: name to be not empty and calories, fat, sugar, and protein to be non-negative
    // EFFECTS: Constructs a new food with calories, grams of fat, grams of sugar, and grams of protein
    public Food(String name, int calories, double fat, double sugar, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.protein = protein;
    }

    // EFFECTS: Prints the name of the food and all of its nutritional values
    public void printFood() {
        System.out.println("Name: " + getName());
        System.out.println("Calories: " + getCalories());
        System.out.println("Grams of fat: " + getFat());
        System.out.println("Grams of sugar: " + getSugar());
        System.out.println("Grams of protein: " + getProtein());
    }

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
