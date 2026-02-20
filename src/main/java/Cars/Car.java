package Cars;

public class Car implements Comparable<Car> {
    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public int getYear() { return year; }
    public String getModel() { return model; }

    @Override
    public int compareTo(Car car) {
        if(year == car.getYear()) {
            return 0;
        }
        if(year < car.getYear()) {
            return -1;
        } else {
            return 1;
        }
    }
}
