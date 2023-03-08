package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class JsonTest {
    // Code is based on the JsonTest class in the JsonSerializationDemo program from the Phase 2
    protected void compareFood(String name, int calories, double fat, double sugar, double protein, Food food) {
        assertEquals(name, food.getName());
        assertEquals(calories, food.getCalories());
        assertEquals(fat, food.getFat());
        assertEquals(sugar, food.getSugar());
        assertEquals(protein, food.getProtein());
    }
}
