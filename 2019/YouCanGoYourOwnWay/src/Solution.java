import java.util.Scanner;

public class Solution {
    public static final String FORMAT = "Case #%d: %s\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            int size = scanner.nextInt();
            getAnswer(c, scanner.next());
        }
    }

    public static void getAnswer(int c, String res) {
        StringBuilder ans = new StringBuilder();
        int i = 0;
        final int len = res.length();
        while (i < len) {
            char t = res.charAt(i);
            ans.append(t == 'E' ? 'S' : 'E');
            i++;
        }
        System.out.printf(FORMAT, c, ans.toString());
    }
}
