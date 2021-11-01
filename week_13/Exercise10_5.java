import java.util.Arrays;
import java.util.stream.Collectors;

public class Exercise10_5 {
    public static void main(String[] args) {
        // Count the number of students
        var count = Arrays.stream(scoreData).parallel()
            .count();

        System.out.println("Number of students: " + count);

        // Calculate the average grade
        var total = Arrays.stream(scoreData).parallel()
            .mapToInt(s -> s.score)
            .sum();

        System.out.printf("Average grade: %1.2f%n", (double)total / count);

        // Count the number of students who get an A
        var countA = Arrays.stream(scoreData).parallel()
            .filter(s -> s.score >= 90)
            .count();

        System.out.println("Number who got an A: " + countA);
        System.out.println();

        // Count the number of students who are failing
        var failing = Arrays.stream(scoreData)
            .filter(s -> s.score < 70)
            .map(s -> s.firstName + " " + s.lastName)
            .collect(Collectors.toList());

        System.out.println("Failing students: ");
        failing.stream().forEach(System.out::println);
        System.out.println();

        // Display data sorted by last name
        System.out.println("Data sorted by last name:");
        Arrays.stream(scoreData)
            .sorted((s1, s2) -> s1.lastName.compareTo(s2.lastName))
            .forEach(s -> System.out.printf("  %s, %s: %d\n", s.lastName, s.firstName, s.score));
        System.out.println();

        // Display data sorted by score
        System.out.println("Data sorted by score:");
        Arrays.stream(scoreData)
            .sorted((s1, s2) -> s1.score - s2.score)
            .forEach(s -> System.out.printf("  %s, %s: %d\n", s.lastName, s.firstName, s.score));
    }

    private static class ScoreInfo {
        String firstName;
        String lastName;
        int score;

        ScoreInfo(String lName, String fName, int s) {
            firstName = fName;
            lastName = lName;
            score = s;
        }
    }

    private static ScoreInfo[] scoreData = new ScoreInfo[] {
        new ScoreInfo("Smith", "John", 70),
        new ScoreInfo("Doe", "Mary", 85), new ScoreInfo("Page", "Alice", 82), new ScoreInfo("Cooper", "Jill", 97),
        new ScoreInfo("Flintstone", "Fred", 66), new ScoreInfo("Rubble", "Barney", 80),
        new ScoreInfo("Smith", "Judy", 48), new ScoreInfo("Dean", "James", 90), new ScoreInfo("Russ", "Joe", 55),
        new ScoreInfo("Wolfe", "Bill", 73), new ScoreInfo("Dart", "Mary", 54), new ScoreInfo("Rogers", "Chris", 78),
        new ScoreInfo("Toole", "Pat", 51), new ScoreInfo("Khan", "Omar", 93), new ScoreInfo("Smith", "Ann", 95)
    };
}