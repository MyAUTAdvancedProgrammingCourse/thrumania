package com.poorgroupproject.thrumania.network;

import com.poorgroupproject.thrumania.land.Land;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Server {
    private static Server serverClass;
    private ClientInstanceOnServer[]cliets;
    private int clientsNum;
    private ServerSocket serverSocket;

    private final int MAX_CLIENT_NUM = 4;
    public static String[] getLocalIPAddress(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        StringTokenizer tokenizer = new StringTokenizer(address.getHostAddress(),".");
        String []result = new String[4];
        for (int i = 0; i < 4; i++) {
            result[i] = tokenizer.nextToken();
        }
        return result;
    }

    public void setUpServer(int port){
        serverClass = new Server(port);
    }

    public Server getInstance(){
        return serverClass;
    }

    private Server(int port){
        cliets = new ClientInstanceOnServer[MAX_CLIENT_NUM];
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitClientsToConnect(int waitsForClients){
        for (int i = 0; i < waitsForClients; i++) {
            try {
                Socket clientSocket = serverSocket.accept();
                cliets[clientsNum++] = new ClientInstanceOnServer(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMapToClients(){
        for (int i = 0; i < clientsNum; i++) {
            cliets[i].sendMap(Land.getInstance());
        }
    }
}
