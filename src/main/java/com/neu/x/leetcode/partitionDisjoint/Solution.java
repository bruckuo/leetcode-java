package com.neu.x.leetcode.partitionDisjoint;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-08-13
 * @time: 10:32
 * Copyright (C) 2018 MTDP
 * All rights reserved
 */
public class Solution {

    public static int partitionDisjoint(int[] array) {
        int i = 0;
        int len = array.length;
        int j = len - 1;
        int k = 0;
        while (k < j) {
            while (array[i] <= array[j]){
                j--;
                if(j == k){
                    return k + 1;
                }
            }
            while (k < j){
                if(array[i] < array[k]){
                    i = k;
                }
                k++;
            }
            j = len - 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = new int[]{66,77,88,99,111,222,333,444,1,2,4,5,6,12,21,23,33,44,55};
//        int i = 0;
//        int end = a.length;
//        while (i < end) {
//            if (a[i] <= a[i + 1]) {
//                i++;
//            }
//            if (a[i] > a[++i]) {
//                System.out.println(a[i++]);
//                return;
//            }
//        }

          int min = 0;
          int max = a.length - 1;
          int mid;
          int key = a[0];
          while (min <= max) {
              mid = (min + max) / 2;
              if (a[mid] > key && a[mid + 1] > key) {
                  min = mid + 1;
              }
              if (a[mid] <= key && a[mid - 1] <= key) {
                  max = mid - 1;
              }
              if (a[mid] > key && a[mid + 1] < key) {
                  System.out.println(a[mid + 1]);
                  return;
              }
          }

        System.out.println(partitionDisjoint(a));
    }
}
