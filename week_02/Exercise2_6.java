import textio.TextIO;

public class Exercise2_6 {
    public static void main(String[] args) {
        System.out.println("Please enter your first name and last name, separated by a space.");
        System.out.print("? ");
        String fullName = TextIO.getln();

        int separator = fullName.indexOf(" ");
        String firstName = fullName.substring(0, separator);
        String lastName = fullName.substring(separator + 1);

        // This could also be achieved by doing:
        // String firstName = fullName.split(" ")[0];
        // String lastName = fullName.split(" ")[1];

        System.out.println("Your first name is " + firstName + ", which has " + firstName.length() + " characters");
        System.out.println("Your last name is " + lastName + ", which has " + lastName.length() + " characters");
        System.out.println("Your initials are " + firstName.charAt(0) + lastName.charAt(0));
    }
}
