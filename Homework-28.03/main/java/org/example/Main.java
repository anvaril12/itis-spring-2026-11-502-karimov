package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.LogFilter.filter;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> allLogs = Files.readAllLines(Paths.get("example.log"));
            List<String> Logs = filter(allLogs);
            System.out.println(Logs.size());

            for (String log : Logs) {
                System.out.println(log);
            }

        } catch (IOException e) {
            System.err.println("ошибка: " + e.getMessage());
        }
    }
}