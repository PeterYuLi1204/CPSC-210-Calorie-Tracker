package persistence;

import model.DailyLog;
import model.Diary;
import model.Food;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    // Code is based on the JsonReaderTest class in the JsonSerializationDemo program from the Phase 2
    // Tests reading a file that does not exist
    @Test
    public void testReadNonExistantFile() {
        JsonReader jsonReader = new JsonReader("./data/nullFile.json");
        try {
            jsonReader.load();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Code is based on the JsonReaderTest class in the JsonSerializationDemo program from the Phase 2
    // Tests reading an empty diary from file
    @Test
    public void testReadEmptyDiary() {
        JsonReader jsonReader = new JsonReader("./data/testReadEmptyDiary.json");
        try {
            Diary diary = jsonReader.load();
            assertTrue(diary.getDailyLogs().isEmpty());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    // Code is based on the JsonReaderTest class in the JsonSerializationDemo program from the Phase 2
    // Tests reading a general diary from file
    @Test
    public void testReadGeneralDiary() {
        JsonReader jsonReader = new JsonReader("./data/testReadGeneralDiary.json");
        try {
            // Loading diary from file
            Diary diary = jsonReader.load();

            // Checking number and dates of daily logs
            List<DailyLog> dailyLogs = diary.getDailyLogs();
            assertEquals(2, dailyLogs.size());
            assertEquals(LocalDate.of(1, 1, 1), dailyLogs.get(0).getDate());
            assertEquals(LocalDate.of(2023, 3, 8), dailyLogs.get(1).getDate());

            // Checking number of foods in daily logs
            List<Food> foods1 = dailyLogs.get(0).getFoods();
            List<Food> foods2 = dailyLogs.get(1).getFoods();
            assertEquals(2, foods1.size());
            assertEquals(3, foods2.size());

            // Checking that foods remain the same
            compareFood("Apple", 150, 27.2, 16.9, 2.1, foods1.get(0));
            compareFood("Banana", 280, 0, 17.8, 1.2, foods1.get(1));
            compareFood("Steak", 562, 32.4, 9.8, 57.9, foods2.get(0));
            compareFood("Pasta", 989, 14.5, 67.1, 22.1, foods2.get(1));
            compareFood("Strawberry Smoothie", 127, 4, 12.7, 1, foods2.get(2));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
