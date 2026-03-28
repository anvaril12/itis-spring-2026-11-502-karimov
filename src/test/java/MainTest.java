import org.example.Main;
import org.example.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void testSolve1() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Тимур Ведищев", 1, 3.0, Arrays.asList("Java",  "Algorithms")));
        students.add(new Student("Паша Кропотов", 4, 5.0, Arrays.asList("Maths", "Algorithms")));
        students.add(new Student("Илья Тереньтьев", 3, 4.7, Arrays.asList("Diskra", "Fizra")));
        students.add(new Student("Федор Гусев", 1, 5.0, Arrays.asList("IIP", "Fizra")));

        List<Student> result = Main.solve1(students);

        assertEquals(2, result.size());
        assertEquals("Илья Тереньтьев", result.get(0));

    }
}
