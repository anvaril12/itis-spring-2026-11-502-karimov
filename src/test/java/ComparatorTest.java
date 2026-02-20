import Cats.Cat;
import Cats.Comparator.CatAgeComparator;
import Cats.Comparator.CatColorComparator;
import Cats.Comparator.CatNameComparator;
import Cars.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorTest {

    @Test
    void equals_Cat() {
        Cat cat1 = new Cat("Персик", "Черный", 5);
        Cat cat2 = new Cat("Персик", "Черный", 5);
        assertEquals(true, cat1.equals(cat2));
    }

    @Test
    void hashCode_Cat() {
        Cat cat1 = new Cat("Персик", "Черный", 5);
        Cat cat2 = new Cat("Персик", "Черный", 5);
        assertEquals(cat1.hashCode(), cat2.hashCode());
    }

    @Test
    void AgeComparator() {
        CatAgeComparator comparator = new CatAgeComparator();
        Cat cat1 = new Cat("Персик", "Черный", 5);
        Cat cat2 = new Cat("Жупсик", "Белый", 10);
        Cat cat3 = new Cat("Максим", "Серый", 5);
        assertTrue(comparator.compare(cat1, cat2) < 0);
        assertTrue(comparator.compare(cat2, cat1) > 0);
        assertEquals(0, comparator.compare(cat1, cat3));
    }

    @Test
    void ColorComparator() {
        CatColorComparator comparator = new CatColorComparator();
        Cat cat1 = new Cat("Персик", "Черный", 5);
        Cat cat2 = new Cat("Жупсик", "Белый", 10);
        Cat cat3 = new Cat("Максим", "Черный", 7);
        assertTrue(comparator.compare(cat1, cat2) > 0);
        assertTrue(comparator.compare(cat2, cat1) < 0);
        assertEquals(0, comparator.compare(cat1, cat3));
    }

    @Test
    void NameComparator() {
        CatNameComparator comparator = new CatNameComparator();
        Cat cat1 = new Cat("Ислам", "Белый", 4);
        Cat cat2 = new Cat("Евгений", "Черный", 6);
        Cat cat3 = new Cat("Ислам", "Серый", 10);
        assertTrue(comparator.compare(cat1, cat2) > 0);
        assertTrue(comparator.compare(cat2, cat1) < 0);
        assertEquals(0,comparator.compare(cat1,cat3));
    }

    @Test
    void CompareTOComparator() {
        Car car1 = new Car("Лада", 2000);
        Car car2 = new Car("Мерседес", 2010);
        Car car3 = new Car("БМВ", 2000);
        assertTrue(car1.compareTo(car2) < 0);
        assertTrue(car2.compareTo(car1) > 0);
        assertEquals(0,car1.compareTo(car3));
    }
}
