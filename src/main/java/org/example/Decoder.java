package org.example;

import java.io.*;
import java.nio.file.*;

public class Decoder {

    public void decode(String jsonPath) {
        File structFile = new File(jsonPath);
        File targetDir = structFile.getParentFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(structFile))) {
            String line;
            String currentFile = null;
            StringBuilder content = new StringBuilder();
            boolean readingContent = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("folder:")) {
                    String path = line.substring(7);
                    Files.createDirectories(Paths.get(targetDir.getAbsolutePath(), path));
                }
                else if (line.startsWith("file:")) {
                    if (currentFile != null) {
                        saveFile(currentFile, content.toString(), targetDir);
                    }
                    currentFile = line.substring(5);
                    content.setLength(0);
                    readingContent = false;
                }
                else if (line.equals("content:")) {
                    readingContent = true;
                }
                else if (line.equals("end")) {
                    if (currentFile != null) {
                        saveFile(currentFile, content.toString(), targetDir);
                        currentFile = null;
                    }
                    readingContent = false;
                }
                else if (readingContent) {
                    if (content.length() > 0) content.append("\n");
                    content.append(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void saveFile(String path, String content, File targetDir) throws IOException {
        Path filePath = Paths.get(targetDir.getAbsolutePath(), path);
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, content);
    }
}