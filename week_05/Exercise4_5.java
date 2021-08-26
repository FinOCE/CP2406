public class Exercise4_5 {
    public interface ArrayProcessor {
        double apply(double[] array);
    }

    public static final ArrayProcessor findMax = array -> {
        double max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }

        return max;
    };

    public static final ArrayProcessor findMin = array -> {
        double min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) min = array[i];
        }

        return min;
    };

    public static final ArrayProcessor getSum = array -> {
        double sum = 0;
        
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sum;
    };

    public static final ArrayProcessor getAverage = array -> {
        double sum = getSum.apply(array);
        return sum/array.length;
    };

    public static ArrayProcessor counter(double value) {
        return array -> {
            int instancesOfValue = 0;

            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) instancesOfValue++;
            }

            return instancesOfValue;
        };
    }

    public static void main(String[] args) {
        double[] array = { 0, 10.5, 13, 14, -7.1, 18.4 };

        System.out.println("Minimum value of array: " + findMin.apply(array));
        System.out.println("Maximum value of array: " + findMax.apply(array));
        System.out.println("Sum of array: " + getSum.apply(array));
        System.out.println("Average of array: " + getAverage.apply(array));
    }
}
