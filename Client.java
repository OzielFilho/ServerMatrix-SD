import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 8080);
            System.out.println("Client Init");

            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            int[] typeMatrix = { 2, 2 };
            out.writeObject(typeMatrix);
            String matrix = (String) in.readObject();
            System.out.println("Server response: \n" + matrix);

            client.close();
            System.out.println("Client END");

        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

}
