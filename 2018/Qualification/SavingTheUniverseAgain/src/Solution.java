import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static final String IMPOSSIBLE = "IMPOSSIBLE";
    public static final String FORMAT = "Case #%d: %s\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Solution solution = new Solution();
        for (int c = 1; c <= n; c++) {
            int ans = solution.compute(scanner.nextInt(), scanner.next());
            if (ans == -1) {
                System.out.printf(FORMAT, c, IMPOSSIBLE);
            } else {
                System.out.printf(FORMAT, c, String.valueOf(ans));
            }
        }
    }

    private int compute(int target, String instruction) {
        List<Boolean> commands = new ArrayList<>();


        for (char c : instruction.toCharArray()) {
            commands.add(c == 'C');
        }

        int c = 0;
        while (getValue(commands) > target) {
            if (swapCommand(commands)) {
                return -1;
            }
            c++;
        }
        return c;
    }

    private boolean swapCommand(List<Boolean> commands) {
        for (int i = commands.size()-1; i > 0; i--) {
            if (!commands.get(i) && commands.get(i-1)) {
                Collections.swap(commands, i, i-1);
                return false;
            }
        }
        return true;
    }

    private int getValue(List<Boolean> commands) {
        int v = 1;
        int ans = 0;
        for (boolean com : commands) {
            if (com) {
                v <<= 1;
            } else {
                ans += v;
            }
        }
        return ans;
    }

}
