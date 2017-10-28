package server;

import client.ControllerClient;

import java.io.*;
import java.net.Socket;

/**
 * Created by damaz on 28.10.2017.
 */
public class MonoThreadClientHandler extends Thread{
    Socket client;
    String input;
    ControllerServer controller;
    BufferedReader in;
    PrintWriter out;
    ServerSocketPart server;

    public MonoThreadClientHandler(Socket socket, ControllerServer controller, ServerSocketPart server){
        client = socket;
        this.controller = controller;
        this.server = server;
        this.setDaemon(true);
    }

    @Override
    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            while(true){
                input = in.readLine();
                controller.consoleServerOutput("Message: " + input);
                server.send(input);
            }
        } catch (IOException e){}
    }

    public PrintWriter getOut(){
        return out;
    }
}
