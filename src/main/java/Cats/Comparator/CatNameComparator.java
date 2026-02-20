package Cats.Comparator;
import Cats.Cat;
import java.util.Comparator;

public class CatNameComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat cat1, Cat cat2) {
        return cat1.getName().compareTo(cat2.getName());
    }
}
