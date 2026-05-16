package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LogFilter {

    private static final Pattern LEVEL_PATTERN = Pattern.compile("\\b(WARN|ERROR|SEVERE)\\b");

    public static List<String> filter(List<String> logs) {
        List<String> result = new ArrayList<>();

        for (String line : logs) {
            Matcher matcher = LEVEL_PATTERN.matcher(line);
            if (matcher.find()) {
                result.add(line);
            }
        }

        return result;
    }
}