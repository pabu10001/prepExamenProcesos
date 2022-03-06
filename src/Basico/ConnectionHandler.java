package Basico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private final String EXIT = "exit()";

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        dataIO();
        getData();
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
