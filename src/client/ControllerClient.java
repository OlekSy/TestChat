package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ControllerClient {
    @FXML TextArea clientOutput;
    @FXML TextField clientInput;
    @FXML Button btnSend;

     BufferedReader in;
    PrintWriter out;

    public void initialize(){
        clientOutput.setEditable(false);
    }

    public void send(){
        out.println(clientInput.getText());
    }

    public void receive(String message){
        clientOutput.appendText("\n" + message);
    }

    public void setIn(BufferedReader in){
        this.in = in;
    }

    public void setOut(PrintWriter out){
        this.out = out;
    }
}
