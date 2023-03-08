package persistence;

import model.Diary;
import model.DailyLog;
import model.Food;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.*;

// Represents a writer that writes a JSON representation of the diary to file
public class JsonWriter {

    private static final int INDENT = 3;
    private String location;

    // Code is based on the JsonWriter class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Constructs a writer with a location to write JSON representation of diary to
    public JsonWriter(String location) {
        this.location = location;
    }

    // Code is based on the JsonWriter class in the JsonSerializationDemo program from the Phase 2
    // MODIFIES: this
    // EFFECTS: opens the writer; throws FileNotFoundException if file cannot be opened and written to
    // then writes the JSON representation of the given diary to file and closes
    public void save(Diary diary) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(location);
        JSONObject json = convertDiaryToJson(diary);
        writer.print(json.toString(INDENT));
        writer.close();
    }

    // Code is based on the WorkRoom class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Returns the given diary as a json object
    private JSONObject convertDiaryToJson(Diary diary) {
        JSONObject json = new JSONObject();

        json.put("daily logs", convertDailyLogsToJson(diary));

        return json;
    }

    // Code is based on the WorkRoom class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Returns the daily logs in the given diary as a json array
    private JSONArray convertDailyLogsToJson(Diary diary) {
        JSONArray jsonArray = new JSONArray();

        for (DailyLog dailyLog : diary.getDailyLogs()) {
            JSONObject json = new JSONObject();
            json.put("date", dailyLog.getDate());
            json.put("foods", convertFoodsToJson(dailyLog));
            jsonArray.put(json);
        }

        return jsonArray;
    }

    // Code is based on the WorkRoom class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Returns the foods in the given daily log as a json array
    private JSONArray convertFoodsToJson(DailyLog dailyLog) {
        JSONArray jsonArray = new JSONArray();

        for (Food food : dailyLog.getFoods()) {
            JSONObject json = new JSONObject();
            json.put("name", food.getName());
            json.put("calories", food.getCalories());
            json.put("fat", food.getFat());
            json.put("sugar", food.getSugar());
            json.put("protein", food.getProtein());
            jsonArray.put(json);
        }

        return jsonArray;
    }
}
