package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Тимур Ведищев", 1, 3.0, Arrays.asList("Java",  "Algorithms")));
        students.add(new Student("Паша Кропотов", 4, 5.0, Arrays.asList("Maths", "Algorithms")));
        students.add(new Student("Илья Тереньтьев", 3, 4.7, Arrays.asList("Diskra", "Fizra")));
        students.add(new Student("Федор Гусев", 1, 5.0, Arrays.asList("IIP", "Fizra")));


        solve1(students);
        System.out.println(solve2(students));;
        System.out.println(solve3(students));;
        solve4(students);
        System.out.println(solve5(students));;

    }

    public static void solve1(List<Student> students) {
        students.stream()
                .filter(s -> s.getYearOfStudy() == 2 || s.getYearOfStudy() == 3)
                .filter(s -> s.getAverageScore() > 4.0)
                .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                .map(Student::getFullName)
                .distinct()
                .forEach(System.out::println);

    }

    public static Map<Integer, List<Student>> solve2(List<Student> students) {
        return students.stream()
                .filter(s -> s.getPassedCourses().contains("Algorithms"))
                .distinct()
                .collect(Collectors.groupingBy(Student::getYearOfStudy));
    }

    public static long solve3(List<Student> students) {
        return students.stream()
                .filter(s -> s.getYearOfStudy() > 1)
                .filter(s -> s.getPassedCourses() != null)
                .flatMap(s -> s.getPassedCourses().stream())
                .filter(s -> s != null && !s.isEmpty())
                .distinct()
                .count();
    }

    public static void solve4(List<Student> students) {
        OptionalDouble min = students.stream()
                .filter(s -> s.getYearOfStudy() == 4 && s.getFullName().charAt(0) == 'А')
                .mapToDouble(Student::getAverageScore)
                .min();

        OptionalDouble max = students.stream()
                .filter(s -> s.getYearOfStudy() == 4 && s.getFullName().charAt(0) == 'А')
                .mapToDouble(Student::getAverageScore)
                .max();

        System.out.println("Максимум:" + max);
        System.out.println("Минимум" + min);
    }

    public static Map<String, Long> solve5(List<Student> students) {
        return students.stream()
                .filter(s -> s.getAverageScore() >= 3.5 && s.getPassedCourses() != null)
                .flatMap(s -> s.getPassedCourses().stream())
                .filter(c -> c != null && !c.isEmpty())
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
    }

}