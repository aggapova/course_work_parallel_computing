import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {
    private static final int THREAD_POOL_SIZE = 5;
    private static final ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server is running. Waiting for connections...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientThread = new ClientHandler(clientSocket);
                System.out.println("Adding new client.");

                pool.execute(clientThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}
