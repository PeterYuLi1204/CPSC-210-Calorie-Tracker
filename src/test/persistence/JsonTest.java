package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class JsonTest {
    protected void compareFood(String name, int calories, double fat, double sugar, double protein, Food food) {
        assertEquals(name, food.getName());
        assertEquals(calories, food.getCalories());
        assertEquals(fat, food.getFat());
        assertEquals(sugar, food.getSugar());
        assertEquals(protein, food.getProtein());
    }
}
