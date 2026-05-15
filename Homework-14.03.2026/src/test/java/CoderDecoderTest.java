import org.example.Coder;
import org.example.Decoder;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoderDecoderTest {

    @Test
    void testEmptyFolder() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();

        Coder coder = new Coder();
        coder.code(testDir.getAbsolutePath());

        File structFile = new File(testDir, "structure.json");
        assertTrue(structFile.exists());

        Decoder decoder = new Decoder();
        decoder.decode(structFile.getAbsolutePath());

        assertTrue(testDir.exists());

        deleteRecursively(testDir);
    }

    @Test
    void testSingleFile() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();

        File file = new File(testDir, "hello.txt");
        Files.writeString(file.toPath(), "Hello World");

        Coder coder = new Coder();
        coder.code(testDir.getAbsolutePath());

        Decoder decoder = new Decoder();
        decoder.decode(new File(testDir, "structure.json").getAbsolutePath());

        File restored = new File(testDir, "hello.txt");
        assertTrue(restored.exists());
        assertEquals("Hello World", Files.readString(restored.toPath()));

        deleteRecursively(testDir);
    }

    @Test
    void testFolderInsideFolder() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();

        File subDir = new File(testDir, "subfolder");
        subDir.mkdir();
        File file = new File(subDir, "data.txt");
        Files.writeString(file.toPath(), "content");

        Coder coder = new Coder();
        coder.code(testDir.getAbsolutePath());

        Decoder decoder = new Decoder();
        decoder.decode(new File(testDir, "structure.json").getAbsolutePath());

        assertTrue(new File(testDir, "subfolder").exists());
        assertTrue(new File(testDir, "subfolder/data.txt").exists());

        deleteRecursively(testDir);
    }

    @Test
    void testMultipleFiles() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();

        Files.writeString(new File(testDir, "1.txt").toPath(), "один");
        Files.writeString(new File(testDir, "2.txt").toPath(), "два");
        Files.writeString(new File(testDir, "3.txt").toPath(), "три");

        Coder coder = new Coder();
        coder.code(testDir.getAbsolutePath());

        Decoder decoder = new Decoder();
        decoder.decode(new File(testDir, "structure.json").getAbsolutePath());

        assertTrue(new File(testDir, "1.txt").exists());
        assertTrue(new File(testDir, "2.txt").exists());
        assertTrue(new File(testDir, "3.txt").exists());

        deleteRecursively(testDir);
    }

    @Test
    void testFileWithLines() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();

        String content = "строка 1\nстрока 2\nстрока 3";
        Files.writeString(new File(testDir, "lines.txt").toPath(), content);

        Coder coder = new Coder();
        coder.code(testDir.getAbsolutePath());

        Decoder decoder = new Decoder();
        decoder.decode(new File(testDir, "structure.json").getAbsolutePath());

        String restored = Files.readString(new File(testDir, "lines.txt").toPath());
        assertEquals(content, restored);

        deleteRecursively(testDir);
    }

    @Test
    void testCoderWithNonExistentPath() {
        Coder coder = new Coder();
        assertDoesNotThrow(() -> coder.code("C:/несуществующая_папка"));
    }

    @Test
    void testDecoderWithNonExistentFile() {
        Decoder decoder = new Decoder();
        assertDoesNotThrow(() -> decoder.decode("несуществующий.json"));
    }

    @Test
    void testDecoderWithCorruptedFile() throws IOException {
        File testDir = Files.createTempDirectory("test").toFile();
        File badFile = new File(testDir, "structure.json");
        Files.writeString(badFile.toPath(), "мусор");

        Decoder decoder = new Decoder();
        assertDoesNotThrow(() -> decoder.decode(badFile.getAbsolutePath()));
        deleteRecursively(testDir);
    }


    private void deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }
}