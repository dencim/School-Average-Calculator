package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    TableView<StudentRecord> table = new TableView<>();

    public String currentFileName = "marks";

    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();

        Scene scene = new Scene(vbox);


        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                DataSource.removeAll();
                table.getItems().clear();
                table.setItems(DataSource.removeAll());
            }
        });

        //Open
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //label.setText("Accepted");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
                File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile != null) {
                    //stage.display(selectedFile);
                    table.getItems().clear();
                    try {
                        DataSource.setAllMarks(selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Failed to load");
                    }
                    currentFileName = selectedFile.getName();
                    table.setItems(DataSource.getAllMarks());
                }
            }
        });

        //Save
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    DataSource.saveAllMarks(currentFileName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Save As
        MenuItem saveAsFile = new MenuItem("Save As");
        saveAsFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save As");
                //System.out.println(pic.getId());
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {

                    //ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),null), "png", file);
                    currentFileName = file.getName();
                    try {
                        DataSource.saveAllMarks(currentFileName);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    //
                }
            }
        });

        //Exit
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFile.getItems().addAll(newFile, openFile, saveFile, saveAsFile, exit);

        menuBar.getMenus().addAll(menuFile);


        //Table
        TableColumn<StudentRecord, String> studentidcolumn = new TableColumn<>("SID");
        studentidcolumn.setMinWidth(200);
        studentidcolumn.setCellValueFactory(new PropertyValueFactory<>("studentid"));

        TableColumn<StudentRecord, Float> assignmentcolumn = new TableColumn<>("Assignments");
        assignmentcolumn.setMinWidth(200);
        assignmentcolumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn<StudentRecord, Float> midtermcolumn = new TableColumn<>("Midterm");
        midtermcolumn.setMinWidth(200);
        midtermcolumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn<StudentRecord, Float> finalcolumn = new TableColumn<>("Final Exam");
        finalcolumn.setMinWidth(200);
        finalcolumn.setCellValueFactory(new PropertyValueFactory<>("finalexam"));

        TableColumn<StudentRecord, Float> finalmarkcolumn = new TableColumn<>("Final Mark");
        finalmarkcolumn.setMinWidth(200);
        finalmarkcolumn.setCellValueFactory(new PropertyValueFactory<>("finalmark"));

        TableColumn<StudentRecord, String> lettergradecolumn = new TableColumn<>("Letter Grade");
        lettergradecolumn.setMinWidth(200);
        lettergradecolumn.setCellValueFactory(new PropertyValueFactory<>("lettergrade"));

        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(studentidcolumn, assignmentcolumn, midtermcolumn, finalcolumn, finalmarkcolumn, lettergradecolumn);

        //Grid

        TextField ssidText = new TextField();
        TextField midtermText = new TextField();
        TextField assignmentsText = new TextField();
        TextField finalExamText = new TextField();
        Button addButton = new Button("Add");

        GridPane grid = new GridPane();
        grid.add(new Label("SSID"), 0, 0);
        grid.add(new Label("Midterm"), 0, 1);
        grid.add(ssidText, 1, 0);
        grid.add(midtermText, 1, 1);
        grid.add(addButton, 1, 2);
        grid.add(new Label("Assignments"), 2, 0);
        grid.add(new Label("Final Exam"), 2, 1);
        grid.add(assignmentsText, 3, 0);
        grid.add(finalExamText, 3, 1);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                DataSource.addMark(ssidText.getText(), Float.parseFloat(assignmentsText.getText()),Float.parseFloat(midtermText.getText()), Float.parseFloat(finalExamText.getText())  );
                table.setItems(DataSource.getAllMarks());
                //table.getColumns().addAll(studentidcolumn, assignmentcolumn, midtermcolumn, finalcolumn, finalmarkcolumn, lettergradecolumn);

            }
        });

        //

        vbox.getChildren().addAll(menuBar, table, grid);

        stage.setScene(scene);
        stage.setTitle("Denis - Lab 08");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
