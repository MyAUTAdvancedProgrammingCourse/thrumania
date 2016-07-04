package com.poorgroupproject.thrumania.network;

import java.net.Socket;

/**
 *
 */
public class Client {
    private static Client clientClass;

    private Socket socket;

    public void setUpConnection(){

    }
    private Client(){

    }

    public Client getInstance(){
        return clientClass;
    }
}
