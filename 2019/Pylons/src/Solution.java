import java.util.*;

public class Solution {
    public static int[] dx = new int[]{-1, 0, 1};

    public static final String FORMAT = "Case #%d: %s\n";
    public static final String FORMAT2 = "%d %d\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            getAnswer(c, row, column);
        }
    }

    private static void getAnswer(int n, int row, int column) {
        boolean [][] used = new boolean[row][];
        int[][] unvisited = new int[row][];
        for (int i = 0 ; i < used.length; i++) {
            used[i] = new boolean[column];
            unvisited[i] = new int[column];
        }

        int target = row*column;
        List<Integer> ai = new ArrayList<>();
        List<Integer> aj = new ArrayList<>();
        for (int i = 0 ; i < row; i++) {
            ai.add(i);
        }
        for (int j = 0 ; j < column; j++) {
            aj.add(j);
        }
        Collections.shuffle(ai);
        Collections.shuffle(aj);
        for (int i : ai) {
            for (int j : aj) {
                if (dfs(used, unvisited, i, j, row, column, 1, target, n)) {
                    System.out.flush();
                    return;
                }
            }
        }
        System.out.printf(FORMAT, n, "IMPOSSIBLE");
        System.out.flush();
    }

    private static boolean dfs(boolean [][] used, final int[][] unvisited, int i, int j, int row, int column, int count, int target, int n) {
        if (used[i][j]) {
            return false;
        }
        if (count == target) {
            System.out.printf(FORMAT, n, "POSSIBLE");
            System.out.printf(FORMAT2, i+1, j+1);
            return true;
        }
        used[i][j] = true;
        for (int tx : dx) {
            for (int ty : dx) {
                int x = i + tx;
                int y = j + ty;
                if (x < 0) {
                    continue;
                }
                if (y < 0) {
                    continue;
                }
                if (x >= row) {
                    continue;
                }
                if (y >= column) {
                    continue;
                }
                unvisited[x][y]--;
            }
        }
        PriorityQueue<Vertex> p = new PriorityQueue<>(Comparator.comparingInt(vertex -> -unvisited[vertex.x][vertex.y]));

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (x == i) {
                    continue;
                }
                if (y == j) {
                    continue;
                }
                if (x - y == i - j) {
                    continue;
                }
                if (x + y == i + j) {
                    continue;
                }
                if (used[x][y]) {
                    continue;
                }
                p.offer(new Vertex(x, y));
            }
        }
        Vertex vertex;
        while ((vertex = p.poll()) != null) {
            int x = vertex.x;
            int y = vertex.y;
            if (dfs(used, unvisited, x, y, row, column, count+1, target, n)) {
                System.out.printf(FORMAT2, i+1, j+1);
                return true;
            }
        }

//        for (int x = 0; x < row; x++) {
//            for (int y = 0; y < column; y++) {
//                if (x == i) {
//                    continue;
//                }
//                if (y == j) {
//                    continue;
//                }
//                if (x - y == i - j) {
//                    continue;
//                }
//                if (x + y == i + j) {
//                    continue;
//                }
//
//                if (dfs(used, unvisited, x, y, row, column, count+1, target, n)) {
//                    System.out.printf(FORMAT2, i+1, j+1);
//                    return true;
//                }
//            }
//        }

        for (int tx : dx) {
            for (int ty : dx) {
                int x = i + tx;
                int y = j + ty;
                if (x < 0) {
                    continue;
                }
                if (y < 0) {
                    continue;
                }
                if (x >= row) {
                    continue;
                }
                if (y >= column) {
                    continue;
                }
                unvisited[x][y]++;
            }
        }

        used[i][j] = false;
        return false;
    }
    static class Vertex{
        public int x;
        public int y;
        public Vertex(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
