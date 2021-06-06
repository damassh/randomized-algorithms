import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFiles {

    public static List<String> getFilesFromDirectory(String directory) throws IOException {
        File directoryPath = new File(directory);
        List<String> files = new ArrayList<>();

        if (!directoryPath.exists()) {
            throw new IllegalArgumentException("Directory doesn't exist");
        }

        if (directoryPath.isDirectory()) {
            for (File file: directoryPath.listFiles()) {
                if(file.isDirectory()) {
                    files.addAll(getFilesFromDirectory(file.getAbsolutePath()));
                } else {
                    files.add(file.getAbsolutePath());
                }
            }
        } else {
            files.add(String.valueOf(directoryPath));
        }
        Collections.sort(files);
        return files;
    }
}
