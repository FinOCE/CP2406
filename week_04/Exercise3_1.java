public class Exercise3_1 {
    public static void main(String[] args) {
        int roll1 = (int)(Math.random()*6+1);
        int roll2 = (int)(Math.random()*6+1);

        System.out.printf("Rolls: %d %d", roll1, roll2);

        if (roll1 == 1 && roll2 == 1) {
            System.out.println();
            System.out.printf("Snake eyes");
        }
    }
}
