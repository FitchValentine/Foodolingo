package application.foodolingo1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwitchController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ingredientField;

    @FXML
    private Label resultLabel;

    @FXML
    private Label resultLabel1;

    @FXML
    private Label timeLabel;

    @FXML
    private Label pulseLabel; // Новый Label для отображения пульса

    @FXML
    private Button searchButton;

    private static final String API_KEY = "uxDBQoWOHl9uhg+OBj7wBQ==VEr2hsa0oqJRxYDv";

    private static final int INITIAL_PULSE = 70;
    private int pulseValue = INITIAL_PULSE;

    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), this::updateTimeLabel)
        );

        Timeline pulseTimeline = new Timeline(
                new KeyFrame(Duration.seconds(2), this::updatePulse)
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        pulseTimeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
        pulseTimeline.play();
    }

    private void updateTimeLabel(ActionEvent event) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("Время HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        timeLabel.setText(formattedTime);
    }

    private void updatePulse(ActionEvent event) {
        int randomChange = (int) (Math.random() * 10 - 5);
        pulseValue += randomChange;
        pulseValue = Math.max(60, Math.min(100, pulseValue));
        pulseLabel.setText("Пульс: " + pulseValue);
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene4(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene5(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene6(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene6.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void searchNutrition() {
        String ingredient = ingredientField.getText();
        if (!ingredient.isEmpty()) {
            try {
                String apiUrl = "https://api.api-ninjas.com/v1/nutrition?query=" + ingredient;
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("X-Api-Key", API_KEY); // Добавляем API-ключ в заголовок запроса

                try (InputStream responseStream = connection.getInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(responseStream);

                    // Получаем значения из JSON
                    JsonNode firstResult = root.get(0);
                    String calories = firstResult.path("calories").isNull() ? "N/A" : firstResult.path("calories").asText();
                    String servingSize = firstResult.path("serving_size_g").isNull() ? "N/A" : firstResult.path("serving_size_g").asText();
                    String fatTotal = firstResult.path("fat_total_g").isNull() ? "N/A" : firstResult.path("fat_total_g").asText();
                    String fatSaturated = firstResult.path("fat_saturated_g").isNull() ? "N/A" : firstResult.path("fat_saturated_g").asText();
                    String protein = firstResult.path("protein_g").isNull() ? "N/A" : firstResult.path("protein_g").asText();
                    String sodium = firstResult.path("sodium_mg").isNull() ? "N/A" : firstResult.path("sodium_mg").asText();
                    String potassium = firstResult.path("potassium_mg").isNull() ? "N/A" : firstResult.path("potassium_mg").asText();
                    String cholesterol = firstResult.path("cholesterol_mg").isNull() ? "N/A" : firstResult.path("cholesterol_mg").asText();
                    String carbohydratesTotal = firstResult.path("carbohydrates_total_g").isNull() ? "N/A" : firstResult.path("carbohydrates_total_g").asText();
                    String fiber = firstResult.path("fiber_g").isNull() ? "N/A" : firstResult.path("fiber_g").asText();
                    String sugar = firstResult.path("sugar_g").isNull() ? "N/A" : firstResult.path("sugar_g").asText();

                    // Выводим значения в UI
                    resultLabel.setText(
                            "Калории (в килокалориях): " + calories +
                                    "\nПорция (в граммах): " + servingSize +
                                    "\nЖиры (в граммах): " + fatTotal +
                                    "\nНасыщенные жиры (в граммах): " + fatSaturated +
                                    "\nБелок (в граммах): " + protein +
                                    "\nНатрий (в граммах): " + sodium +
                                    "\nКалий (в граммах): " + potassium +
                                    "\nХолестерин (в граммах): " + cholesterol +
                                    "\nУглеводы (в граммах): " + carbohydratesTotal +
                                    "\nКлетчатка (в граммах): " + fiber +
                                    "\nСахар (в граммах): " + sugar
                    );

                    // Проверка количества калорий и вывод сообщения
                    if (!calories.equals("N/A")) {
                        double caloriesValue = Double.parseDouble(calories);
                        if (caloriesValue > 100) {
                            resultLabel1.setText("Что-то тут\n многовато \n калорий\n ты и так\n жирный!!!");
                        } else {
                            resultLabel1.setText("Молодец\n продолжай \nв том же духе!");
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
