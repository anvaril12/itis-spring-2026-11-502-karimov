package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface CarInfo {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface FieldDesc {
    String value();
}

@CarInfo("Немецкий автомобиль")
public class BmwCar extends Car implements Movable{

    @FieldDesc("Цвет автомобиля")
    private String color;

    @FieldDesc("Год производства")
    private int yearOfRelease;

    @FieldDesc("Тип кузова")
    private String bodyType;

    public BmwCar(String color, int yearOfRelease, String bodyType) {
        this.color = color;
        this.yearOfRelease = yearOfRelease;
        this.bodyType = bodyType;
    }

    public BmwCar(int yearOfRelease, String color) throws IllegalArgumentException{
        if(yearOfRelease < 1900) {
            throw new IllegalArgumentException("Не бывает такого года выпуска");
        }
        this.yearOfRelease = yearOfRelease;
        this.color = color;
    }

    public BmwCar(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public String getBodyType() {
        return bodyType;
    }

    @Override
    public void move() {
        System.out.println("Машина едет");
    }

    @Override
    public void stop() {
        System.out.println("Машина стоит");
    }
}
