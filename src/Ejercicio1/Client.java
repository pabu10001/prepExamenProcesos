package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Connection {
    private static Scanner scn = new Scanner(System.in);
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private Socket clientSocket;

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
            sendData();
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);
        }
    }

    public void sendData() {
        String base, altura;
        System.out.println("Introduce base:");
        base = scn.nextLine();

        System.out.println("Introduce altura:");
        altura   = scn.nextLine();

        try {
            dataOutputStream.writeUTF(base + "~" + altura);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        Thread thread = new Thread(() -> {
            String data;
            try {
                do {
                    data = dataInputStream.readUTF();
                    System.out.println("Resultado: " + data);
                    closeConnection();
                } while (true);
            } catch (IOException e) {
            }
        });
        thread.start();
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
