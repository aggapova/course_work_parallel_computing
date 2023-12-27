import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String host = "localhost";
    private static final int port = 8888;


    public static void main(String[] args) {
        try (Socket serverSocket = new Socket(host, port);
             DataOutputStream out = new DataOutputStream(serverSocket.getOutputStream());
             DataInputStream in = new DataInputStream(serverSocket.getInputStream())) {

            System.out.print("Enter num of threads: ");
            int numberOfThreads = scanner.nextInt();
            out.writeInt(numberOfThreads);

            System.out.print("Enter the word: ");
            scanner.nextLine();
            String word = scanner.nextLine().toLowerCase();
            out.writeUTF(word);

            String indexedFiles = in.readUTF();
            System.out.println(indexedFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
