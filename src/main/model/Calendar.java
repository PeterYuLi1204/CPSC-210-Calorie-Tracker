package model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Day> days;

    // EFFECTS: Constructs a new calendar with no days in it
    public Calendar() {
        days = new ArrayList<>();
    }

    // TODO 3
    // EFFECTS: Returns the average number of calories consumed per day
    public int getAverageCalories() {
        return 0; // Stub
    }

    // TODO 4
    // EFFECTS: Returns the average grams of fat consumed per day
    public double getAverageFat() {
        return 0; // Stub
    }

    // TODO 5
    // EFFECTS: Returns the average grams of sugar consumed per day
    public double getAverageSugar() {
        return 0; // Stub
    }

    // TODO 6
    // EFFECTS: Returns the average grams of protein consumed per day
    public double getAverageProtein() {
        return 0; // Stub
    }
}
