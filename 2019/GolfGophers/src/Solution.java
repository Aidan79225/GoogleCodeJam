import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        while (t-- > 0) {
            try {
                new Solution(scanner, n, m);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public Solution(Scanner scanner, int n, int m) {
        int[] primes = new int[] {5, 7, 9, 11, 13, 16, 17};
        int[] remainder = new int[] {0, 0, 0, 0, 0, 0, 0};
        int[] others = new int[] {0, 0, 0, 0, 0, 0, 0};
        int[] modInverse = new int[] {0, 0, 0, 0, 0, 0, 0};
        int M = 1;
        int maxI = 0;
        while (M < m && maxI < primes.length && maxI < n) {
            M *= primes[maxI];
            maxI++;
        }

        for (int i = 0; i < maxI && i < n; i++) {
            System.out.println(getString(primes[i]));
            System.out.flush();

            int count = 0;
            for (int j = 0; j < 18; j++) {
                count += scanner.nextInt();
            }
            remainder[i] = count % primes[i];

            others[i] = M / primes[i];

            for (int j = 1; j < primes[i]; j++) {
                if ((others[i] * j) % primes[i] == 1) {
                    modInverse[i] = j;
                    break;
                }
            }
        }


        int ans = 0;
        for (int i = 0; i < maxI && i < n; i++) {
            ans += remainder[i] * others[i] * modInverse[i];
        }

        while (ans > M) {
            ans -= M;
        }

        System.out.println(ans);
        System.out.flush();
        int status = scanner.nextInt();
        if (status == -1) {
            throw new RuntimeException("fail");
        }

    }

    private String getString(int c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            sb.append(c).append(" ");
        }
        return sb.substring(0, sb.length()-1);
    }
}
