package lottoGame.settingsPage;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInLeft;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import util.LottoStage;
import util.Audio;

public class SettingsController implements LottoStage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane top;

    @FXML
    private Line topLine;

    @FXML
    private Label topLottoLbl;

    @FXML
    private Button backBtn;

    @FXML
    private Label settingsLbl;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label volumeLbl;

    @FXML
    private Label onOffMusicLbl;

    @FXML
    private Button lowVolumeBtn;

    @FXML
    private Button highVolumeBtn;

    @FXML
    private ImageView onOffMusicBtn;

    private final Audio audio = new Audio(0.8, "src/resources/audio/track.wav");
    private static boolean isMusicPlay = true;

    @FXML
    void initialize() {
        volumeSlider.setValue(audio.getWt());
        System.out.println(audio.getWt());

        onCreate();
        volumeController();
        audioOnOff();
        onBack();
    }

    public void onCreate() {
        openSceneAnimation();
    }

    @Override
    public void onClose() {

    }

    @Override
    public void openSceneAnimation() {
        new SlideInLeft(top)
                .setSpeed(0.5)
                .play();

        new SlideInLeft(topLine)
                .setSpeed(0.4)
                .play();

        new FadeIn(topLottoLbl)
                .setSpeed(0.2)
                .play();

        new FadeIn(backBtn)
                .setSpeed(0.2)
                .play();
    }

    private void onBack() {
        backBtn.setOnAction(event -> {
            openOtherStage("../mainMenuPage/startMenu.fxml", event);
        });
    }

    public void openOtherStage(String fxmlFileName, Event event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene gameScene = new Scene(parent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException exception) {
            System.out.println("Can't open " + fxmlFileName);
        }
    }

    private void audioOnOff() {
        onOffMusicBtn.setOnMouseClicked(event -> {
            if (isMusicPlay) {
                onOffMusicBtn.setImage(new Image(getClass().getResourceAsStream("../../resources/images/musicOff.png")));
                audio.stop();
            } else {
                onOffMusicBtn.setImage(new Image(getClass().getResourceAsStream("../../resources/images/musicOn.png")));
                audio.play();
                audio.setVolume();
                audio.repeat();
            }
            isMusicPlay = !isMusicPlay;
        });
    }

    private void volumeController() {

        lowVolumeController();
        highVolumeController();

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.doubleValue());
            audio.setWt(newValue.doubleValue());
            audio.setVolume();
        });
    }

    private void lowVolumeController() {
        lowVolumeBtn.setOnAction(event -> {
            double wt = volumeSlider.getValue() - 0.1;

            if (!isSliderValueInRange(wt)) {
                wt = getMaxOrMinValueFromCurrent(wt);
            }

            volumeSlider.setValue(wt);
            audio.setWt(wt);
            audio.setVolume();
        });
    }

    private void highVolumeController() {
        highVolumeBtn.setOnAction(event -> {
            double wt = volumeSlider.getValue() + 0.1;

            if (!isSliderValueInRange(wt)) {
                wt = getMaxOrMinValueFromCurrent(wt);
            }

            volumeSlider.setValue(wt);
            audio.setWt(wt);
            audio.setVolume();
        });
    }

    private boolean isSliderValueInRange(double value) {
        return (value >= 0 && value <= 1);
    }

    private double getMaxOrMinValueFromCurrent(double value) {

        double wt = 0;
        if (value < 0) wt = 0;
        if (value > 1) wt = 1;

        return wt;
    }
}

