import java.util.Comparator;

public class ComparableElement implements Comparator<Element> {
    @Override
    public int compare(Element o1, Element o2) {
        float reasult = o1.value - o2.value;
        if(reasult == 0)
            return 0;

        if(reasult < 0)
            return -1;

        if(reasult > 0)
            return 1;

        return 0;
    }
}
