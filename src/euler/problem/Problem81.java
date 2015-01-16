package euler.problem;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Mihails Volkovs on 2015.01.12.
 */
public class Problem81 {

    private static int completePaths = 0;

    private static int minimumTotal = Integer.MAX_VALUE;

    private static PathNode result = null;

    public static final void main(String... args) throws IOException, URISyntaxException {
        IntegerMatrix matrix = new IntegerMatrix("https://projecteuler.net/project/resources/p081_matrix.txt");
//        IntegerMatrix matrix = new IntegerMatrix(
//                "131, 673, 234, 103, 18" + "\n" +
//                        "201, 96, 342, 965, 150" + "\n" +
//                        "630, 803, 746, 422, 111" + "\n" +
//                        "537, 699, 497, 121, 956" + "\n" +
//                        "805, 732, 524, 37, 331");
        matrix.out();
        IntegerMatrix paths = new IntegerMatrix(matrix.rowsCount(), Integer.MAX_VALUE);
        paths.out();

        generateNext(new PathNode(matrix, 0, 0, null), paths);

        System.out.println("Generated paths: " + completePaths);
        System.out.println("The shortest path is: " + result.getTotal());
        System.out.println("The shortest path is: " + result.toString());
    }

    private static void generateNext(PathNode node, IntegerMatrix paths) {

        if (paths.get(node.row, node.column) <= node.getTotal()) {
            // already generated shorter path at that point - break this sequence
            return;
        } else {
            paths.set(node.row, node.column, node.getTotal());
        }

                PathNode nextNode1 = node.getNextHorizontally();
        if (nextNode1 != null) {
            generateNext(nextNode1, paths);
        }

        PathNode nextNode2 = node.getNextVertically();
        if (nextNode2 != null) {
            generateNext(nextNode2, paths);
        }

        // path completed - adding to the list
        if (nextNode1 == null && nextNode2 == null) {
            completePaths++;
            if (node.getTotal() < minimumTotal) {
                minimumTotal = node.getTotal();
                result = node;
            }
            if (completePaths % 10000000 == 0) {
                System.out.println("Completed paths: " + completePaths);
                System.out.println("Recent path is: " + node.toString());
                System.out.println("Current result is: " + result.toString());
                System.out.println("Current result is: " + minimumTotal);
            }
        }
    }

    public static class PathNode implements Comparable<PathNode> {
        private IntegerMatrix matrix;
        private int row;
        private int column;
        private PathNode previous;
        private int total;
        private int value;

        public PathNode(IntegerMatrix matrix, int row, int column, PathNode previous) {
            this.matrix = matrix;
            this.row = row;
            this.column = column;
            this.previous = previous;
            int previousTotal = previous == null ? 0 : previous.getTotal();
            this.value = matrix.get(row, column);
            this.total = previousTotal + value;
        }

        public PathNode getNextHorizontally() {
            int nextColumn = column + 1;
            if (nextColumn >= matrix.columnsCount()) {
                return null;
            }
            return new PathNode(matrix, row, nextColumn, this);
        }

        public PathNode getNextVertically() {
            int nextRow = row + 1;
            if (nextRow >= matrix.rowsCount()) {
                return null;
            }
            return new PathNode(matrix, nextRow, column, this);
        }

        public int getTotal() {
            return total;
        }

        @Override
        public int compareTo(PathNode pathNode) {
            return Long.compare(total, pathNode.total);
        }

        @Override
        public String toString() {
            return (previous == null ? "" : previous.toString()) + String.format("[%s, %s] (%s), ", row, column, value);
        }
    }

    public static class IntegerMatrix {

        private int[][] cells;

        public IntegerMatrix(int size, int initValue) throws IOException, URISyntaxException {
            cells = new int[size][size];
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    cells[row][column] = initValue;
                }
            }
        }

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

        public int get(int row, int column) {
            return cells[row][column];
        }

        public void set(int row, int column, int value) {
            cells[row][column] = value;
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

        public int rowsCount() {
            return cells.length;
        }

        public int columnsCount() {
            return cells.length == 0 ? 0 : cells[0].length;
        }
    }


}
