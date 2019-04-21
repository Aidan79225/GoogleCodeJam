import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

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
        for (int i = 0 ; i < used.length; i++) {
            used[i] = new boolean[column];
        }

        int target = row*column;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (dfs(used, i, j, row, column, 1, target, n)) {
                    return;
                }

            }
        }
        System.out.printf(FORMAT, n, "IMPOSSIBLE");
    }

    private static boolean dfs(boolean [][] used, int i, int j, int row, int column, int count, int target, int n) {
        if (used[i][j]) {
            return false;
        }
        if (count == target) {
            System.out.printf(FORMAT, n, "POSSIBLE");
            System.out.printf(FORMAT2, i+1, j+1);
            return true;
        }
        used[i][j] = true;
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

                if (dfs(used, x, y, row, column, count+1, target, n)) {
                    System.out.printf(FORMAT2, i+1, j+1);
                    return true;
                }
            }
        }
        used[i][j] = false;
        return false;
    }
}
