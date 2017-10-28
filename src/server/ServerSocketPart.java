package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damaz on 22.10.2017.
 */
public class ServerSocketPart extends Thread {
    ControllerServer controller;
    List<MonoThreadClientHandler> clients;

    public static final int PORT = 8080;

    public ServerSocketPart(ControllerServer controller) throws IOException{
        this.controller = controller;
        this.setDaemon(true);
    }

    @Override
    public void run(){
        MonoThreadClientHandler temp;
        ServerSocket serverSocket;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(PORT);
//            controller.consoleServerOutput("Server socket created.");
            try {
                while (true) {
                    Socket socket = serverSocket.accept();
//                    controller.consoleServerOutput("Client Socket created.");
                    temp = new MonoThreadClientHandler(socket, controller, this);
                    clients.add(temp);
                    temp.start();
//                    controller.consoleServerOutput("Client Thread created.");
                }
            } finally {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message){
        for(MonoThreadClientHandler temp : clients){
            temp.getOut().println(message);
        }
    }
}
