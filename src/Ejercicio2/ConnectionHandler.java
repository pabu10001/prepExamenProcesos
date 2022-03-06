package Ejercicio2;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
        enviarFichero("src/Ejercicio2/Ficheros/txtServer.txt");
    }


    private void enviarFichero(String ruta){
        BufferedReader bufferedReader;
        String texto = "";
        String currentLine;
        try {
            bufferedReader = new BufferedReader(new FileReader(ruta));
            while ((currentLine = bufferedReader.readLine()) != null ){
                currentLine += "\n";
                sendData(currentLine);
                texto += currentLine;
                try {
                    Thread.sleep(100); //necesario para que no pierda mensajes el cliente
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sendData("exit()");
            sendData(getHash(texto));
            System.out.println("Fichero Enviado\n");
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarFichero(String texto){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("src/Ejercicio2/Ficheros/txtServer.txt", false)
            );
            bufferedWriter.write(texto);
            bufferedWriter.close();
            System.out.println("Fichero Guardado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHash(String texto){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] dataBytes = texto.getBytes();
            md.update(dataBytes) ;// TEXTQ A RESUMIR
            byte[] resumen = md.digest(); // SE CALCULA EL RESUMEN
            return Arrays.toString(resumen);
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); return null;}
    }

    private void dataIO() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        Thread thread = new Thread(() -> {
            String texto = "";
            String data;
            try {
                while (!(data = dataInputStream.readUTF()).equals(EXIT)) {
                    texto += data;
                }
                if (getHash(texto).equals(dataInputStream.readUTF())){
                    System.out.println("Son iguales");
                    guardarFichero(texto);
                }else {
                    System.out.println("No son iguales");
                }
            } catch (IOException e) {
                closeConnection();
            }
        });
        thread.start();
    }

    private void sendData(String s) {
        Thread thread = new Thread(() -> {
            try {
                dataOutputStream.writeUTF(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void closeConnection() {
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
