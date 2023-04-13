import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {

            System.out.println("Server Init");

            while (true) {
                ClientHandler handler = new ClientHandler(server.accept());
                handler.start();
            }

        } catch (Exception e) {

        }
    }
}
