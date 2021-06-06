import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LazySelect {
    
    public static List<Double> generateR(double[] arrNumbers, int size) {
        List<Double> R = new ArrayList<>(size);
        Random rand = new Random();
        int i = 0;
        while (i < size) {
            int j = rand.nextInt(arrNumbers.length - 1);
            if (!R.contains(arrNumbers[j])) {
                R.add(arrNumbers[j]);
                i++;
            }
        }
        Collections.sort(R);
        return R;
    }
    public static double select(double[] arrNumbers, int k) {
        // validate rank bound
        if (k == arrNumbers.length) {
            return Arrays.stream(arrNumbers).max().getAsDouble();
        } else if (k == 1) {
            return Arrays.stream(arrNumbers).min().getAsDouble();
        }

        int len = arrNumbers.length;
        int rSize = (int) Math.pow(len, 0.75);
        List<Double> R = generateR(arrNumbers, rSize);
        int kn = (int) (k * Math.pow(len, -0.25));
        int rankA = (int) Math.max(kn - Math.sqrt(len), 0);
        int rankB = (int) Math.min(kn + Math.sqrt(len), rSize - 1);

        int low = 0;
        int high = 0;
        List<Double> P = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (arrNumbers[i] < R.get(rankA)) low++;
            if (arrNumbers[i] < R.get(rankB)) high++;
            if (arrNumbers[i] >= R.get(rankA) && arrNumbers[i] <= R.get(rankB)) P.add(arrNumbers[i]);
        }

        if (k >= low && k <= high && P.size() <= 4 * rSize + 1) {
            Collections.sort(P);
            int position = k - low - 1;
            if (position >= 0) return P.get(position);
            else return - 1;
        } else {
            return - 1;
        }
    }

    public static double run(double[] arrNumbers, int k) {
        double val = -1;
        if (k > arrNumbers.length || k <= 0) {
            System.out.println("Array length is less than k");
        } else {
            while(val == -1) {
                val = select(arrNumbers, k);
            }
        }
        return val;
    }
}
