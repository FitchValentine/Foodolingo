package application.foodolingo1;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;


public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene3(ActionEvent event) throws IOException {
        // Здесь вы можете установить продолжительность анимации (в миллисекундах)
        int animationDuration = 1000;

        // Создаем анимацию затухания
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(animationDuration), root);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // Анимация проявления после переключения сцены
                FadeTransition fadeInTransition = new FadeTransition(Duration.millis(animationDuration), root);
                fadeInTransition.setFromValue(0.0);
                fadeInTransition.setToValue(1.0);
                fadeInTransition.play();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Запускаем анимацию затухания
        fadeTransition.play();
    }


    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private Button authSignInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSignUp;

    private void loginUser(String loginText, String loginPassword, ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        dbHandler.getUser(user);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1) {
            try {
                switchToScene3(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword, event);
            else
                System.out.println("Поля логина и пароля пусты");
        });
    }

}


