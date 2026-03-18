package Cats.Comparator;
import Cats.Cat;
import java.util.Comparator;

public class CatAgeComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat cat1, Cat cat2) {
        return cat1.getAge() - cat2.getAge();
    }
}
