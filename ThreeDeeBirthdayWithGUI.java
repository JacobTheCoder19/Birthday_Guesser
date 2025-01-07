/**
 * Created by: Jacob Graham
 * Date of creation: 01/07/2025
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ThreeDeeBirthdayWithGUI extends Application {

    private int day = 0;
    private int[][][] dates = {
        {
            {16, 17, 18, 19}, 
            {20, 21, 22, 23}, 
            {24, 25, 26, 27},
            {28, 29, 30, 31}
        },
        {
            {8, 9, 10, 11},
            {12, 13, 14, 15},
            {24, 25, 26, 27},
            {28, 29, 30, 31}
        },
        {
            {4, 5, 6, 7},
            {12, 13, 14, 15},
            {20, 21, 22, 23},
            {28, 29, 30, 31},
        },
        {
            {2, 3, 6, 7},
            {10, 11, 14, 15},
            {18, 19, 22, 23},
            {26, 27, 30, 31}
        },
        {
            {1, 3, 5, 7},
            {9, 11, 13, 15},
            {17, 19, 21, 23},
            {25, 27, 29, 31}
        }
    };

    @Override
    public void start(Stage primaryStage) {
        // Get the birthday month
        TextInputDialog monthDialog = new TextInputDialog();
        monthDialog.setTitle("Birthday Guesser");
        monthDialog.setHeaderText("Birthday Month Input");
        monthDialog.setContentText("Enter your birthday month:");

        String month = monthDialog.showAndWait().orElse("");

        if (month.isEmpty()) {
            showAlert("Input Error", "Month cannot be empty.", AlertType.ERROR);
            return;
        }

        // Start guessing the day
        for (int i = 0; i < 5; i++) {
            StringBuilder setDisplay = new StringBuilder();
            setDisplay.append("Is your birthday in this set?\n\n");

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    setDisplay.append(dates[i][j][k]).append("\t");
                }
                setDisplay.append("\n");
            }

            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setTitle("Set " + (i + 1));
            confirmAlert.setHeaderText("Set " + (i + 1));
            confirmAlert.setContentText(setDisplay.toString());

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            confirmAlert.getButtonTypes().setAll(yesButton, noButton);

            ButtonType result = confirmAlert.showAndWait().orElse(noButton);

            if (result == yesButton) {
                day += dates[i][0][0];
            }
        }

        // Display the birthday
        showAlert("Birthday Guesser", "Your birthday is: " + month + " " + day, AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
