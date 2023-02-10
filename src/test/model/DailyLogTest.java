package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyLogTest {

    private DailyLog log1;
    private DailyLog log2;
    private DailyLog log3;

    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;
    private Food f5;

    @BeforeEach
    public void setup() {
        log1 = new DailyLog(2000, 2, 29);
        log2 = new DailyLog(1900, 2, 28);
        log3 = new DailyLog(2023, 2, 10);

        f1 = new Food("Avocado", 68, 17.9, 23.6, 1.9);
        f2 = new Food("Steak", 189, 17.8, 12.3, 49.4);
        f3 = new Food("Tofu", 188, 3, 23.7, 6.7);
        f4 = new Food("Seaweed", 72, 3.7, 2.8, 49.5);
        f5 = new Food("Thai chicken", 68, 17.8, 23.7, 49.5);

        log3.addFood(f1);
        log3.addFood(f2);
        log3.addFood(f3);
        log3.addFood(f4);
    }

    @Test
    public void testConstructor() {
        // check initial values
        assertEquals(LocalDate.of(2000, 2, 29), log1.getDate());
        assertEquals(LocalDate.of(1900, 2, 28), log2.getDate());
        assertEquals(LocalDate.of(2023, 2, 10), log3.getDate());

        assertTrue(log1.getFoodLog().isEmpty());
        assertTrue(log2.getFoodLog().isEmpty());
        assertEquals(4, log3.getFoodLog().size());
    }

    @Test
    public void testAddFood() {
        // invoke behaviour to test
        log1.addFood(f1);
        log1.addFood(f2);
        log1.addFood(f3);
        log1.addFood(f4);

        // check output
        assertEquals(4, log1.getFoodLog().size());
        assertEquals(f1, log1.getFoodLog().get(0));
        assertEquals(f2, log1.getFoodLog().get(1));
        assertEquals(f3, log1.getFoodLog().get(2));
        assertEquals(f4, log1.getFoodLog().get(3));
    }

    @Test
    public void testRemoveFood() {
        // invoke behaviour to test
        log3.removeFood(1);

        // check output
        assertEquals(3, log3.getFoodLog().size());
        assertEquals(f1, log3.getFoodLog().get(0));
        assertEquals(f3, log3.getFoodLog().get(1));
        assertEquals(f4, log3.getFoodLog().get(2));
    }

    @Test
    public void testRemoveFoodUntilEmpty() {
        // invoke behaviour to test
        log3.removeFood(0);
        log3.removeFood(0);
        log3.removeFood(0);
        log3.removeFood(0);

        // check output
        assertTrue(log1.getFoodLog().isEmpty());
    }

    @Test
    public void testSetDate() {
        // check initial value
        assertEquals(log1.getDate(), LocalDate.of(2000, 2, 29));

        // invoke behaviour to test
        log1.setDate(1900, 2, 28);

        // check output
        assertEquals(log1.getDate(), log2.getDate());
    }

    @Test
    public void testSetDateToSameDate() {
        // check initial value
        assertEquals(log2.getDate(), LocalDate.of(1900, 2, 28));

        // invoke behaviour to test
        log2.setDate(1900, 2, 28);

        // check output
        assertEquals(log2.getDate(), LocalDate.of(1900, 2, 28));
    }

    @Test
    public void testCompareDailyLogDates(){
        // invoke behaviour to test and check output
        assertEquals(100, DailyLog.compareDailyLogDates(log1, log2));
        assertEquals(-100, DailyLog.compareDailyLogDates(log2, log1));

        assertEquals(-23, DailyLog.compareDailyLogDates(log1, log3));
        assertEquals(23, DailyLog.compareDailyLogDates(log3, log1));

        assertEquals(1, DailyLog.compareDailyLogDates(log1, new DailyLog(2000, 2, 28)));
        assertEquals(0, DailyLog.compareDailyLogDates(log1, new DailyLog(2000, 2, 29)));
        assertEquals(-1, DailyLog.compareDailyLogDates(log1, new DailyLog(2000, 3, 1)));
    }

    @Test
    public void testGetFoodWithMostCalories() {
        // create initial condition
        log1.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(f1, log1.getFoodWithMostCalories());
        assertEquals(f2, log3.getFoodWithMostCalories());
    }

    @Test
    public void testGetFoodWithMostCaloriesWithTie() {
        // create initial condition
        log1.addFood(f1);
        log1.addFood(f5);

        log2.addFood(f5);
        log2.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(f1, log1.getFoodWithMostCalories());
        assertEquals(f5, log2.getFoodWithMostCalories());
    }

    @Test
    public void testGetFoodWithMostFat() {
        // create initial condition
        log1.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(f1, log1.getFoodWithMostFat());
        assertEquals(f1, log3.getFoodWithMostFat());
    }

    @Test
    public void testGetFoodWithMostFatWithTie() {
        // create initial condition
        log1.addFood(f2);
        log1.addFood(f5);

        log2.addFood(f5);
        log2.addFood(f2);

        // invoke behaviour to test and check output
        assertEquals(f2, log1.getFoodWithMostFat());
        assertEquals(f5, log2.getFoodWithMostFat());
    }

    @Test
    public void testGetFoodWithMostSugar() {
        // create initial condition
        log1.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(f1, log1.getFoodWithMostSugar());
        assertEquals(f3, log3.getFoodWithMostSugar());
    }

    @Test
    public void testGetFoodWithMostSugarWithTie() {
        // create initial condition
        log1.addFood(f3);
        log1.addFood(f5);

        log2.addFood(f5);
        log2.addFood(f3);

        // invoke behaviour to test and check output
        assertEquals(f3, log1.getFoodWithMostSugar());
        assertEquals(f5, log2.getFoodWithMostSugar());
    }

    @Test
    public void testGetFoodWithMostProtein() {
        // create initial condition
        log1.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(f1, log1.getFoodWithMostProtein());
        assertEquals(f4, log3.getFoodWithMostProtein());
    }

    @Test
    public void testGetFoodWithMostProteinWithTie() {
        // create initial condition
        log1.addFood(f4);
        log1.addFood(f5);

        log2.addFood(f5);
        log2.addFood(f4);

        // invoke behaviour to test and check output
        assertEquals(f4, log1.getFoodWithMostProtein());
        assertEquals(f5, log2.getFoodWithMostProtein());
    }

    @Test
    public void testGetTotalCalories() {
        // create initial condition
        log2.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(0, log1.getTotalCalories());
        assertEquals(68, log2.getTotalCalories());
        assertEquals(517, log3.getTotalCalories());
    }

    @Test
    public void testGetTotalFat() {
        // create initial condition
        log2.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(0, log1.getTotalFat());
        assertEquals(17.9, log2.getTotalFat());
        assertEquals(42.4, log3.getTotalFat());
    }

    @Test
    public void testGetTotalSugar() {
        // create initial condition
        log2.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(0, log1.getTotalSugar());
        assertEquals(23.6, log2.getTotalSugar());
        assertEquals(62.4, log3.getTotalSugar());
    }

    @Test
    public void testGetTotalProtein() {
        // create initial condition
        log2.addFood(f1);

        // invoke behaviour to test and check output
        assertEquals(0, log1.getTotalProtein());
        assertEquals(1.9, log2.getTotalProtein());
        assertEquals(107.5, log3.getTotalProtein());
    }
}
