package Ejercicio2;

import java.util.Scanner;

public class Main2 {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Que quieres ser? c/s");

        switch (scn.nextLine()){
            case "c":
                Client client = new Client();
                client.startClient();
                break;
            case "s":
                Server servidor = new Server();
                servidor.startServer();
                break;
            default:
                System.out.println("Entrada no valida, Saliendo... ");
        }

    }
}
