package com.neu.x.leetcode.valiSymbol;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-07-19
 * @time: 11:40
 */
public class Solution {
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')');
    }};
    public static boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            if(stack.isEmpty() || map.containsKey(c)) {
                stack.push(c);
            } else if(map.get(stack.peek()).equals(c)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }

}
