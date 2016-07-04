package com.poorgroupproject.thrumania.network;

import com.poorgroupproject.thrumania.land.Land;

import java.io.*;
import java.net.Socket;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class ClientInstanceOnServer {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;

    private String clientName;

    public ClientInstanceOnServer(Socket clientSocket){
        this.socket = clientSocket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clientName = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMap(Land land){
        try {
            int rows = land.getRows();
            int cols = land.getCols();
            dataOutputStream.writeInt(rows);
            dataOutputStream.writeInt(cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    objectOutputStream.writeObject(Land.getInstance().getCellAt(i,j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
