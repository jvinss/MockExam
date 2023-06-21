package org.example;

import java.util.ArrayList;

public class BuildList {
    static ArrayList<Car> cars = new ArrayList<>();

    static void buildList() {
        cars.add(new Car(123,"bmw",3594.9, 2));
        cars.add(new Car(3634,"audi",38346.9, 1));
        cars.add(new Car(135,"ferrari",130000.4, 10));
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }
}
