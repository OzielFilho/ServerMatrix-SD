
import java.util.Random;

public class Matrix {
    private final int[][] values;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new int[rows][columns];
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getValue(int row, int column) {
        return this.values[row][column];
    }

    public void setValue(int row, int column, int value) {
        this.values[row][column] = value;
    }

    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows) {
            throw new IllegalArgumentException("Cannot multiply matrices with incompatible sizes.");
        }

        Matrix result = new Matrix(this.rows, other.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                int sum = 0;

                for (int k = 0; k < this.columns; k++) {
                    sum += this.values[i][k] * other.values[k][j];
                }

                result.values[i][j] = sum;
            }
        }

        return result;
    }

    public void randomize(int min, int max) {
        Random rand = new Random();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.values[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                sb.append(this.values[i][j]);

                if (j != this.columns - 1) {
                    sb.append("\t");
                }
            }

            if (i != this.rows - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public static Matrix fromString(String str) {
        String[] rows = str.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].split("\t").length;
        Matrix matrix = new Matrix(numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            String[] values = rows[i].split("\t");
            for (int j = 0; j < numCols; j++) {
                matrix.setValue(i, j, Integer.parseInt(values[j]));
            }
        }

        return matrix;
    }
}
