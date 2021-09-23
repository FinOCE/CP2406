import textio.TextIO;

public class Exercise7_5 {
    private static final int LENGTH = 100;

    private class QuickSort {
        public static int[] sort(int[] arr, int length) {
            sort(arr, 0, length - 1);
            return arr;
        }
    
        private static void sort(int[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
    
                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }
        }
    
        private static int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = low - 1;
    
            for (int j = low; j <= high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
    
            swap(arr, i + 1, high);
    
            return i + 1;
        }
    
        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int i = 0;
        int[] numbers = new int[LENGTH];

        System.out.println("Enter numbers to sort:");

        while (true) {
            System.out.print("> ");
            int input = TextIO.getlnInt();

            if (input == 0) break;

            numbers[i++] = input;

            if (i == LENGTH) break;
        }

        numbers = QuickSort.sort(numbers, i);

        System.out.print("Your numbers sorted: ");
        for (int j = 0; j < i; j++)
            System.out.printf("%d ", numbers[j]);
    }
}
