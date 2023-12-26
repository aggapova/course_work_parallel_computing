import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;
import java.util.stream.Stream;

public class FileManager {
    private final InvertedIndex invertedIndex;
    private final List<File> filesList = new ArrayList<>();

    public FileManager(InvertedIndex invertedIndex){
        this.invertedIndex = invertedIndex;
    }
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
            filesList.add(directory);
        }
    }

    public void readContent(File file) throws IOException {
        String fileContent = readFile(file);
        String[] splittedContent = fileContent.split("\\W"); //split non-word characters
        List<String> wordList = Stream.of(splittedContent)
                .filter(str -> !str.isEmpty())
                .toList();
        for (String word : wordList){
            invertedIndex.addToMapping(word, file.toString());
        }
    }

    public List<File> getAll() {
        return filesList;
    }
}
