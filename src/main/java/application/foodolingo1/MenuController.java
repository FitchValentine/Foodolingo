package application.foodolingo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label timeLabel;

    @FXML
    private Label pulseLabel;

    @FXML
    private TextField seasonTextField;
    @FXML
    private Button showImagesButton;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    private int pulseValue;

    public void initialize() {
        // You can add any initialization code here if needed
        startTimers();  // Start the timers when the controller is initialized
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

    private void startTimers() {
        // Создаем объект Timeline с обновлением каждую секунду для времени
        Timeline timeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateTimeLabel())
        );

        // Создаем объект Timeline с обновлением каждую секунду для пульса
        Timeline pulseTimeline = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> updatePulseLabel())
        );

        timeTimeline.setCycleCount(Timeline.INDEFINITE);
        pulseTimeline.setCycleCount(Timeline.INDEFINITE);

        timeTimeline.play();
        pulseTimeline.play();
    }

    private void updateTimeLabel() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("Время HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        timeLabel.setText(formattedTime);
    }

    private void updatePulseLabel() {
        int randomChange = (int) (Math.random() * 10 - 5);
        pulseValue += randomChange;
        pulseValue = Math.max(60, Math.min(100, pulseValue));
        pulseLabel.setText("Пульс: " + pulseValue);
    }

    @FXML
    private void onShowImagesButtonClicked() {
        String season = seasonTextField.getText().toLowerCase();

        // Очищаем изображения перед обновлением
        clearImages();

        // Загружаем изображения в зависимости от времени года
        switch (season) {
            case "осень":
                loadImage("/application/foodolingo1/SeasonMenu/autumn1.png", imageView1);
                loadImage("/application/foodolingo1/SeasonMenu/autumn2.png", imageView2);
                loadImage("/application/foodolingo1/SeasonMenu/autumn3.png", imageView3);
                break;
            case "зима":
                loadImage("/application/foodolingo1/SeasonMenu/winter1.png", imageView1);
                loadImage("/application/foodolingo1/SeasonMenu/winter2.png", imageView2);
                loadImage("/application/foodolingo1/SeasonMenu/winter3.png", imageView3);
                break;
            case "весна":
                loadImage("/application/foodolingo1/SeasonMenu/spring1.png", imageView1);
                loadImage("/application/foodolingo1/SeasonMenu/spring2.png", imageView2);
                loadImage("/application/foodolingo1/SeasonMenu/spring3.png", imageView3);
                break;
            case "лето":
                loadImage("/application/foodolingo1/SeasonMenu/summer1.png", imageView1);
                loadImage("/application/foodolingo1/SeasonMenu/summer2.png", imageView2);
                loadImage("/application/foodolingo1/SeasonMenu/summer3.png", imageView3);
                break;
            default:
                // Обработка некорректного ввода времени года
                break;
        }
    }

    private void loadImage(String fileName, ImageView imageView) {
        // Загрузка изображения из файла и установка его в ImageView
        Image image = new Image(getClass().getResourceAsStream(fileName));
        imageView.setImage(image);
    }

    private void clearImages() {
        // Очистка ImageView перед загрузкой новых изображений
        imageView1.setImage(null);
        imageView2.setImage(null);
        imageView3.setImage(null);
    }
}
