package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;

import static org.example.BuildList.cars;

public class ClientHandler implements Runnable{
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;

        InetAddress inetAddress = this.clientSocket.getInetAddress();
        System.out.println("Connesso da: " + inetAddress);
    }

    boolean manage() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            return false;
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            return false;
        }

        String s = "";
        while (true) {
            try {
                if  ((s = in.readLine()) == null) break;
            } catch (IOException e) {
                return false;
            }
            System.out.println("Input: " + s);
            out.println("Comando inserito: " + s);
            execute(s,out);
        }
        return true;
    }

    void execute(String s, PrintWriter out) {
        Gson g = new Gson();
        Car moreExpensiveCar = null;
        String carJson = "";
        switch (s) {
            case "more_expensive":
                double max = Double.MIN_VALUE;
                for (Car car : cars) {
                    if (car.getPrice() > max) {
                        moreExpensiveCar = car;
                    }
                }
                carJson = g.toJson(moreExpensiveCar);
                out.println(carJson);
                break;
            case "all":
                for (Car car : cars) {
                    carJson = g.toJson(car);
                    out.println(carJson);
                }
                break;
            case "sorted":
                Comparator<Car> comparator = new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        return o1.getBrand().compareTo(o2.getBrand());
                    }
                };

                Collections.sort(cars,comparator);
                for (Car car : cars) {
                    carJson = g.toJson(car);
                    out.println(carJson);
                }
                break;
            default:
                out.println("Comando non riconosciuto.");
                break;
        }
    }

    @Override
    public void run() { manage(); }
}
