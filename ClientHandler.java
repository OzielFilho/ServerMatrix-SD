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
        System.out.println("Client Handler Init in: (" + this.threadId() + "):");
        try {

            ObjectInputStream inputDataClient = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream outDataClient = new ObjectOutputStream(clientSocket.getOutputStream());

            String[] parametersClient = (String[]) inputDataClient.readObject();

            if (parametersClient == null) {
                outDataClient.writeObject("Parameters Empty");
            }
            if (parametersClient.length != 2) {
                System.out.println("Problem in parameters format: <Matrix> <Matrix>");
                return;
            }
            String parameter1 = parametersClient[0];
            String parameter2 = parametersClient[0];
            Matrix matrix1 = Matrix.fromString(parameter1);
            Matrix matrix2 = Matrix.fromString(parameter2);

            Matrix multiplyMatrix = matrix1.multiply(matrix2);

            outDataClient.writeObject(multiplyMatrix.toString());

            Thread.sleep(500);

            System.out.println("Client (" + this.threadId() + ") Handler End");

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