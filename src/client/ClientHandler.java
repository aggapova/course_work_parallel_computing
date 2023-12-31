package client;

import file.FileManager;
import file.FileProcessor;
import invertedindex.InvertedIndex;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
             DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

            InvertedIndex invertedIndex = new InvertedIndex();
            FileManager fileManager = new FileManager(invertedIndex);
            File dataset = new File("dataset");
            fileManager.processDirectory(dataset);
            List<File> filesList = fileManager.getAll();
            System.out.println("Server is reading from the channel");

            while (true) {
                int numOfThreads = in.readInt();
                FileProcessor fileProcessor = new FileProcessor(fileManager);
                out.writeLong(fileProcessor.process(numOfThreads, filesList));

                String key = in.readUTF();
                Set<String> processedFiles = invertedIndex.getFilesForKey(key);

                if (processedFiles == null || processedFiles.isEmpty()) {out.writeUTF("No matching files found.");}
                else{
                    out.writeUTF("Matching files:\n" + processedFiles);
                }

                String response = in.readUTF();
                if (!response.equals("yes")) break;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

