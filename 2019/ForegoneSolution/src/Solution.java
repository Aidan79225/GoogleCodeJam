
import java.util.Scanner;

public class Solution {
    public static final String FORMAT = "Case #%d: %s %s\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            getAnswer(c, scanner.next());
        }
    }

    public static void getAnswer(int c, String res) {
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for (int i = 0; i < res.length(); i++) {
            char t = res.charAt(i);
            if (t == '4') {
                left.append(1);
                right.append(3);
            } else {
                left.append(0);
                right.append(t);
            }
        }
        int count = 0;
        while (left.charAt(count) == '0' ) {
            count++;
        }
        System.out.printf(FORMAT, c, left.substring(count), right.toString());
    }

}
