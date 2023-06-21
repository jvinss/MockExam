package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

/**
 * Hello world!
 *
 */
public class App 
{
    static int portNumber = 1234;
    static ServerSocket serverSocket;

    static boolean startServer() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void main( String[] args )
    {
        BuildList lista = new BuildList();
        lista.buildList();

        if (!startServer()) {
            System.out.println("Server non inizializzato.");
            return;
        } else {
            out.println();
        }

        while (true){
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.println("Accepted");

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            Thread t = new Thread(clientHandler);
            t.start();
        }
    }
}