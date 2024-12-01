import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static String[] readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] lines = reader.lines().toList().toArray(new String[0]);
        reader.close();
        return lines;
    }
}
