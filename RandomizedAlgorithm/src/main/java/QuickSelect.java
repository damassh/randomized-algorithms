public class QuickSelect {

    public int partition(int[] arrNumbers, int low, int high) {
        int pivot = arrNumbers[high];
        int pivotIndex = low;

        for (int i = low; i <= high; i++) {
            if (arrNumbers[i] < pivot) {
                int temp = arrNumbers[i];
                arrNumbers[i] = arrNumbers[pivotIndex];
                arrNumbers[pivotIndex] = temp;
                pivotIndex++;
            }
        }

        // swap pivot to the final pivot location
        swap(arrNumbers, pivotIndex, high);

        return pivotIndex;
    }

    // Return the k-th smallest element within left ... right inclusive
    public int select(int[] arrNumbers, int low, int high, int k) {
        // find the pivotIndex
        int pivotIndex = partition(arrNumbers, low, high);

        if (pivotIndex == k) {
            return arrNumbers[pivotIndex];
        } else if (pivotIndex < k) {
            return select(arrNumbers, pivotIndex + 1, high, k);
        } else {
            return select(arrNumbers, low, pivotIndex - 1, k);
        }
    }

    public int[] swap(int[] arrNumbers, int i, int j) {
        int temp = arrNumbers[i];
        arrNumbers[i] = arrNumbers[j];
        arrNumbers[j] = temp;
        return arrNumbers;
    }

    public static void main(String[] args) {
        QuickSelect quickSelect = new QuickSelect();
        int[] array = new int[] { 10, 4, 5, 8, 6, 11, 26 };
        int[] arraycopy = new int[] { 10, 4, 5, 8, 6, 11, 26 };
        int kPosition = 3;
        int length = array.length;

        if (kPosition > length) {
            System.out.println("Index out of bound");
        } else {
            System.out.println("K-th smallest element in array : " +
                    quickSelect.select(arraycopy, 0, length - 1, kPosition -1));
        }
    }
}
