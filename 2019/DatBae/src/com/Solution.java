package com;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int c = 1; c <= n; c++) {
            new Solution(scanner);
        }
    }


    public Solution(Scanner scanner) {
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int f = scanner.nextInt();
        char[][] ans = new char[f][];
        int count = 1;
        for (int i = 0; i < f; i++) {
            StringBuilder sb = new StringBuilder();
            char t = '0';
            int c = 0;
            for (int l = 0; l < n; l++) {
                sb.append(t);
                c++;
                if (c >= count) {
                    c = 0;
                    t = t == '0' ? '1' : '0';
                }
            }
            System.err.println(sb.toString());
            System.out.println(sb.toString());
            System.out.flush();
            ans[i] = scanner.next().toCharArray();
            count = (count<<1);
        }
        Set<Integer> ids = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ids.add(i);
        }

        for (int i = 0; i < n-b; i++) {
            int num = 0;
            for (int j = f-1; j >= 0; j--) {
                num = (num<<1);
                num += (ans[j][i] - '0');
            }
            ids.remove(num);
        }
        List<Integer> finalAns = new ArrayList<>(ids);
        Collections.sort(finalAns);
        StringBuilder s = new StringBuilder();
        for (Integer id : finalAns) {
            if (s.length()>0) {
                s.append(" ");
            }
            s.append(id);
        }
        System.out.println(s.toString());
        System.out.flush();
        int a = scanner.nextInt();
    }
}
