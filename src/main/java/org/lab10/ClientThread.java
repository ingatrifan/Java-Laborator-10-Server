package org.lab10;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    Socket socket;
    ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client accepted, waiting commands...");
        while (true){
            try {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                String response = "Server received the request ...";
                if ( line.equals("stop")) response = "Server stopped";
                System.out.println(line);
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(response);
                if ( line.equals("stop"))break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
