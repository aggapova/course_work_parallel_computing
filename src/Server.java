import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(9999)){
            System.out.println("Waiting for connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.print("Connection accepted.");

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            InvertedIndex invertedIndex = new InvertedIndex();
            FileManager fileManager = new FileManager(invertedIndex);
            File file = new File("dataset");
            fileManager.processDirectory(file);
            List<File> filesList = fileManager.getAll();

            System.out.println("Server reading from channel");
            int numOfThreads = in.readInt();
            FileProcessor fileProcessor = new FileProcessor(fileManager);
            fileProcessor.process(numOfThreads, filesList);

            String key = in.readUTF();
            Set<String> processedFiles = invertedIndex.getFilesForKey(key);
            out.writeUTF(processedFiles.toString());

            in.close();
            out.close();
            clientSocket.close();// not needed in multi-threading
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
