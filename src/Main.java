/*import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        InvertedIndex invertedIndex = new InvertedIndex();
        FileManager fileManager = new FileManager(invertedIndex);
        FileProcessor fileProcessor = new FileProcessor(fileManager);
        File file = new File("dataset");
        fileManager.processDirectory(file);
        List<File> filesList = fileManager.getAll();

        System.out.println("Enter num of threads: ");
        Scanner scanner = new Scanner(System.in);
        int numOfThreads = scanner.nextInt();
        fileProcessor.process(numOfThreads, filesList);

        System.out.println("Enter word: ");
        scanner.nextLine();
        String key = scanner.nextLine();
        Set<String> processedFiles = invertedIndex.getFilesForKey(key);
        System.out.println(processedFiles);

    }
}
*/