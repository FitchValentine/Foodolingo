package application.foodolingo1;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OpenCaseController {

    @FXML
    private ImageView caseImageView;

    @FXML
    private Label resultLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label pulseLabel;

    private List<Image> caseImages;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private List<String> imageLabels;

    private static final int INITIAL_PULSE = 70;
    private int pulseValue = INITIAL_PULSE;

    public void initialize() {
        caseImages = List.of(
                new Image(getClass().getResourceAsStream("/application/foodolingo1/Meshes/Cake.jpg")),
                new Image(getClass().getResourceAsStream("/application/foodolingo1/Meshes/Chips.jpg")),
                new Image(getClass().getResourceAsStream("/application/foodolingo1/Meshes/Pizza.jpg"))
        );

        imageLabels = List.of(
                "Торт, много \n крема и сахара\n но сегодня\n можно!",
                "Хьюстон\n у нас\n лейс!",
                "Вау\n Пицца\n Давайте\n покушаем!"
        );

        // Создаем объект Timeline с обновлением каждую секунду для времени
        Timeline timeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), this::updateTimeLabel)
        );

        // Создаем объект Timeline с обновлением каждую секунду для пульса
        Timeline pulseTimeline = new Timeline(
                new KeyFrame(Duration.seconds(2), this::updatePulseLabel)
        );

        timeTimeline.setCycleCount(Timeline.INDEFINITE);
        pulseTimeline.setCycleCount(Timeline.INDEFINITE);

        timeTimeline.play();
        pulseTimeline.play();
    }

    private void updateResultLabel(Image image) {
        if (caseImages.contains(image)) {
            int index = caseImages.indexOf(image);
            resultLabel.setText(imageLabels.get(index));
        }
    }

    private void animateImage(Image image) {
        caseImageView.setImage(image);
        caseImageView.setOpacity(0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), new KeyValue(caseImageView.opacityProperty(), 1))
        );

        timeline.setOnFinished(event -> updateResultLabel(image));

        timeline.play();
    }

    private void updateTimeLabel(ActionEvent event) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("Время HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        timeLabel.setText(formattedTime);
    }

    private void updatePulseLabel(ActionEvent event) {
        int randomChange = (int) (Math.random() * 10 - 5);
        pulseValue += randomChange;
        pulseValue = Math.max(60, Math.min(100, pulseValue));
        pulseLabel.setText("Пульс: " + pulseValue);
    }

    public void openCase(ActionEvent event) {
        Image selectedImage = getRandomImage();
        animateImage(selectedImage);
    }

    private Image getRandomImage() {
        Random random = new Random();
        int index = random.nextInt(caseImages.size());
        return caseImages.get(index);
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
}
