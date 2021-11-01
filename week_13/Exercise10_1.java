import java.util.TreeMap;

public class Exercise10_1 {
    public static void main(String[] args) {
        var directory = new PhoneDirectory();

        directory.putNumber("Fred", "555-1234");
        directory.putNumber("Barney", "555-2345");
        directory.putNumber("Wilma", "555-3456");

        System.out.println("Current contacts:");
        directory.print();
        System.out.println();

        System.out.println("Number for Fred is " + directory.getNumber("Fred"));
        System.out.println("Number for Wilma is " + directory.getNumber("Wilma"));
        System.out.println("Number for Tom is " + directory.getNumber("Tom"));
    }

    private static class PhoneDirectory {
        private TreeMap<String, String> data = new TreeMap<String, String>();

        public String getNumber(String name) {
            return data.get(name);
        }

        public void putNumber(String name, String number) throws IllegalArgumentException {
            if (name == null || number == null)
                throw new IllegalArgumentException("Name and number cannot be null");

            data.put(name, number);
        }

        public void print() {
            for (var entry : data.entrySet())
                System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
