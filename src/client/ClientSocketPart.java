package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by damaz on 23.10.2017.
 */
public class ClientSocketPart extends Thread{
    ControllerClient controllerClient;
    BufferedReader in;
    PrintWriter out;

    public ClientSocketPart(ControllerClient controllerClient){
        this.controllerClient = controllerClient;
        setDaemon(true);
    }

    @Override
    public void run(){
        InetAddress address = null;
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Socket socket;
        try {
            socket = new Socket(address, 8080);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            controllerClient.setIn(in);
            controllerClient.setOut(out);
            try {
                while (true) {
                    controllerClient.receive(in.readLine());
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
