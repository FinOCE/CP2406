public class Exercise2_2 {
    public static void main(String[] args) {
        int roll1 = (int)(Math.random() * 6) + 1;
        int roll2 = (int)(Math.random() * 6) + 1;
        int total = roll1 + roll2;

        System.out.println("The first die comes up " + roll1);
        System.out.println("The first die comes up " + roll2);
        System.out.println("Your total roll is " + total);
    }
}
