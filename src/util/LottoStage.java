package util;

import javafx.event.Event;

public interface LottoStage {

    void onCreate();
    void onClose();
    void openSceneAnimation();
    void openOtherStage(String fxmlFileName, Event event);
}
