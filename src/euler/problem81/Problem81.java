package euler.problem81;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Mihails Volkovs on 2015.01.12.
 */
public class Problem81 {

    public static final void main(String... args) throws IOException, URISyntaxException {
//        IntegerMatrix matrix = new IntegerMatrix("https://projecteuler.net/project/resources/p081_matrix.txt");
        IntegerMatrix matrix = new IntegerMatrix(
                "131, 673, 234, 103, 18" + "\n" +
                        "201, 96, 342, 965, 150" + "\n" +
                        "630, 803, 746, 422, 111" + "\n" +
                        "537, 699, 497, 121, 956" + "\n" +
                        "805, 732, 524, 37, 331");


        matrix.out();
    }

    public static class IntegerMatrix {

        private int[][] cells;

        public IntegerMatrix(String matrixSource) throws IOException, URISyntaxException {
            if (matrixSource.startsWith("http")) {
                matrixSource = IOUtils.toString(new URL(matrixSource).toURI());
            }

            String[] lines = matrixSource.split("\n");
            int row = 0;
            cells = new int[lines.length][];
            for (String line : lines) {
                String[] cellStrings = line.split(",\\s*");
                cells[row] = new int[cellStrings.length];
                int column = 0;
                for (String cell : cellStrings) {
                    cells[row][column] = Integer.parseInt(cell);
                    column++;
                }
                row++;
            }
        }

        public void out() {
            System.out.println("Matrix: ");
            for (int[] row : cells) {
                for (int cell : row) {
                    System.out.print(cell + ", ");
                }
                System.out.println();
            }
        }
    }


}
