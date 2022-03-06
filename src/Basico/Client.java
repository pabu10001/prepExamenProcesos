package Basico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Connection{
    private static Scanner scn = new Scanner(System.in);
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private Socket clientSocket;
    private final String EXIT = "exit()";

    public Client() {

    }

    public void startClient() {
        System.out.println("Introduce host ->");
        host = scn.nextLine();
        System.out.println("Introduce puerto ->");
        port = scn.nextInt();
        scn.nextLine();
        port = port <= 0 ? 1234 : port;

        try {
            clientSocket = new Socket(host, port);
            System.out.println("Conectado");
            dataIO();
            getData();
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);
        }
    }

    public void dataIO() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        Thread thread = new Thread(() -> {
            String data;
            try {
                while (!(data = dataInputStream.readUTF()).equals(EXIT)) {
                    System.out.println(data);
                    /*
                    Que haces al recibir data?
                     */
                }
            } catch (IOException e) {
                closeConnection();
            }
        });
        thread.start();
    }


    public void sendData(String s) {
        Thread thread = new Thread(() -> {
            try {
                dataOutputStream.writeUTF(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }


    public void closeConnection() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Excepción en cerrarConexion(): " + e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
