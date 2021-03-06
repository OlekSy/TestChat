package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainServer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleServer.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.setResizable(false);
        primaryStage.show();

        ServerSocketPart serverSocket = new ServerSocketPart(loader.getController());
        serverSocket.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
