import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Exercise10_4 {
    public static void main(String[] args) {
        List<Integer> result;

        // Test if an Integer is even
        Predicate<Integer> pred = i -> i % 2 == 0;

        var coll = makeSet();
        System.out.println("Original collection: " + coll);

        Predicates.remove(coll, pred);
        System.out.println("Remove evens: " + coll);

        coll = makeSet();
        Predicates.retain(coll, pred);
        System.out.println("Retain evens: " + coll);

        coll = makeSet();
        result = Predicates.collect(coll, pred);
        System.out.println("Collect evens: " + result);

        var list = new ArrayList<Integer>(coll);
        int i = Predicates.find(list, pred);
        System.out.println("Find first even: at index " + i);

        // Test if an Integer is bigger than 100
        pred = n -> n > 100;

        coll = makeSet();
        System.out.println("Original collection: " + coll);

        Predicates.remove(coll, pred);
        System.out.println("Remove big: " + coll);

        coll = makeSet();
        Predicates.retain(coll, pred);
        System.out.println("Retain big: " + coll);

        coll = makeSet();
        result = Predicates.collect(coll, pred);
        System.out.println("Collect big: " + result);

        list = new ArrayList<Integer>(coll);
        i = Predicates.find(list, pred);
        System.out.println("Find first big: at index " + i);
    }

    private static Collection<Integer> makeSet() {
        var set = new TreeSet<Integer>();

        set.add(Integer.valueOf(32));
        set.add(Integer.valueOf(17));
        set.add(Integer.valueOf(142));
        set.add(Integer.valueOf(56));
        set.add(Integer.valueOf(1899));
        set.add(Integer.valueOf(57));
        set.add(Integer.valueOf(999));
        set.add(Integer.valueOf(86));
        set.add(Integer.valueOf(83));
        set.add(Integer.valueOf(100));

        return set;
    }

    private class Predicates {
        public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
            var iter = coll.iterator();
            while (iter.hasNext()) {
                T item = iter.next();

                if (pred.test(item))
                    iter.remove();
            }
        }

        public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
            var iter = coll.iterator();
            while (iter.hasNext()) {
                T item = iter.next();

                if (!pred.test(item))
                    iter.remove();
            }
        }

        public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
            var list = new ArrayList<T>();

            for (var item : coll) {
                if (pred.test(item))
                    list.add(item);
            }

            return list;
        }

        public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
            for (int i = 0; i < list.size(); i++) {
                T item = list.get(i);

                if (pred.test(item))
                    return i;
            }

            return -1;
        }
    }
}
