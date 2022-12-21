package com.example.kr_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Locale;

//import static jdk.internal.org.jline.terminal.Terminal.MouseTracking.Button;

/*
public class CalendarApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }*/


    public class CalendarApp extends Application {
        private CalendarPane calendarPane = new CalendarPane();
        private Button btPrevious = new Button("Previous");
        private Button btNext = new Button("Next");
        private ComboBox<String> cboLocales = new ComboBox<>();
        private Locale[] availableLocales = Locale.getAvailableLocales();
        //МЕТОД. Намира всички налични езици за календара


        @Override // Override the start method in the Application class
        public void start(Stage primaryStage) {
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btPrevious, btNext);
            hBox.setAlignment(Pos.CENTER);

            // Initialize cboLocales with all available locales
            setAvailableLocales();
            HBox hBoxLocale = new HBox(5);
            hBoxLocale.getChildren().addAll(
                    new Label("Select a locale"), cboLocales);

            BorderPane pane = new BorderPane();


            pane.setCenter(calendarPane);
            pane.setTop(hBoxLocale);
            hBoxLocale.setAlignment(Pos.CENTER);
            pane.setBottom(hBox);
            hBox.setAlignment(Pos.CENTER);

            // Create a scene and place it in the stage
            Scene scene = new Scene(pane, 600, 300);
            primaryStage.setTitle("CalendarApp"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage
            primaryStage.show(); // Display the stage

            btPrevious.setOnAction(e -> {
                int currentMonth = calendarPane.getMonth();
                if (currentMonth == 0) { // The previous month is 11 for Dec
                    calendarPane.setYear(calendarPane.getYear() - 1);
                    calendarPane.setMonth(11);
                } else {
                    calendarPane.setMonth((currentMonth - 1) % 12);
                }
            });

            btNext.setOnAction(e -> {
                int currentMonth = calendarPane.getMonth();
                if (currentMonth == 11) // The next month is 0 for Jan
                    calendarPane.setYear(calendarPane.getYear() + 1);

                calendarPane.setMonth((currentMonth + 1) % 12);
            });

            cboLocales.setOnAction(e ->
                    calendarPane.setLocale(availableLocales[cboLocales.
                            getSelectionModel().getSelectedIndex()]));
        }

        private void setAvailableLocales() {
            for (int i = 0; i < availableLocales.length; i++)
                cboLocales.getItems().add(availableLocales[i]
                        .getDisplayName() + " " + availableLocales[i].toString());
            //Връща имената на всички езици и добавя името в полето.

            cboLocales.getSelectionModel().selectFirst();
        }



    public static void main(String[] args) {
        launch();
    }
}