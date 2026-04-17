import org.example.ParallelSum;
import org.example.SingleThreadSum;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SumCalculatorTest {

    private final SingleThreadSum single = new SingleThreadSum();
    private final ParallelSum parallel = new ParallelSum(Runtime.getRuntime().availableProcessors());

    @Test
    void testEmptyArray() {
        int[] array = {};
        assertEquals(0, single.sum(array));
        assertEquals(0, parallel.sum(array));
    }

    @Test
    void testSimpleArray() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(15, single.sum(array));
        assertEquals(15, parallel.sum(array));
    }

    @Test
    void testArrayWithNegatives() {
        int[] array = {-1, -2, -3, 10, 20};
        assertEquals(24, single.sum(array));
        assertEquals(24, parallel.sum(array));
    }

    @Test
    void testLargeArray() {
        int[] array = new int[10_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        assertEquals(10_000, single.sum(array));
        assertEquals(10_000, parallel.sum(array));
    }

    @Test
    void testParallelMatchesSingle() {
        int[] array = new int[1000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        long singleResult = single.sum(array);
        long parallelResult = parallel.sum(array);

        assertEquals(singleResult, parallelResult, "Многопоточный результат не совпадает с однопоточным");
    }

    @Test
    void testArrayNotDivisibleByThreads() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ParallelSum customParallel = new ParallelSum(4);
        assertEquals(55, customParallel.sum(array));
    }
}