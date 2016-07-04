package com.poorgroupproject.thrumania.network;

import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Cell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Client {
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private ObjectInputStream objectInputStream;

    private InetAddress address;
    private int port;

    private static Client clientClass = new Client();

    private String name;

    private Socket socket;

    public void setUpConnection(byte []address, int port){
        try {
            this.address = InetAddress.getByAddress(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new Socket(this.address,port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Client(){

    }

    public void sendClientName(String name){
        this.name = name;
        try {
            dataOutputStream.writeUTF(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getMapFromServer(){
        try {
            int rows = dataInputStream.readInt();
            int cols = dataInputStream.readInt();
            Land.getInstance().newMap(rows,cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Land.getInstance().setCellAt(i,j, ((Cell) objectInputStream.readObject()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Client getInstance(){
        return clientClass;
    }
}
