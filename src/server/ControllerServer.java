package server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ControllerServer {
    @FXML TextArea serverOutput;

    public void initialize(){
        serverOutput.setEditable(false);
        serverOutput.setText("System information:");
    }

    public void consoleServerOutput(String text){
        serverOutput.appendText("\n" + text);
    }
}
