package Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends Connection {
    private Scanner scn = new Scanner(System.in);
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private List<ConnectionHandler> connectionHandlerList;

    public Server() {
        connectionHandlerList = new ArrayList<>();
    }

    public void startServer(){
        System.out.println("Introduce puerto -> [def. 1234]");
        port = scn.nextInt();scn.nextLine();
        port = port <= 0? 1234: port;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor abierto");

            connections();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Su propio hilo para las conexiones
    public void connections(){
        try {
            for (;;) { //limitar el for para un numero maximo de conexiones
                clientSocket = serverSocket.accept();
                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
                connectionHandlerList.add(connectionHandler);
                connectionHandler.start();
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
