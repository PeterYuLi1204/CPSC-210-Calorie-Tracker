package ui;

import model.DailyLog;
import model.Diary;
import model.Food;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphicalMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private static final String LOCATION = "./data/diary.json";
    private Diary diary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainMenuButtonPanel;
    private JPanel dailyLogButtonPanel;
    private JPanel dailyLogListPanel;
    private JPanel foodListPanel;
    private JScrollPane dailyLogListPanelScrollable;
    private JScrollPane foodListPanelScrollable;

    private DailyLog currentDailyLog;

    // EFFECTS: Starts a graphical menu of program at the main menu
    public GraphicalMenu() {
        super("Calorie Tracker");
        initializeFields();
        initializeGraphics();
    }

    // Initialization

    // MODIFIES: this
    // EFFECTS: Initializes the diary and saving and writing functions
    private void initializeFields() {
        diary = new Diary();
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
    }

    // Based off the code from the DrawingEditor class in the SimpleDrawingPlayer-Starter program
    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this CalorieTracker will operate, and creates the appropriate buttons
    private void initializeGraphics() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeDailyLogListPanel();
        initializeMainMenuInputs();
        initializeFoodListPanel();
        initializeDailyLogInputs();
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the graphical display for list of daily logs in the diary
    private void initializeDailyLogListPanel() {
        dailyLogListPanel = new JPanel(new GridLayout(0, 2));
        dailyLogListPanelScrollable = new JScrollPane(dailyLogListPanel);
        dailyLogListPanelScrollable.setPreferredSize(new Dimension(WIDTH, 500));
        add(dailyLogListPanelScrollable);
    }

    // EFFECTS: Initializes the graphical display for list of foods in the daily log
    private void initializeFoodListPanel() {
        foodListPanel = new JPanel(new GridLayout(0, 1));
        foodListPanelScrollable = new JScrollPane(foodListPanel);
        foodListPanelScrollable.setPreferredSize(new Dimension(WIDTH, 500));
        foodListPanelScrollable.setVisible(false);
        add(foodListPanelScrollable);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the buttons for the main menu
    private void initializeMainMenuInputs() {
        mainMenuButtonPanel = new JPanel(new GridBagLayout());
        mainMenuButtonPanel.setBackground(Color.GRAY);
        add(mainMenuButtonPanel);

        JButton addDailyLogButton = new JButton("Add Daily Log");
        JButton averageNutritionButton = new JButton("Average Nutritional Intake");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        addDailyLogButton.addActionListener(this);
        averageNutritionButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        mainMenuButtonPanel.add(addDailyLogButton);
        mainMenuButtonPanel.add(averageNutritionButton);
        mainMenuButtonPanel.add(saveButton);
        mainMenuButtonPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the buttons for the daily log viewing menu
    private void initializeDailyLogInputs() {
        dailyLogButtonPanel = new JPanel(new GridBagLayout());
        dailyLogButtonPanel.setBackground(Color.GRAY);
        dailyLogButtonPanel.setVisible(false);
        add(dailyLogButtonPanel);

        JButton addFoodButton = new JButton("Add Food");
        JButton editDateButton = new JButton("Edit Date");
        JButton totalNutritionButton = new JButton("Total Nutritional Intake");
        JButton backToMainMenuButton = new JButton("Back To Main Menu");
        addFoodButton.addActionListener(this);
        editDateButton.addActionListener(this);
        totalNutritionButton.addActionListener(this);
        backToMainMenuButton.addActionListener(this);

        dailyLogButtonPanel.add(addFoodButton);
        dailyLogButtonPanel.add(editDateButton);
        dailyLogButtonPanel.add(totalNutritionButton);
        dailyLogButtonPanel.add(backToMainMenuButton);
    }

    // Button Functionality

    // EFFECTS: Responds and continues the program when user presses a button
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Daily Log":
                handleAddDailyLog();
                break;
            case "Average Nutritional Intake":
                printAverageNutrition();
                break;
            case "Save":
                saveDiary();
                break;
            case "Load":
                loadDiary();
                break;
            case "Add Food":
                handleAddFood();
                break;
            case "Edit Date":
                handleEditDate();
                break;
            case "Total Nutritional Intake":
                printTotalNutrition();
                break;
            case "Back To Main Menu":
                switchMenu(true);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Switches to the appropriate menu based on the given boolean
    private void switchMenu(boolean switchToMain) {
        dailyLogButtonPanel.setVisible(!switchToMain);
        foodListPanelScrollable.setVisible(!switchToMain);
        mainMenuButtonPanel.setVisible(switchToMain);
        dailyLogListPanelScrollable.setVisible(switchToMain);

        if (switchToMain) {
            displayDailyLogs();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the daily logs in the diary
    private void displayDailyLogs() {
        dailyLogListPanel.removeAll();
        for (DailyLog dailyLog : diary.getDailyLogs()) {
            JPanel dailyLogCard = new JPanel(new BorderLayout());
            dailyLogCard.setPreferredSize(new Dimension(WIDTH / 2 - 10, 100));

            JButton dailyLogButton = new JButton(dailyLog.getDate().toString());
            JButton deleteButton = new JButton("X");

            dailyLogButton.addActionListener(E -> {
                switchMenu(false);
                displayFoods(dailyLog);
                currentDailyLog = dailyLog;
            });

            deleteButton.addActionListener(E -> {
                diary.getDailyLogs().remove(dailyLog);
                dailyLogListPanel.remove(dailyLogCard);
                validate();
                displaySuccessGraphic("Successfully deleted the daily log!");
            });

            dailyLogCard.add(dailyLogButton, BorderLayout.CENTER);
            dailyLogCard.add(deleteButton, BorderLayout.EAST);
            dailyLogListPanel.add(dailyLogCard);
        }
        validate();
    }

    // MODIFIES: this
    // EFFECTS: Displays the foods in the given daily log
    private void displayFoods(DailyLog dailyLog) {
        foodListPanel.removeAll();
        for (Food food : dailyLog.getFoods()) {
            JPanel foodCard = new JPanel(new BorderLayout());
            foodCard.setPreferredSize(new Dimension(WIDTH - 20, 150));

            JButton foodButton = new JButton(printFood(food));
            JButton deleteButton = new JButton("X");

            foodButton.addActionListener(E -> handleEditFood(food));

            deleteButton.addActionListener(E -> {
                dailyLog.getFoods().remove(food);
                foodListPanel.remove(foodCard);
                validate();
                displaySuccessGraphic("Successfully deleted the food!");
            });

            foodCard.add(foodButton, BorderLayout.CENTER);
            foodCard.add(deleteButton, BorderLayout.EAST);
            foodListPanel.add(foodCard);
        }
        validate();
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a daily log to the diary with graphical prompts and then displays it
    private void handleAddDailyLog() {
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Input the year"));
            int month = Integer.parseInt(JOptionPane.showInputDialog("Input the month"));
            int day = Integer.parseInt(JOptionPane.showInputDialog("Input the day"));

            diary.addDailyLog(new DailyLog(year, month, day));
            displayDailyLogs();
            displaySuccessGraphic("Successfully added a new daily log!");
        } catch (Exception e) {
            displayErrorMessage("Invalid date!");
        }
    }

    // EFFECTS: Displays a popup that shows the average nutritional intake for the diary
    private void printAverageNutrition() {
        String nutritionInformation = "Average calories per day: " + diary.getAverageCalories()
                + "\nAverage grams of fat per day: " + diary.getAverageFat() + " g"
                + "\nAverage grams of sugar per day: " + diary.getAverageSugar() + " g"
                + "\nAverage grams of protein per day: " + diary.getAverageProtein() + " g";
        JOptionPane.showMessageDialog(null, nutritionInformation,"Average Nutritional Intake",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/Apple.png"));
    }

    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Saves the diary to file
    private void saveDiary() {
        try {
            jsonWriter.save(diary);
            displaySuccessGraphic("Successfully saved the diary!");
        } catch (FileNotFoundException e) {
            displayErrorMessage("Unable to write to file!");
        }
    }

    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
    // MODIFIES: this
    // EFFECTS: Loads the diary from file
    private void loadDiary() {
        try {
            diary = jsonReader.load();
            displayDailyLogs();
            displaySuccessGraphic("Successfully loaded the diary!");
        } catch (IOException e) {
            displayErrorMessage("Unable to read from file!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new food to the daily log being viewed with graphical prompts and then displays it
    private void handleAddFood() {
        try {
            String name = JOptionPane.showInputDialog("Enter the name of the food");
            int calories = Integer.parseInt(JOptionPane.showInputDialog("Enter the calories of the food"));
            double fat = Double.parseDouble(JOptionPane.showInputDialog("Enter the grams of fat of the food"));
            double sugar = Double.parseDouble(JOptionPane.showInputDialog("Enter the grams of sugar of the food"));
            double protein = Double.parseDouble(JOptionPane.showInputDialog("Input the grams of protein of the food"));

            if (name.isEmpty() || calories < 0 || fat < 0 || sugar < 0 || protein < 0) {
                throw new Exception();
            }

            currentDailyLog.addFood(new Food(name, calories, fat, sugar, protein));
            displayFoods(currentDailyLog);
            displaySuccessGraphic("Successfully added a new food!");
        } catch (Exception e) {
            displayErrorMessage("Invalid food information provided!");
        }
    }

    // EFFECTS: Displays a popup that shows the total nutritional intake for the daily log being viewed
    private void printTotalNutrition() {
        String nutritionInformation = "Total calories consumed: " + currentDailyLog.getTotalCalories()
                + "\nTotal grams of fat consumed: " + currentDailyLog.getTotalFat() + " g"
                + "\nAverage grams of sugar consumed: " + currentDailyLog.getTotalSugar() + " g"
                + "\nAverage grams of protein consumed: " + currentDailyLog.getTotalProtein() + " g";
        JOptionPane.showMessageDialog(null, nutritionInformation,"Average Nutritional Intake",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/Pear.png"));
    }

    // MODIFIES: this
    // EFFECTS: Changes the date of the daily log being viewed with graphical prompts then displays it
    private void handleEditDate() {
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Input the year"));
            int month = Integer.parseInt(JOptionPane.showInputDialog("Input the month"));
            int day = Integer.parseInt(JOptionPane.showInputDialog("Input the day"));

            currentDailyLog.setDate(year, month, day);
            displaySuccessGraphic("Successfully changed the date!");
        } catch (Exception e) {
            displayErrorMessage("Invalid date!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Changes the information of the given food with graphical prompts and then displays it
    private void handleEditFood(Food food) {
        try {
            String name = JOptionPane.showInputDialog("Enter the name of the food");
            int calories = Integer.parseInt(JOptionPane.showInputDialog("Enter the calories of the food"));
            double fat = Double.parseDouble(JOptionPane.showInputDialog("Enter the grams of fat of the food"));
            double sugar = Double.parseDouble(JOptionPane.showInputDialog("Enter the grams of sugar of the food"));
            double protein = Double.parseDouble(JOptionPane.showInputDialog("Input the grams of protein of the food"));

            if (name.isEmpty() || calories < 0 || fat < 0 || sugar < 0 || protein < 0) {
                throw new Exception();
            }

            food.setName(name);
            food.setCalories(calories);
            food.setFat(fat);
            food.setSugar(sugar);
            food.setProtein(protein);

            displayFoods(currentDailyLog);
            displaySuccessGraphic("Successfully updated the food!");
        } catch (Exception e) {
            displayErrorMessage("Invalid food information provided!");
        }
    }

    // EFFECTS: Returns a formatted string that includes all information regarding the given food
    private String printFood(Food food) {
        return "<html>" + food.getName()
                + "<br />- Calories: " + food.getCalories()
                + "<br />- Grams of fat: " + food.getFat() + " g"
                + "<br />- Grams of sugar: " + food.getSugar() + " g"
                + "<br />- Grams of protein: " + food.getProtein() + " g </html>";
    }

    // Pop-up Messages

    // EFFECTS: Displays the given confimation message as a pop-up window with an icon
    private void displaySuccessGraphic(String successfulOperation) {
        JOptionPane.showMessageDialog(null, successfulOperation, "Confirmation",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/Confirmation.png"));
    }

    // EFFECTS: Displays the given warning message as a pop-up window with an icon
    private void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE,
                new ImageIcon("Image/Error.png"));
    }
}