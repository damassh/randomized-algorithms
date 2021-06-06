public class QuickSelect {

    public int partition(double[] arrNumbers, int low, int high) {
        double pivot = arrNumbers[high];
        int pivotIndex = low;

        for (int i = low; i < high; i++) {
            if (arrNumbers[i] <= pivot) {
                swap(arrNumbers, i, pivotIndex);
                pivotIndex++;
            }
        }

        // swap pivot to the final pivot location
        swap(arrNumbers, pivotIndex, high);
        return pivotIndex;
    }

    // Return the k-th smallest element within left ... right inclusive
    public double select(double[] arrNumbers, int low, int high, int k) {
        // find the pivotIndex
        if (k > 0 && k <= high - low + 1) {
            int pivotIndex = partition(arrNumbers, low, high);
            if (pivotIndex - low == k - 1) {
                return arrNumbers[pivotIndex];
            }
            if (pivotIndex - low > k - 1) {
                return select(arrNumbers, low, pivotIndex - 1, k);
            }
            return select(arrNumbers, pivotIndex + 1, high, k - pivotIndex + low - 1);
        }
        return Integer.MAX_VALUE;
    }

    public double[] swap(double[] arrNumbers, int i, int j) {
        double temp = arrNumbers[i];
        arrNumbers[i] = arrNumbers[j];
        arrNumbers[j] = temp;
        return arrNumbers;
    }
}
