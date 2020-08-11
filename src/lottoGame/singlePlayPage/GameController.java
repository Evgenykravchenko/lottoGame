package lottoGame.singlePlayPage;

import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.LottoStage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements LottoStage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane gameBackground;

    @FXML
    private AnchorPane lottoCard;

    @FXML
    private AnchorPane cardImg;

    @FXML
    void initialize() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void openSceneAnimation() {

    }

    @Override
    public void openOtherStage(String fxmlFileName, Event event) {

    }
}
