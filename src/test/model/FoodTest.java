package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {

    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;

    @BeforeEach
    public void setup() {
        f1 = new Food("Avocado", 68, 3.1, 23.6, 1.9);
        f2 = new Food("Steak", 189, 17.8, 12.3, 49.4);
        f3 = new Food("Tofu", 140, 3, 39, 6.7);
        f4 = new Food("Seaweed", 72, 3.7, 2.8, 8);
    }

    @Test
    public void testConstructor() {
        // check initial values
        assertEquals("Avocado", f1.getName());
        assertEquals("Steak", f2.getName());
        assertEquals("Tofu", f3.getName());
        assertEquals("Seaweed", f4.getName());

        assertEquals(68, f1.getCalories());
        assertEquals(189, f2.getCalories());
        assertEquals(140, f3.getCalories());
        assertEquals(72, f4.getCalories());

        assertEquals(3.1, f1.getFat());
        assertEquals(17.8, f2.getFat());
        assertEquals(3, f3.getFat());
        assertEquals(3.7, f4.getFat());

        assertEquals(23.6, f1.getSugar());
        assertEquals(49.4, f2.getProtein());
        assertEquals(39, f3.getSugar());
        assertEquals(2.8, f4.getSugar());

        assertEquals(1.9, f1.getProtein());
        assertEquals(12.3, f2.getSugar());
        assertEquals(6.7, f3.getProtein());
        assertEquals(8, f4.getProtein());
    }

    @Test
    public void testSetName() {
        // invoke behaviour to test
        f1.setName("a");
        f2.setName("5 hour energy");
        f3.setName("OMG! Biscuit");
        f4.setName("Seaweed");

        // check output
        assertEquals("a", f1.getName());
        assertEquals("5 hour energy", f2.getName());
        assertEquals("OMG! Biscuit", f3.getName());
        assertEquals("Seaweed", f4.getName());
    }

    @Test
    public void testSetCalories() {
        // invoke behaviour to test
        f1.setCalories(0);
        f2.setCalories(270);
        f3.setCalories(120);
        f4.setCalories(72);

        // check output
        assertEquals(0, f1.getCalories());
        assertEquals(270, f2.getCalories());
        assertEquals(120, f3.getCalories());
        assertEquals(72, f4.getCalories());
    }

    @Test
    public void testSetFat() {
        // invoke behaviour to test
        f1.setFat(0);
        f2.setFat(19.2);
        f3.setFat(4.5);
        f4.setFat(3.7);

        // check output
        assertEquals(0, f1.getFat());
        assertEquals(19.2, f2.getFat());
        assertEquals(4.5, f3.getFat());
        assertEquals(3.7, f4.getFat());
    }

    @Test
    public void testSetSugar() {
        // invoke behaviour to test
        f1.setSugar(0);
        f2.setSugar(18.3);
        f3.setSugar(12.4);
        f4.setSugar(2.8);

        // check output
        assertEquals(0, f1.getSugar());
        assertEquals(18.3, f2.getSugar());
        assertEquals(12.4, f3.getSugar());
        assertEquals(2.8, f4.getSugar());
    }

    @Test
    public void testSetProtein() {
        // invoke behaviour to test
        f1.setProtein(0);
        f2.setProtein(69.3);
        f3.setProtein(3.1);
        f4.setProtein(8);

        // check output
        assertEquals(0, f1.getProtein());
        assertEquals(69.3, f2.getProtein());
        assertEquals(3.1, f3.getProtein());
        assertEquals(8, f4.getProtein());
    }
}