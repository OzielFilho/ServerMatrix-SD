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

            Matrix matrix1 = new Matrix(2, 2);
            matrix1.randomize(0, 10);
            Matrix matrix2 = new Matrix(2, 2);
            matrix2.randomize(0, 10);

            String[] typeMatrix = { matrix1.toString(), matrix2.toString() };
            out.writeObject(typeMatrix);

            String matrix = (String) in.readObject();
            System.out.println("Server response: \n" + matrix);

            client.close();

        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

}
