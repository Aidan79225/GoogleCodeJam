import java.util.*;

public class Solution {
    public static final String FORMAT = "Case #%d: %d %d\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            int num = scanner.nextInt();
            int max = scanner.nextInt();
            List<Line> vlines = new ArrayList<>();
            List<Line> hlines = new ArrayList<>();
            for (int i = 0; i < num;  i++) {
                int h = scanner.nextInt();
                int v = scanner.nextInt();
                switch (scanner.next()) {
                    case "N":
                        vlines.add(new Line(v, max));
                        break;
                    case "S":
                        vlines.add(new Line(0, v));
                        break;
                    case "W":
                        hlines.add(new Line(0, h));
                        break;
                    case "E":
                        hlines.add(new Line(h, max));
                        break;

                }
            }
            getAnswer(c, hlines, vlines);
        }
    }

    private static void getAnswer(int t, List<Line> hlines, List<Line> vlines) {
        List<Line> mergeV = mergeLines(vlines);
        List<Line> mergeH = mergeLines(hlines);
        Line maxV = getMax(mergeV);
        Line maxH = getMax(mergeH);
        System.out.printf(FORMAT, t, maxH.start, maxV.start);
    }

    private static List<Line> mergeLines(List<Line> source) {
        source.sort((t0, t1) -> {
            if (t0.start == t1.start) {
                return t0.end - t1.end;
            } else {
                return t0.start - t1.start;
            }
        });
        List<Line> ans = new ArrayList<>();
        for (Line line : source) {
            for (int i = 0; i < ans.size(); i++) {
                Line target = ans.get(i);
                if() {

                }
            }
            if (line.start < line.end) {

            }

        }


    }

    private static Line getMax(List<Line> source) {
        Line ans = source.get(0);
        for (Line line : source) {
            if (ans.count < line.count) {
                ans = line;
            }
        }
        return ans;
    }


    public static class Line {
        int start;
        int end;
        public int count = 1;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
