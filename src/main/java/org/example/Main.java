package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://185.221.160.131/api/v1/transactions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = "";
        while (br.ready()) {
            result += br.readLine();
        }
        br.close();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> list = objectMapper.readValue(result, new TypeReference<>() {});

        System.out.println(solve1(list));
        System.out.println(solve2(list));
        System.out.println(solve3(list));
        solve4(list);
        solve5(list);
        solve6(list);
        solve7(list);
        solve8(list);
        solve9(list);
        solve10(list);
        solve11(list);
        solve12(list);
        solve13(list);
        solve14(list);
        solve15(list);
    }

    public static Double solve1(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0.0);
    }

    public static Double solve2(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isSuccess)
                .mapToDouble(Transaction::getAmount)
                .min()
                .orElse(0.0);
    }

    public static Double solve3(List<Transaction> transactions) {
        return (double) transactions.stream()
                .filter(Transaction::isSuccess).
                count() / transactions.size() * 100;
    }

    public static void solve4(List<Transaction> transactions) {
        Set<Integer> result = transactions.stream()
                .map(Transaction::getUserIdFrom)
                .collect(Collectors.toSet());
        System.out.println(result);
    }

    public static void solve5(List<Transaction> transactions) {
        transactions.stream()
                .map(Transaction::getDenyReason)
                .filter(reason -> reason != null && !reason.isEmpty())
                .distinct()
                .forEach(System.out::println);
    }

    public static void solve6(List<Transaction> transactions) {
        Map<Integer, Double> result = transactions.stream()
                .filter(Transaction::isSuccess)
                .collect(Collectors.groupingBy(
                        Transaction::getUserIdFrom,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
        System.out.println(result);
    }

    public static void solve7(List<Transaction> transactions) {
        Map<Integer, Double> result = transactions.stream()
                .filter(Transaction::isSuccess)
                .collect(Collectors.groupingBy(
                        Transaction::getUserIdTo,
                        Collectors.averagingDouble(Transaction::getAmount)
                ));
        System.out.println(result);
    }

    public static void solve8(List<Transaction> transactions) {
        transactions.stream()
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .limit(5)
                .forEach(System.out::println);
    }

    public static void solve9(List<Transaction> transactions) {
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getUserIdFrom))
                .entrySet().stream()
                .filter(e -> e.getValue().stream().allMatch(Transaction::isSuccess))
                .map(Map.Entry::getKey)
                .forEach(id -> System.out.println(id));
    }

    public static void solve10(List<Transaction> transactions) {
        Map<Boolean, List<Transaction>> result = transactions.stream()
                .collect(Collectors.partitioningBy(Transaction::isSuccess));
        System.out.println(result);
    }

    public static void solve11(List<Transaction> transactions) {
        Map<Integer, Map<Integer, Double>> result = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getUserIdFrom,
                        Collectors.groupingBy(
                                Transaction::getUserIdTo,
                                Collectors.summingDouble(Transaction::getAmount)
                        )
                ));
        System.out.println(result);
    }

    public static void solve12(List<Transaction> transactions) {
        transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getUserIdFrom() + " " + t.getUserIdTo(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println(e.getKey() + e.getValue()));
    }

    public static void solve13(List<Transaction> transactions) {
        double result = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .average()
                .orElse(0.0);
        transactions.stream()
                .filter(t -> t.getAmount() > result)
                .forEach(System.out::println);
    }

    public static void solve14(List<Transaction> transactions) {
        transactions.stream()
                .map(Transaction::getDenyReason)
                .filter(reason -> reason != null && !reason.isEmpty())
                .collect(Collectors.groupingBy(
                        reason -> reason,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    public static void solve15(List<Transaction> transactions) {
        Map<Integer, Double> sums = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getUserIdTo,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        Map.Entry<Integer, Double> max = sums.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();

        System.out.println(max.getKey() + " " + max.getValue());
    }
}