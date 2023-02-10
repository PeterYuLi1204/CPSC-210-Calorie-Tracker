package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiaryTest {

    private Diary diary;

    private DailyLog log1;
    private DailyLog log2;

    @BeforeEach
    public void setup() {
        diary = new Diary();

        log1 = new DailyLog(2000, 2, 29);
        log2 = new DailyLog(1900, 2, 28);

        log1.addFood(new Food("Avocado", 68, 17.9, 23.6, 1.9));
        log1.addFood(new Food("Steak", 189, 17.8, 12.3, 49.4));

        log2.addFood(new Food("Tofu", 188, 3, 23.7, 6.7));
        log2.addFood(new Food("Seaweed", 72, 3.7, 2.8, 49.5));
        log2.addFood(new Food("Thai chicken", 26, 12.2, 31.7, 8.8));
    }

    @Test
    public void testConstructor() {
        // check initial values
        assertTrue(diary.getDailyLogs().isEmpty());
    }

    @Test
    public void testAddDailyLogInChronologicalOrder() {
        // invoke behaviour to test
        diary.addDailyLog(log2);
        diary.addDailyLog(log1);

        // check output
        assertEquals(2, diary.getDailyLogs().size());
        assertEquals(log2, diary.getDailyLogs().get(0));
        assertEquals(log1, diary.getDailyLogs().get(1));
    }

    @Test
    public void testAddDailyLogInWrongChronologicalOrder() {
        // invoke behaviour to test
        diary.addDailyLog(log1);
        diary.addDailyLog(log2);

        // check output
        assertEquals(2, diary.getDailyLogs().size());
        assertEquals(log2, diary.getDailyLogs().get(0));
        assertEquals(log1, diary.getDailyLogs().get(1));
    }

    @Test
    public void testRemoveDailyLog() {
        // create initial condition
        diary.addDailyLog(log1);
        diary.addDailyLog(log2);

        // invoke behaviour to test
        diary.removeDailyLog(0);

        // check output
        assertEquals(1, diary.getDailyLogs().size());
        assertEquals(log1, diary.getDailyLogs().get(0));
    }

    @Test
    public void testRemoveDailyLogUntilEmpty() {
        // create initial condition
        diary.addDailyLog(log1);
        diary.addDailyLog(log2);

        // invoke behaviour to test
        diary.removeDailyLog(0);
        diary.removeDailyLog(0);

        // check output
        assertTrue(diary.getDailyLogs().isEmpty());
    }

    @Test
    public void testGetAverageCalories() {
        // check initial behaviour and output
        assertEquals(0, diary.getAverageCalories());

        diary.addDailyLog(log1);

        // invoke behaviour to test and check output
        assertEquals(257, diary.getAverageCalories());

        diary.addDailyLog(log2);

        // invoke behaviour to test and check output
        assertEquals(271, diary.getAverageCalories());
    }

    @Test
    public void testGetAverageFat() {
        // check initial behaviour and output
        assertEquals(0, diary.getAverageFat());

        diary.addDailyLog(log1);

        // invoke behaviour to test and check output
        assertEquals(35.7, diary.getAverageFat());

        diary.addDailyLog(log2);

        // invoke behaviour to test and check output
        assertEquals(27.3, diary.getAverageFat());
    }

    @Test
    public void testGetAverageSugar() {
        // check initial behaviour and output
        assertEquals(0, diary.getAverageSugar());

        diary.addDailyLog(log1);

        // invoke behaviour to test and check output
        assertEquals(35.9, diary.getAverageSugar());

        diary.addDailyLog(log2);

        // invoke behaviour to test and check output
        assertEquals(47.1, diary.getAverageSugar());
    }

    @Test
    public void testGetAverageProtein() {
        // check initial behaviour and output
        assertEquals(0, diary.getAverageProtein());

        diary.addDailyLog(log1);

        // invoke behaviour to test and check output
        assertEquals(51.3, diary.getAverageProtein());

        diary.addDailyLog(log2);

        // invoke behaviour to test and check output
        assertEquals(58.2, diary.getAverageProtein());
    }
}
