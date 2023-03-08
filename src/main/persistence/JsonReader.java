package persistence;

import model.Diary;
import model.DailyLog;
import model.Food;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a JSON representation of the diary stored in file
public class JsonReader {

    private String location;

    // Code is based on the JsonReader class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Constructs a reader with a location to read JSON representation of diary from
    public JsonReader(String location) {
        this.location = location;
    }

    // Code is based on the JsonReader class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS:
    public Diary load() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach(builder::append);
        }

        JSONObject jsonObject = new JSONObject(builder.toString());

        return parseDiary(jsonObject);
    }

    // Code is based on the JsonReader class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: parses diary from JSON object and returns it
    private Diary parseDiary(JSONObject jsonObject) {
        Diary diary = new Diary();

        for (Object json : jsonObject.getJSONArray("daily logs")) {
            JSONObject nextDailyLog = (JSONObject) json;
            parseDailyLog(diary, nextDailyLog);
        }

        return diary;
    }

    // Code is based on the JsonReader class in the JsonSerializationDemo program from the Phase 2
    // MODIFIES: diary
    // EFFECTS: parses daily log from JSON object and adds it to the given diary
    private void parseDailyLog(Diary diary, JSONObject nextDailyLog) {
        int year = Integer.parseInt(nextDailyLog.getString("date").substring(0, 4));
        int month = Integer.parseInt(nextDailyLog.getString("date").substring(5, 7));
        int day = Integer.parseInt(nextDailyLog.getString("date").substring(8, 10));

        DailyLog dailyLog = new DailyLog(year, month, day);

        for (Object json : nextDailyLog.getJSONArray("foods")) {
            JSONObject nextFood = (JSONObject) json;
            parseFood(dailyLog, nextFood);
        }

        diary.addDailyLog(dailyLog);
    }

    // Code is based on the JsonReader class in the JsonSerializationDemo program from the Phase 2
    // MODIFIES: dailyLog
    // EFFECTS: parses food from JSON object and adds it to the given daily log
    private void parseFood(DailyLog dailyLog, JSONObject nextFood) {
        String name = nextFood.getString("name");
        int calories = nextFood.getInt("calories");
        double fat = nextFood.getDouble("fat");
        double sugar = nextFood.getDouble("sugar");
        double protein = nextFood.getDouble("protein");

        dailyLog.addFood(new Food(name, calories, fat, sugar, protein));
    }
}
