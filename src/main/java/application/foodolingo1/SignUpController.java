package application.foodolingo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox SignUpCheckBoxAlse;

    @FXML
    private CheckBox SignUpCheckBoxFemale;

    @FXML
    private CheckBox SignUpCheckBoxMale;

    @FXML
    private TextField SignUpHeight;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField SignUpAge;

    @FXML
    private TextField SignUpWeight;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();


        loginSignUpButton.setOnAction(event -> {
            SignUpNewUser();

        });
    }

    private void SignUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = SignUpName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String weight = SignUpWeight.getText();
        String height = SignUpHeight.getText();
        String age = SignUpAge.getText();
        String gender = "";

        if (SignUpCheckBoxMale.isSelected())
            gender = "Мужской";
        if (SignUpCheckBoxFemale.isSelected())
            gender = "Женский";
        else
            gender = "Другой";
        User user = new User(firstName, userName, password, weight, height, age, gender);
        dbHandler.signUpUser(user);

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}