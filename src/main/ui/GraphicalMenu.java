package ui;

import model.Diary;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphicalMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static final String LOCATION = "./data/diary.json";
    private Diary diary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Starts a graphical menu of program at the main menu
    public GraphicalMenu() {
        super("Calorie Tracker");
        initializeFields();
        initializeMainMenuInputs();
        initializeGraphics();
    }

    private void initializeFields() {
        diary = new Diary();
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
    }

    // Based off the code from the DrawingEditor class in the SimpleDrawingPlayer-Starter program
    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this CalorieTracker will operate, and creates the appropriate buttons
    private void initializeGraphics() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeMainMenuInputs() {
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        add(saveButton);
        add(loadButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                saveDiary();
                break;
            case "Load":
                loadDiary();
                break;
        }
    }

    // Code is based on the WorkRoomApp class in the JsonSerializationDemo program from the Phase 2
    // EFFECTS: Saves the diary to file
    private void saveDiary() {
        try {
            jsonWriter.save(diary);
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
        } catch (IOException e) {
            displayErrorMessage("Unable to read from file!");
        }
    }

    private void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
    }
}
