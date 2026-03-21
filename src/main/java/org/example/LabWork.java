package org.example;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class LabWork {

    public static void main(String[] args) {
        try {
            // Получаем список файлов
            String json = getContent("http://185.221.160.131:81/files");

            // Парсим JSON
            List<FileInfo> files = new ArrayList<>();
            int searchPos = 0;

            while (true) {
                int idPos = json.indexOf("\"id\"", searchPos);
                if (idPos == -1) break;

                int colonPos = json.indexOf(":", idPos);
                int quoteStart = json.indexOf("\"", colonPos);
                int quoteEnd = json.indexOf("\"", quoteStart + 1);
                String id = json.substring(quoteStart + 1, quoteEnd);

                int fileNamePos = json.indexOf("\"fileName\"", quoteEnd);
                if (fileNamePos == -1) break;

                int fileNameColon = json.indexOf(":", fileNamePos);
                int fileNameQuoteStart = json.indexOf("\"", fileNameColon);
                int fileNameQuoteEnd = json.indexOf("\"", fileNameQuoteStart + 1);
                String fileName = json.substring(fileNameQuoteStart + 1, fileNameQuoteEnd);

                int filePathPos = json.indexOf("\"filePath\"", fileNameQuoteEnd);
                if (filePathPos == -1) break;

                int filePathColon = json.indexOf(":", filePathPos);
                int filePathQuoteStart = json.indexOf("\"", filePathColon);
                int filePathQuoteEnd = json.indexOf("\"", filePathQuoteStart + 1);
                String filePath = json.substring(filePathQuoteStart + 1, filePathQuoteEnd);
                filePath = filePath.replace("\\", "/");
                if (filePath.startsWith("/")) {
                    filePath = filePath.substring(1);
                }

                FileInfo info = new FileInfo();
                info.id = id;
                info.fileName = fileName;
                info.filePath = filePath;
                files.add(info);

                searchPos = filePathQuoteEnd;
            }

            System.out.println("Найдено файлов: " + files.size());

            // Создаем папку для проекта
            String projectDir = "MyProject";
            Files.createDirectories(Paths.get(projectDir));

            // Скачиваем файлы
            int success = 0;
            for (FileInfo file : files) {
                try {
                    System.out.print(file.fileName + " ... ");

                    String content = getContent("http://185.221.160.131:81/files/" + file.id);
                    content = content.replace("<br/>", "\n");

                    String fullPath;
                    if (file.filePath != null && !file.filePath.isEmpty()) {
                        fullPath = projectDir + "/" + file.filePath + "/" + file.fileName;
                    } else {
                        fullPath = projectDir + "/" + file.fileName;
                    }

                    fullPath = fullPath.replace("//", "/");
                    Path path = Paths.get(fullPath);

                    if (path.getParent() != null) {
                        Files.createDirectories(path.getParent());
                    }

                    Files.write(path, content.getBytes());
                    System.out.println("OK");
                    success++;

                } catch (Exception e) {
                    System.out.println("ОШИБКА");
                }
            }

            System.out.println("\nСкачано: " + success + " из " + files.size());
            System.out.println("Папка: " + projectDir);

            // Вводим ФИО
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ФИО: ");
            String fio = scanner.nextLine();
            System.out.println("Привет, " + fio);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String getContent(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        conn.disconnect();

        return result.toString();
    }

    static class FileInfo {
        String id = "";
        String fileName = "";
        String filePath = "";
    }
}