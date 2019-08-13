package com.neu.x.leetcode.convert;

/**
 * @description: Z 字形变换
 * @author: guojiang.xiong
 * @date: 2019-07-17
 * @time: 11:44
 */
public class Solution {
    /**
     * 时间复杂度：O(n) n为字符串s的长度
     * 
     * @param s:
     * @param numRows:
     * @return java.lang.String
     */
    private static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int len = Math.min(s.length(), numRows);
        String[] rows = new String[len];
        for (int i = 0; i < len; i++) {
            rows[i] = "";
        }
        int loc = 0;
        boolean down = false;
        for (int i = 0; i < s.length(); i++) {
            rows[loc] += s.substring(i, i + 1);
            if (loc == 0 || loc == numRows - 1) {
                down = !down;
            }
            if (down) {
                loc += 1;
            } else {
                loc += -1;
            }

        }
        StringBuilder sb = new StringBuilder();
        for (String row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "LEETCODEISHIRING";
        System.out.println(convert(str, 3));
    }
}
