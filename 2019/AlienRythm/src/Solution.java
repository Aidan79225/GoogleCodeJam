import java.util.*;

public class Solution {

    public static final String FORMAT = "Case #%d: %d\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            int size = scanner.nextInt();
            List<String> source = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                source.add(scanner.next());
            }
            getAnswer(c, source);
        }
    }

    private static void getAnswer(int c, List<String> source) {
        int ans = 0;
        Trie root = new Trie(null);
        for (String s : source) {
            root.addString(s.length()-1, s);
        }
        traversal(root);
        System.out.printf(FORMAT, c, count(root));
    }

    private static void traversal(Trie root) {
        for (Character key : root.sub.keySet()) {
            traversal(root.sub.get(key));
        }
        root.cut();
    }

    private static int count(Trie root) {
        int ans = 0;
        for (Character key : root.sub.keySet()) {
            ans += root.sub.get(key).ans;
        }
        return ans;
    }


    static class Trie{
        Trie parent;
        int count = 0;
        int ans = 0;
        int maxLength = 0;
        Map<Character, Trie> sub = new HashMap<>();
        public Trie(Trie parent) {
            this.parent = parent;
        }

        public void addString (int i, String c) {
            count++;
            if (i < 0) {
                return;
            }
            maxLength = Math.max(maxLength, i);
            Trie trie = sub.get(c.charAt(i));
            if (trie == null) {
                trie = new Trie(this);
            }
            sub.put(c.charAt(i), trie);
            trie.addString(i-1, c);
        }

        public void cut() {
            if (parent == null) {
                return;
            }
            if (count >= 2) {
                count -= 2;
                ans += 2;
                parent.cut();
            }
        }
    }
}
