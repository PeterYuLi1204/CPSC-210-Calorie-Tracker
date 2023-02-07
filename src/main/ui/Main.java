package ui;

import model.DailyLog;
import model.Diary;
import model.Food;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DailyLog day1 = new DailyLog();
        DailyLog day2 = new DailyLog();
        Diary diary = new Diary();
        diary.addDay(day1);
        diary.addDay(day2);

        day1.addFood(new Food("apple", 100, 0, 13, 0));
        day1.addFood(new Food("pear", 150, 0, 8, 0));
        day1.addFood(new Food("steak", 60, 17, 8, 67));

        day2.addFood(new Food("apple", 300, 0, 13, 0));
        day2.addFood(new Food("pear", 300, 0, 8, 0));
        day2.addFood(new Food("steak", 180, 17, 8, 67));

        System.out.println(diary.getAverageCalories());
        System.out.println(diary.getAverageFat());
        System.out.println(diary.getAverageSugar());
        System.out.println(diary.getAverageProtein());
        System.out.println();

        printFood(day1.getFoodWithMostProtein());
        System.out.println();
        printFood(day2.getFoodWithMostProtein());
    }

    // EFFECTS: Prints the name of the given food and all of its nutritional values
    public static void printFood(Food food) {
        System.out.println("Name: " + food.getName());
        System.out.println("Calories: " + food.getCalories());
        System.out.println("Grams of fat: " + food.getFat());
        System.out.println("Grams of sugar: " + food.getSugar());
        System.out.println("Grams of protein: " + food.getProtein());
    }

    // EFFECTS: Prints out all the foods eaten in the given and their nutritional information
    public static void printFoodLog(List<Food> log) {
        for (Food food : log) {
            printFood(food);
            System.out.println();
        }
    }
}
