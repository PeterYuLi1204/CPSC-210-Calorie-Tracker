package persistence;

import model.DailyLog;
import model.Diary;
import model.Food;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testWriteInvalidFile() {
        JsonWriter jsonWriter = new JsonWriter("./data/\0.json");
        try {
            jsonWriter.save(new Diary());
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    public void testWriteEmptyDiary() {
        JsonWriter jsonWriter = new JsonWriter("./data/testWriteEmptyDiary");
        try {
            jsonWriter.save(new Diary());

            JsonReader jsonReader = new JsonReader("./data/testWriteEmptyDiary");
            Diary diary = jsonReader.load();
            assertTrue(diary.getDailyLogs().isEmpty());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not have been thrown");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWriteGeneralDiary() {
        JsonWriter jsonWriter = new JsonWriter("./data/testWriteEmptyDiary");
        JsonReader jsonReader = new JsonReader("./data/testWriteEmptyDiary");
        try {
            // Constructing new diary and adding daily logs
            Diary diary = new Diary();
            diary.addDailyLog(new DailyLog(2, 2, 2));
            diary.addDailyLog(new DailyLog(2023, 3, 7));

            // Adding foods to empty daily logs
            diary.getDailyLogs().get(0).addFood(new Food("Candy", 200, 2.1, 21.7, 0));
            diary.getDailyLogs().get(0).addFood(new Food("Yogurt", 652, 65.2, 12.1, 17.2));
            diary.getDailyLogs().get(0).addFood(new Food("Milk", 75, 16.2, 5.4, 6.7));
            diary.getDailyLogs().get(1).addFood(new Food("Water", 0, 0, 0, 0));
            diary.getDailyLogs().get(1).addFood(new Food("Dumplings", 417, 16.2, 1.5, 29.9));

            // Saving diary to file
            jsonWriter.save(diary);

            // Loading diary from file
            Diary loadedDiary = jsonReader.load();

            // Checking number and dates of daily logs
            List<DailyLog> loadedDailyLogs = loadedDiary.getDailyLogs();
            assertEquals(2, loadedDailyLogs.size());
            assertEquals(LocalDate.of(2, 2, 2), loadedDailyLogs.get(0).getDate());
            assertEquals(LocalDate.of(2023, 3, 7), loadedDailyLogs.get(1).getDate());

            // Checking number of foods in daily logs
            List<Food> loadedFoods1 = loadedDailyLogs.get(0).getFoods();
            List<Food> loadedFoods2 = loadedDailyLogs.get(1).getFoods();
            assertEquals(3, loadedFoods1.size());
            assertEquals(2, loadedFoods2.size());

            // Checking that foods remain the same
            compareFood("Candy", 200, 2.1, 21.7, 0, loadedFoods1.get(0));
            compareFood("Yogurt", 652, 65.2, 12.1, 17.2, loadedFoods1.get(1));
            compareFood("Milk", 75, 16.2, 5.4, 6.7, loadedFoods1.get(2));
            compareFood("Water", 0, 0, 0, 0, loadedFoods2.get(0));
            compareFood("Dumplings", 417, 16.2, 1.5, 29.9, loadedFoods2.get(1));
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not have been thrown");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
