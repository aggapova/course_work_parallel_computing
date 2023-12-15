import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        InvertedIndex invertedIndex = new InvertedIndex();
        FileManager fileManager = new FileManager(invertedIndex);
        Scanner scanner = new Scanner(System.in);
        File file = new File("test");
        fileManager.processDirectory(file);
        System.out.print("Enter a word: ");
        String s = scanner.nextLine();
        Set<String> files = invertedIndex.getFilesForKey(s);
        System.out.println(files);
    }
}