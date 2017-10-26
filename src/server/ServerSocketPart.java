package server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by damaz on 22.10.2017.
 */
public class ServerSocketPart extends Thread {
    ControllerServer controller;

    public static final int PORT = 8080;

    public ServerSocketPart(ControllerServer controller) throws IOException{
        this.controller = controller;
        this.setDaemon(true);
    }

    @Override
    public void run(){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
//            controller.consoleServerOutput("Started: " + serverSocket);
            try {
                Socket socket = serverSocket.accept();
//                try {
//                    controller.consoleServerOutput("Connection accepted: " + socket);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    String str;
                    while (true) {
                        str = in.readLine();
//                        if (str.equals("END")) break;
                        controller.consoleServerOutput("Echoing: " + str);
                        out.println(str);
                        if(socket.getInputStream().read() == -1){
                            socket.close();
                            break;
                        }
                    }
//                } finally {
//                    controller.consoleServerOutput("Closing...");
//                    socket.close();
//                }
            } finally {
                serverSocket.close();
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
