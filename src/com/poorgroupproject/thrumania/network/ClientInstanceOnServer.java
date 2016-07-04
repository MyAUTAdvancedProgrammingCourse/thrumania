package com.poorgroupproject.thrumania.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class ClientInstanceOnServer {
    private Socket socket;
    private DataInputStream dataInputStream;

    public ClientInstanceOnServer(Socket clientSocket){
        this.socket = clientSocket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
