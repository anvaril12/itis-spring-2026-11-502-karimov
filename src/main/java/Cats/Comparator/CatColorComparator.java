package Cats.Comparator;
import Cats.Cat;
import java.util.Comparator;

public class CatColorComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat cat1, Cat cat2) {
        return cat1.getColor().compareTo(cat2.getColor());
    }
}
