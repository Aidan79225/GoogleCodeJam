import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            try {
                new Solution(scanner);
            } catch (Exception e) {
                return;
            }
        }
    }

    private Scanner scanner;
    private int count = 0;
    private boolean[] status = new boolean[1000000];

    Solution(Scanner scanner) throws Exception {
        this.scanner = scanner;
        int a = scanner.nextInt();
        int x = 500, y = 500;
        while (count < 1000) {
            inputAlways(x, y);

        }
    }

    private void inputAlways(int x, int y) throws Exception {
        while (!input(x, y));
    }

    private boolean input(int x, int y) throws Exception {
        count++;
        System.out.printf("%d %d\n", x, y);
        System.out.flush();
        isSolved(x, y);
        boolean ans = true;
        for (int i = x-1; i <= x + 1; i++) {
            for (int j = y-1; j <= y + 1; j++) {
                ans &= status[getIndex(i,j)];
            }
        }
        return ans;
    }

    private int getIndex(int x, int y) {
        return x + y*1000;
    }

    private void isSolved(int x, int y) throws Exception {
        int s1 = scanner.nextInt();
        int s2 = scanner.nextInt();
        System.err.printf("prepare = %d %d\n", s1, s2);
        if (s1 == 0 && s2 == 0) {
            System.err.println("Correct");
            throw new Exception();
        } else if (s1 == -1 && s2 == -1) {
            System.err.println("Wrong");
            throw new Exception();
        }
        status[getIndex(x,y)] = true;
    }
}
