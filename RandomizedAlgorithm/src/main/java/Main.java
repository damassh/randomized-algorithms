import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DIRECTORY = "src/main/resources";

    public static void runLazySelect(double[] arrNumbers, int k) throws IOException {
        long startTime = System.currentTimeMillis();
        LazySelect ls = new LazySelect();
        double val = ls.run(arrNumbers, k);
        long executionTime = (System.currentTimeMillis() - startTime);
        if (val != -1) {
            print("lazySelect", k, val, executionTime);
        } else {
            System.out.println("Maximum number of runs exceeded. Algorithm failed to produce correct output.");
        }
    }

    public static void runQuickSelect(double[] arrNumbers, int k) throws IOException {
        long startTime = System.currentTimeMillis();
        QuickSelect qs = new QuickSelect();
        double val = 0;
        if (k > arrNumbers.length) {
            System.out.println("Index out of bound");
        } else {
            val = qs.select(arrNumbers, 0, arrNumbers.length - 1, k);
            long executionTime = (System.currentTimeMillis() - startTime);
            print("quickSelect", k, val, executionTime);
        }
    }

    public static void runMedianOfMedians(double[] arrNumbers, int k) throws IOException {
        long startTime = System.currentTimeMillis();
        MedianOfMedians mom = new MedianOfMedians();
        double val = 0;
        if (k > arrNumbers.length) {
            System.out.println("Index out of bound");
        } else {
            val = mom.select(arrNumbers, 0, arrNumbers.length - 1, k);
            long executionTime = (System.currentTimeMillis() - startTime);
            print("medOfMeds", k, val, executionTime);
        }
    }


    public static double[] getData(String fileName) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        List<Double> list = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            list.add(Double.parseDouble(line));
        }

        double[] arrNumbers = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrNumbers[i] = list.get(i);
        }
        return arrNumbers;
    }

    public static String getFileName(String filename) {
        String[] f = filename.split("/");
        return String.valueOf(f[f.length - 1]);
    }

    public static void print(String algorithm, int kPosition, double kthElement, long executionTime) {
        System.out.println(algorithm + "\t\t\t " + kPosition + "\t\t\t\t\t " + kthElement
                + "\t\t\t" + executionTime);
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Start");
        ReadFiles readFiles = new ReadFiles();
        List<String> files = readFiles.getFilesFromDirectory(DIRECTORY);
        String[] algorithms = {"lazySelect", "quickSelect", "medianOfMedians"};


        for (String file: files) {
            System.out.println("Benchmark: " + getFileName(file));
            double[] arrNumbers = getData(file);
//            int k = 1;
            int k = arrNumbers.length / 2;
            System.out.println("Algorithm \t\t\t k position \t\t kth element \t\t Execution Time(ms)");
            System.out.println("---------------------------------------------------------------------------------------");
            for (String algorithm: algorithms) {
                if (algorithm.equals("lazySelect")) {
                    runLazySelect(arrNumbers, k);
                } else if (algorithm.equals("quickSelect")) {
                    runQuickSelect(arrNumbers, k);
                } else if (algorithm.equals("medianOfMedians")) {
                    runMedianOfMedians(arrNumbers, k);
                }
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
}
