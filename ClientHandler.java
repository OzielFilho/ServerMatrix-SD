import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    Socket clientSocket;

    ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        System.out.println("Client Handler Init in: (" + this.getId() + "):" + clientSocket.getInetAddress());
        try {
            ObjectInputStream inputDataClient = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream outDataClient = new ObjectOutputStream(clientSocket.getOutputStream());
            int[] parametersClient = (int[]) inputDataClient.readObject();
            if (parametersClient.length != 2) {
                System.out.println("Problem in parameters format: <int> <int>");
                return;
            }
            Matrix matrix1 = new Matrix(parametersClient[0], parametersClient[1]);
            matrix1.randomize(0, 10);
            Matrix matrix2 = new Matrix(parametersClient[0], parametersClient[1]);
            matrix2.randomize(0, 10);

            Matrix multiplyMatrix = matrix1.multiply(matrix2);

            outDataClient.writeObject(multiplyMatrix.toString());

            Thread.sleep(15000);

            System.out.println("Client Handler End");

            clientSocket.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}