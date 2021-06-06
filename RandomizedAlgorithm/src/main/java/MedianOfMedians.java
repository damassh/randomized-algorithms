import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MedianOfMedians {

    public double[] swap(double[] arrNumbers, int i, int j) {
        double temp = arrNumbers[i];
        arrNumbers[i] = arrNumbers[j];
        arrNumbers[j] = temp;
        return arrNumbers;
    }

    public int partition(double[] arrNumbers, int left, int right, double x) {
        int i;
        for (i = left; i < right; i++) {
            if (arrNumbers[i] == x) break;
        }
        swap(arrNumbers, i, right);

        // Standard partition algorithm
        i = left;
        for (int j = left; j <= right - 1; j++) {
            if (arrNumbers[j] <= x) {
                swap(arrNumbers, i, j);
                i++;
            }
        }
        swap(arrNumbers, i, right);
        return i;
    }

    public double getMedianOfMedians(double[] arrNumbers, int i) {
        if (i == 1) {
            return arrNumbers[i - 1];
        } else {
            return select(arrNumbers, 0, i -1 ,i /2);
        }
    }

    public double select(double[] arrNumbers, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int n = right - left + 1;

            int i;
            double[] median = new double[(n + 4) / 5];
            for (i = 0; i < median.length - 1; i++) {
                double[] arrTemp = Arrays.copyOfRange(arrNumbers, 5 * i + left, 5 * i + left + 4);
                Arrays.sort(arrTemp);
                median[i]  = arrTemp[2];
            }

            if(n % 5 == 0) {
                double[] arrTemp = Arrays.copyOfRange(arrNumbers, 5 * i + left, 5 * i + left + 4);
                Arrays.sort(arrTemp);
                median[i]  = arrTemp[2];
                i++;
            } else {
             double[] arrTemp = Arrays.copyOfRange(arrNumbers, 5 * i + left, 5 * i + left + (n % 5));
                Arrays.sort(arrTemp);
                median[i]  = arrTemp[(n % 5) / 2];
                i++;
            }

            double medianOfMedians = getMedianOfMedians(median, i);
            int position = partition(arrNumbers, left, right, medianOfMedians);

            // If position is same as k
            if (position - left == k - 1) {
                return arrNumbers[position];
            } else if (position - left > k - 1) {
                return select(arrNumbers, left, position - 1, k);
            } else {
                return select(arrNumbers, position + 1, right, k - position + left - 1);
            }
        }
        // If k is more than the number of elements in array
        return Integer.MAX_VALUE;
    }
}
