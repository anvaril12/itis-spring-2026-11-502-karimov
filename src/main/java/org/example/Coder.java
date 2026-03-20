package org.example;

import java.io.*;
import java.nio.file.*;

public class Coder {

    public void code(String dirPath) {
        File root = new File(dirPath);
        File output = new File(root, "structure.json");

        try (PrintWriter writer = new PrintWriter(new FileWriter(output))) {
            scan(root, root, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void scan(File current, File root, PrintWriter writer) {
        String path = getRelativePath(current, root);

        if (current.isDirectory()) {
            writer.println("folder:" + path);
            for (File child : current.listFiles()) {
                if (child.getName().equals("structure.json")) continue;
                scan(child, root, writer);
            }
        } else {
            writer.println("file:" + path);
            writer.println("content:");
            try (BufferedReader br = new BufferedReader(new FileReader(current))) {
                String line;
                while ((line = br.readLine()) != null) {
                    writer.println(line);
                }
            } catch (IOException e) {
                writer.println("ошибка");
            }
        }
    }

    private String getRelativePath(File file, File root) {
        String rootPath = root.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        if (filePath.equals(rootPath)) return "";
        return filePath.substring(rootPath.length() + 1);
    }
}