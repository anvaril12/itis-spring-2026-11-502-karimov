import org.example.LogFilter;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LogfilterTest {

    @Test
    void testFilterReturnsWarnAndErrorAndSevere() {
        List<String> logs = List.of(
                "INFO Application started",
                "WARN Slow query",
                "ERROR Connection failed",
                "DEBUG Query executed",
                "SEVERE System crash"
        );
        List<String> result = LogFilter.filter(logs);

        assertEquals(3, result.size());
        assertTrue(result.get(0).contains("WARN"));
        assertTrue(result.get(1).contains("ERROR"));
        assertTrue(result.get(2).contains("SEVERE"));
    }

    @Test
    void testFilterReturnsEmptyForNoMatches() {
        List<String> logs = List.of(
                "INFO Application started",
                "DEBUG Debugging"
        );

        List<String> result = LogFilter.filter(logs);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterHandlesEmptyList() {
        List<String> result = LogFilter.filter(List.of());rm -rf .git
        assertTrue(result.isEmpty());
    }
}