package file;

import java.util.List;
import java.io.File;

public class FileProcessor {
    private final FileManager fileManager;

    public FileProcessor(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public long process(int numOfThreads, List<File> files) throws InterruptedException {
        FileProcessorThread[] processorThreads = new FileProcessorThread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++){
            processorThreads[i] = new FileProcessorThread(
                    files,
                    fileManager,
                    (files.size() / numOfThreads) * i,
                    (files.size() / numOfThreads) * (i + 1)
                    );
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
            processorThreads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
           processorThreads[i].join();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
