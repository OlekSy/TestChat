package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleClient.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        ClientSocketPart clientSocket = new ClientSocketPart(loader.getController());
        clientSocket.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
