package client;

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

            boolean continueSearching = true;
            while (continueSearching) {
                System.out.print("Enter num of threads: ");
                int numOfThreads = scanner.nextInt();
                out.writeInt(numOfThreads);
                long buildTime = in.readLong();
                System.out.printf("Time for building %s ms, threads: %s\n", buildTime, numOfThreads);

                System.out.print("Enter the word: ");
                scanner.nextLine();
                String key = scanner.nextLine().toLowerCase();
                out.writeUTF(key);

                String indexedFiles = in.readUTF();
                System.out.println(indexedFiles);

                System.out.print("Do you want to search for more words? (yes/no): ");
                String response = scanner.next().toLowerCase();
                out.writeUTF(response);
                continueSearching = response.equals("yes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
