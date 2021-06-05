import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MedianOfMedians {

    public int findMedian(Integer[] arrNumbers, int i, int n) {
        if (i >= n) {
            Arrays.sort(arrNumbers, n, i);
        } else {
            Arrays.sort(arrNumbers, i, n);
        }
        // return the median element
        return arrNumbers[n / 2];
    }

    public Integer[] swap(Integer[] arrNumbers, int i, int j) {
        int temp = arrNumbers[i];
        arrNumbers[i] = arrNumbers[j];
        arrNumbers[j] = temp;
        return arrNumbers;
    }

    public int partition(Integer[] arrNumbers, int left, int right, int x) {
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

    public int getMedianOfMedians(Integer[] arrNumbers, int i) {
        if (i == 1) {
            return arrNumbers[i - 1];
        } else {
            return select(arrNumbers, 0, i -1 ,i /2);
        }
    }

    public int select(Integer[] arrNumbers, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int n = right - left + 1;

            int i;
            Integer[] median = new Integer[(n + 4) / 5];
            for (i = 0; i < n / 5; i++) {
                median[i] = findMedian(arrNumbers, left + i * 5, 5);
            }
            // For last group with less than 5 elements
            if (i * 5 < n) {
                median[i] = findMedian(arrNumbers, left + i * 5, n % 5);
                i++;
            }

            int medianOfMedians = getMedianOfMedians(median, i);
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

    public static void main(String[] args) {
        MedianOfMedians medOfMeds = new MedianOfMedians();
//        int arr[] = {12, 3, 5, 7, 4, 19, 26};
//        int n = arr.length, k = 3;


        Set<Integer> list2 = new LinkedHashSet<Integer>();
        Random r = new Random();
        while( list2.size() < 20000 ) {
            list2.add(r.nextInt(20000));
        }

        int n = list2.size();
        Integer[] arr = new Integer[n];
        arr = list2.toArray(arr);
        n = arr.length;
        int k = 17;

        System.out.println("K'th smallest element is "
                + medOfMeds.select(arr, 0, n - 1, k));
    }
}
