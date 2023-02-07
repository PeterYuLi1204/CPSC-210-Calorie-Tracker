package model;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private List<DailyLog> dailyLogs;

    // EFFECTS: Constructs a new calendar with no days in it
    public Diary() {
        dailyLogs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given day to the calendar
    public void addDay(DailyLog dailyLog) {
        dailyLogs.add(dailyLog);
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: Removes the day at the given position from the calendar
    public void removeDay(int index) {
        dailyLogs.remove(index);
    }

    // TODO
    // EFFECTS: Returns the average number of calories consumed per day rounded down to nearest whole number
    public int getAverageCalories() {
        int totalCalories = 0;

        for (DailyLog dailyLog : dailyLogs) {
            totalCalories += dailyLog.getTotalCalories();
        }

        return totalCalories / dailyLogs.size();
    }

    // EFFECTS: Returns the average grams of fat consumed per day
    public double getAverageFat() {
        double totalFat = 0;

        for (DailyLog dailyLog : dailyLogs) {
            totalFat += dailyLog.getTotalFat();
        }

        return totalFat / dailyLogs.size();
    }

    // EFFECTS: Returns the average grams of sugar consumed per day
    public double getAverageSugar() {
        double totalSugar = 0;

        for (DailyLog dailyLog : dailyLogs) {
            totalSugar += dailyLog.getTotalSugar();
        }

        return totalSugar / dailyLogs.size();
    }

    // EFFECTS: Returns the average grams of protein consumed per day
    public double getAverageProtein() {
        double totalProtein = 0;

        for (DailyLog dailyLog : dailyLogs) {
            totalProtein += dailyLog.getTotalProtein();
        }

        return totalProtein / dailyLogs.size();
    }
}
