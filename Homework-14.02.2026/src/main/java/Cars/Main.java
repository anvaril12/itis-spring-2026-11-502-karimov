package Cars;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Car> list = new TreeSet<Car>();
        list.add(new Car("Bmw", 2010));
        list.add(new Car("Mercedes", 2010));
        list.add(new Car("Lada", 2000));
        list.add(new Car("Ferrari", 2026));
        list.add(new Car("Skoda", 2015));

        for(Car car : list) {
            System.out.println(car.getYear() + "  " + car.getModel());
        }
    }
}
