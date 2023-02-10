package model;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private final List<DailyLog> dailyLogs;

    // EFFECTS: Constructs a new calendar with no days in it
    public Diary() {
        dailyLogs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given daily log to the diary then sorts the diary chronologically
    public void addDailyLog(DailyLog dailyLog) {
        dailyLogs.add(dailyLog);
        dailyLogs.sort(DailyLog::compareDailyLogs);
    }

    // MODIFIES: this
    // EFFECTS: Removes the day at the given position from the calendar
    public void removeDailyLog(int index) {
        dailyLogs.remove(index);
    }

    // EFFECTS: Returns all daily logs in the diary
    public List<DailyLog> getDailyLogs() {
        return dailyLogs;
    }

    // EFFECTS: Returns the average number of calories consumed per day rounded down to the nearest whole number
    //          or 0 if there are no daily logs
    public int getAverageCalories() {
        if (dailyLogs.size() > 0) {
            int totalCalories = 0;

            for (DailyLog dailyLog : dailyLogs) {
                totalCalories += dailyLog.getTotalCalories();
            }

            return totalCalories / dailyLogs.size();
        }

        return 0;
    }

    // EFFECTS: Returns the average grams of fat consumed per day or 0 if there are no daily logs
    public double getAverageFat() {
        if (dailyLogs.size() > 0) {
            double totalFat = 0;

            for (DailyLog dailyLog : dailyLogs) {
                totalFat += dailyLog.getTotalFat();
            }

            return totalFat / dailyLogs.size();
        }

        return 0;
    }

    // REQUIRES: At least one daily log in the diary
    // EFFECTS: Returns the average grams of sugar consumed per day or 0 if there are no daily logs
    public double getAverageSugar() {
        if (dailyLogs.size() > 0) {
            double totalSugar = 0;

            for (DailyLog dailyLog : dailyLogs) {
                totalSugar += dailyLog.getTotalSugar();
            }

            return totalSugar / dailyLogs.size();
        }

        return 0;
    }

    // EFFECTS: Returns the average grams of protein consumed per day or 0 if there are no daily logs
    public double getAverageProtein() {
        if (dailyLogs.size() > 0) {
            double totalProtein = 0;

            for (DailyLog dailyLog : dailyLogs) {
                totalProtein += dailyLog.getTotalProtein();
            }

            return totalProtein / dailyLogs.size();
        }

        return 0;
    }
}