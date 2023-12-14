import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Objects;

public class FileManager {
    public String readFile(File file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine());
            }
        }

        return contentBuilder.toString();
    }

    public void processDirectory(File directory) throws IOException {
        if(directory.isDirectory()){
            File[] files = Objects.requireNonNull(directory.listFiles());
            for (File file: files){
                processDirectory(file);
            }
        }else{
            String fileContent = readFile(directory);
            System.out.println(fileContent);
        }
    }
}
