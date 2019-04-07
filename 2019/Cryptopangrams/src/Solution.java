import java.math.BigInteger;
import java.util.*;

public class Solution {
    public static final String FORMAT = "Case #%d: %s\n";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            String N = scanner.next();
            int len = scanner.nextInt();
            List<BigInteger> ciphertext = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                ciphertext.add(new BigInteger(scanner.next(), 10));
            }
            getAnswer(c, len, ciphertext);
        }
    }

    private static void getAnswer(int c, int len, List<BigInteger> ciphertext) {
        Set<BigInteger> alphbateSet = new HashSet<>();
        List<BigInteger> gcds = new ArrayList<>();
        List<BigInteger> plaintext = new ArrayList<>();
        for (int i = 0; i <= len; i++) {
            plaintext.add(new BigInteger("0"));
        }
        for (int i = 0; i < len-1; i++) {
            int j = i+1;
            BigInteger temp = gcd(ciphertext.get(i), ciphertext.get(j));
            if (!temp.equals(ciphertext.get(i)) && !temp.equals(ciphertext.get(j))) {
                gcds.add(temp);
                plaintext.set(i, ciphertext.get(i).divide(temp));
                plaintext.set(i+1, temp);
                plaintext.set(i+2, ciphertext.get(j).divide(temp));
                alphbateSet.add(ciphertext.get(i).divide(temp));
                alphbateSet.add(temp);
                alphbateSet.add(ciphertext.get(j).divide(temp));
            } else {
                gcds.add(BigInteger.ONE);
            }
        }

        BigInteger temp = new BigInteger("0");
        boolean found = false;
        for (int i = 0 ; i < gcds.size(); i++) {
            if (!found) {
                if (!gcds.get(i).equals(BigInteger.ONE)) {
                    found = true;
                    temp = gcds.get(i);
                }
            } else {
                if (gcds.get(i).equals(BigInteger.ONE)) {
                    temp = ciphertext.get(i).divide(temp);
                    gcds.set(i, temp);
                    plaintext.set(i, ciphertext.get(i).divide(temp));
                    plaintext.set(i+1, temp);
                    plaintext.set(i+2, ciphertext.get(i+1).divide(temp));

                    alphbateSet.add(ciphertext.get(i).divide(temp));
                    alphbateSet.add(temp);
                    alphbateSet.add(ciphertext.get(i+1).divide(temp));
                } else {
                    temp = gcds.get(i);
                }
            }
        }

        found = false;
        for (int i = gcds.size()-1 ; i >= 0; i--) {
            if (!found) {
                if (!gcds.get(i).equals(BigInteger.ONE)) {
                    found = true;
                    temp = gcds.get(i);
                }
            } else {
                if (gcds.get(i).equals(BigInteger.ONE)) {
                    temp = ciphertext.get(i+1).divide(temp);
                    gcds.set(i, temp);
                    plaintext.set(i, ciphertext.get(i).divide(temp));
                    plaintext.set(i+1, temp);
                    plaintext.set(i+2, ciphertext.get(i+1).divide(temp));

                    alphbateSet.add(ciphertext.get(i).divide(temp));
                    alphbateSet.add(temp);
                    alphbateSet.add(ciphertext.get(i+1).divide(temp));
                } else {
                    temp = gcds.get(i);
                }
            }
        }


        List<BigInteger> alphbate = new ArrayList<>(alphbateSet);
        Collections.sort(alphbate);

        Map<BigInteger, Integer> alphbateMap = new HashMap<>();
        int size = alphbate.size();
        for (int i = 0 ; i < size; i++) {
            alphbateMap.put(alphbate.get(i), i);
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < plaintext.size(); i++) {
            ans.append((char)(alphbateMap.get(plaintext.get(i))+'A'));
        }

        System.out.printf(FORMAT, c, ans.toString());
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.remainder(b));
    }

}
