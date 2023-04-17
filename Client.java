import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        while (true) {
            try {
                Socket client = new Socket("localhost", 8080);
                System.out.println("Client Init");

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                Scanner scanner = new Scanner(System.in);

                System.out
                        .print("1. Generate Matrix (2x2) automatically.\n2. Enter your Matrix (2x2).\n3. Close\n");
                int value = scanner.nextInt();

                switch (value) {
                    case 1:
                        Matrix matrix1 = new Matrix(2, 2);
                        matrix1.randomize(0, 10);
                        Matrix matrix2 = new Matrix(2, 2);
                        matrix2.randomize(0, 10);
                        System.out.println(
                                "Matrix: \n" + matrix1.toString() + "\n---------\n" + matrix2.toString());

                        String[] typeMatrix = { matrix1.toString(), matrix2.toString() };
                        out.writeObject(typeMatrix);

                        String matrix = (String) in.readObject();
                        System.out.println("Server response multiply: \n" + matrix);
                        scanner.close();

                        break;
                    case 2:
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter your matrix 1 (on one line): ");
                        String m1 = "" + sc.nextLine();
                        System.out.println("Enter your matrix 2 (on one line): ");
                        String m2 = "" + sc.nextLine();

                        sc.close();

                        String[] m1Array = m1.split(" ");
                        List<Integer> m1IntList = new ArrayList<>();
                        for (String s : m1Array) {
                            m1IntList.add(Integer.parseInt(s));
                        }

                        String[] m2Array = m2.split(" ");
                        List<Integer> m2IntList = new ArrayList<>();
                        for (String s : m2Array) {
                            m2IntList.add(Integer.parseInt(s));
                        }

                        Matrix matrixInput1 = Matrix.fromArray(m1IntList, 2, 2);
                        Matrix matrixInput2 = Matrix.fromArray(m2IntList, 2, 2);
                        System.out.println("Matrix: \n" + matrixInput1.toString() + "\n---------\n"
                                + matrixInput2.toString());

                        String[] typeMatrixInput = { matrixInput1.toString(), matrixInput2.toString() };
                        out.writeObject(typeMatrixInput);

                        String matrixReceiverInput = (String) in.readObject();
                        System.out.println("Server response multiply: \n" + matrixReceiverInput);
                        break;
                        
                    case 3:
                    default:
                        System.out.println("Client Closed");

                        break;
                }

                client.close();
                break;

            } catch (Exception e) {
                System.out.println("Error " + e);
            }

        }
    };
}
