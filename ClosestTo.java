import java.util.Comparator;

public class ClosestTo implements Comparator<Integer> {

    private int val;

    public ClosestTo(int n) {
        this.val = n;
    }

    @Override
    public int compare(Integer n1, Integer n2) {
        if (Math.abs(val - n1) == Math.abs(val - n2)) return 0;
        else if (Math.abs(val - n1) < Math.abs(val - n2)) return 1;
        else return -1;
    }
}
