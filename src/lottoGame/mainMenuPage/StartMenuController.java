package lottoGame.mainMenuPage;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

import animatefx.animation.*;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import util.LottoStage;
import util.Audio;

public class StartMenuController implements LottoStage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane menuBackground;

    @FXML
    private AnchorPane top;

    @FXML
    private Line topLine;

    @FXML
    private Label topLottoLbl;

    @FXML
    private Label menuLbl;

    @FXML
    private Button singleGameBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;


    private Audio audio = new Audio(0.8, "src/resources/audio/track.wav");


    @FXML
    void initialize() {

        onCreate();
        onPlay();
        settingsOpen();
        onClose();

    }

    private void settingsOpen() {
        settingsBtn.setOnMouseClicked(event -> {
            //audio.stop();
            openOtherStage("../settingsPage/settings.fxml", event);
        });
    }

    private void onPlay() {
        singleGameBtn.setOnMouseClicked(event -> {
            openOtherStage("../singlePlayPage/game.fxml", event);
        });
    }

    @Override
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

    @Override
    public void onCreate() {
        openSceneAnimation();
    }

    @Override
    public void onClose() {
        exitBtn.setOnMouseClicked(event -> {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        });
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

        new FadeIn(menuLbl)
                .setSpeed(0.3)
                .play();

        new BounceIn(singleGameBtn)
                .setSpeed(0.4)
                .play();


        new BounceIn(settingsBtn)
                .setSpeed(0.4)
                .play();

        new BounceIn(exitBtn)
                .setSpeed(0.4)
                .play();
    }
}

