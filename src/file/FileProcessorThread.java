package file;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileProcessorThread extends Thread {
    FileManager fileManager;
    List<File> files;
    int start;
    int end;

    public FileProcessorThread(List<File> files, FileManager fileManager, int start, int end){
        this.files = files;
        this.fileManager = fileManager;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        for (int i = start; i < end; i++){
            try{
                fileManager.readContent(files.get(i));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
