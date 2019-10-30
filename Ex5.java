import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ex5 {

    public static void main(String[] args) {
        testBest();
    }

    public static void testBest() {
        LispList<Integer> ls = makeList();
        ClosestTo comp = new ClosestTo(20);
        System.out.println(best(ls, comp));
    }

    public static int count(LispList<Integer> ls, int n) {
        if (ls.isEmpty()) return 0;
        else if (ls.head() == n) return count(ls.tail(), n) + 1;
        else return count(ls.tail(), n);
    }

    public static boolean ordered(LispList<Integer> ls) {
        if (ls.isEmpty() || ls.tail().isEmpty()) return true;
        else if (!(ls.head() < ls.tail().head())) return false;
        else return ordered(ls.tail());
    }

    public static LispList<Integer> filter(LispList<Integer> ls, int n) {
        if (ls.isEmpty()) return LispList.<Integer>empty();
        else if (ls.head() < n) return filter(ls.tail(), n);
        else return filter(ls.tail(), n).cons(ls.head());
    }

    public static LispList<Integer> positions(LispList<Integer> ls, int n) {
        return positions(ls, n, 0);
    }

    private static LispList<Integer> positions(LispList<Integer> ls, int n, int i) {
        if (ls.isEmpty()) return LispList.<Integer>empty();
        else if (ls.head() == n) return positions(ls.tail(), n, i+1).cons(i);
        else return (positions(ls.tail(), n, i+1));
    }

    public static boolean sublist(LispList<Integer> ls1, LispList<Integer> ls2) {
        if (ls2.isEmpty()) return ls1.isEmpty();
        else if (ls1.isEmpty()) return true;
        else if (ls1.head() == ls2.head()) {
            if (sublistHelper(ls1.tail(), ls2.tail())) return true;
            else return sublist(ls1, ls2.tail());
        }
        else return sublist(ls1, ls2.tail());
    }

    private static boolean sublistHelper(LispList<Integer> ls1, LispList<Integer> ls2) {
        if (ls2.isEmpty()) return ls1.isEmpty();
        else if (ls1.isEmpty()) return true;
        else if (ls1.head() == ls2.head())
            return sublistHelper(ls1.tail(), ls2.tail());
        else return false;
    }

    public static boolean subset(LispList<Integer> ls1, LispList<Integer> ls2) {
        if (ls2.isEmpty()) return ls1.isEmpty();
        else if (ls1.isEmpty()) return true;
        else {
            if (subsetHelper(ls1.head(), ls2)) return subset(ls1.tail(), ls2);
            else return false;
        }
    }

    private static boolean subsetHelper(int n, LispList<Integer> ls2) {
        if (ls2.isEmpty()) return false;
        else if (ls2.head() == n) return true;
        else return subsetHelper(n, ls2.tail());
    }

    public static int best(LispList<Integer> ls, Comparator<Integer> comp) {
        if (ls.isEmpty()) return -1;
        else return best(ls.tail(), comp, ls.head());
    }

    private static int best(LispList<Integer> ls, Comparator<Integer> comp, int biggest) {
        if (ls.isEmpty()) return biggest;
        if (comp.compare(ls.head(), biggest) > 0) return best(ls.tail(), comp, ls.head());
        else return best(ls.tail(), comp, biggest);
    }

    private static LispList<Integer> makeList() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a list of lists of integers (all on one line):");
        String line = input.nextLine();
        return makeLispListInt(line);
    }

    private static LispList<Integer> makeLispListInt(String str)
    // Takes a string representing a list of integers and returns
    // the equivalent Lisp list of integers
    {
        str=str.trim();
        str=str.substring(1,str.length()-1).trim();
        return makeLispListInt1(str);
    }

    private static LispList<Integer> makeLispListInt1(String str)
    {
        String[] ints = str.split(" *, *");
        LispList<Integer> ls = LispList.<Integer>empty();
        for(int i=ints.length-1; i>=0; i--)
            try {
                ls = ls.cons(Integer.parseInt(ints[i]));
            } catch (NumberFormatException e) {}
        return ls;
    }
}
