package lottoGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import util.Audio;


public class Main extends Application {

    private Audio audio = new Audio(0.8, "src/resources/audio/track.wav");

    @Override
    public void start(Stage primaryStage) throws Exception {
        audio.sound();
        audio.setVolume();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenuPage/startMenu.fxml"));
        primaryStage.setTitle("Lotto game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
