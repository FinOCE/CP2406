public class Exercise7_2 {
    public static void main(String[] args) {
        System.out.println("Before transpose:");
        var M = new int[][] {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 }
        };
        print(M);

        System.out.println();

        System.out.println("After transpose:");
        var T = transpose(M);
        print(T);
    }

    public static int[][] transpose(int[][] M) {
        int R = M.length;
        int C = M[0].length; // Assuming all rows are of equal length
        int[][] T = new int[C][R];

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                T[i][j] = M[j][i];
            }
        }

        return T;
    }

    public static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }

            System.out.println();
        }
    }
}
