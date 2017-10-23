package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ControllerClient {
    @FXML TextArea clientOutput;
    @FXML TextField clientInput;
    @FXML Button btnSend;

    BufferedReader in;
    PrintWriter out;

    public void initialize() throws UnknownHostException, IOException{
        clientOutput.setEditable(false);
        InetAddress address = InetAddress.getByName("localhost");
        clientOutput.appendText("address = " + address);
        Socket socket = new Socket(address, 8080);
        try {
            clientOutput.appendText("socket = " + socket);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            for(int i = 0; i < 10; i++){
                out.println("message " + i);
                clientOutput.appendText(in.readLine());
            }
        } finally {
            clientOutput.appendText("Closing...");
            socket.close();
        }
    }

    public void send(){
        out.println(clientInput.getText());
    }

    public void recieve(String message){
        clientOutput.appendText("\n" + message);
    }
}
