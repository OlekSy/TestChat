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
    }

    @Override
    public void run(){
        InetAddress address = null;
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        controllerClient.receive("address = " + address);
        Socket socket;
        try {
            socket = new Socket(address, 8080);
//            try {
                controllerClient.receive("socket = " + socket);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                controllerClient.setIn(in);
                controllerClient.setOut(out);
//                for(int i = 0; i < 10; i++){
//                    out.println("message " + i);
//                    controllerClient.recieve(in.readLine());
//                }
//            } finally {
//                controllerClient.recieve("Closing...");
//                socket.close();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void send(String message){
//        out.println(message);
//    }
}
