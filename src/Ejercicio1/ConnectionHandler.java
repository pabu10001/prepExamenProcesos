package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler extends Thread{
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
        for(;;){
            getData();
        }
    }
    public void dataIO(){
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData(){
        String[] data;
        try {
            do {
                data = dataInputStream.readUTF().split("~");
                dataToSend(calcArea(data[0], data[1]));
            } while (!data[1].equals(EXIT));
        } catch (IOException e) {
            closeConnection();
        }
    }

    public String calcArea(String base, String altura){
        try{
            return ((Double.parseDouble(base) * Double.parseDouble(altura)) / 2) + "";
        }catch (Exception e){
            return "Datos mal introducidos";
        }
    }

    public void dataToSend(String s){
        Thread thread = new Thread(() -> {
            try {
                dataOutputStream.writeUTF(s);
            }catch (Exception e){
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
