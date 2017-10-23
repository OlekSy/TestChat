package server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by damaz on 22.10.2017.
 */
public class ServerSocketPart {

    public static final int PORT = 8080;

    public ServerSocketPart(ControllerServer controller) throws IOException{
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Started: " + serverSocket);
        controller.consoleServerOutput("Started: " + serverSocket);
        try {
            Socket socket = serverSocket.accept();
            try {
                //serverOutput.appendText("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true) {
                    String str = in.readLine();
                    if (str.equals("END")) break;
                    //serverOutput.appendText("Echoing: " + str);
                    out.println(str);
                }
            } finally {
                //serverOutput.appendText("Closing...");
                socket.close();
            }
        } finally {
            serverSocket.close();
        }
    }
}
