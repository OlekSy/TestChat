package server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;

public class ControllerServer {
    @FXML TextArea serverOutput;

    public void initialize() throws IOException{
        serverOutput.setEditable(false);
    }

    public void consoleServerOutput(String text){
        serverOutput.appendText("\n" + text);
    }
}
