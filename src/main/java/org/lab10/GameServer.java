package org.lab10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    GameServer(){
        startServer();
    }
    private void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(6868);
            System.out.println("Server started");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ClientThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
