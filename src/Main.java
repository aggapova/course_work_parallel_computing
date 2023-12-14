import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        File file = new File("test");
        fileManager.processDirectory(file);
    }
}