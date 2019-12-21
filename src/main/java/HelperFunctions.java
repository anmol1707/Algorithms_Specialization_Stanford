import java.io.FileNotFoundException;
import java.io.InputStream;

public class HelperFunctions {

    public static InputStream readFileAsStream(String filePath) throws FileNotFoundException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found at the specified filePath!");
        }
        return inputStream;
    }
}
