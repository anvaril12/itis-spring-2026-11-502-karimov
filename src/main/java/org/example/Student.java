package org.example;

import java.util.List;

public class Student {
    private String fullName;
    private int yearOfStudy;
    private double averageScore;
    private List<String> passedCourses;

    public Student(String fullName, int yearOfStudy, double averageScore, List<String> passedCourses) {
        this.averageScore = averageScore;
        this.fullName = fullName;
        this.yearOfStudy = yearOfStudy;
        this.passedCourses = passedCourses;
    }

    public Student() {

    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getFullName() {
        return fullName;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public List<String> getPassedCourses() {
        return passedCourses;
    }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public void setAverageScore(double averageScore) { this.averageScore = averageScore; }
    public void setPassedCourses(List<String> passedCourses) { this.passedCourses = passedCourses; }
}
