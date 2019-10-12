package com.neu.x.leetcode;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-10
 * @time: 14:28
 */
public class HcgCalculate {

    public static String calculateHcg (double currentHcg, LocalDateTime startTime, LocalDateTime endTime) {
        double max = currentHcg * Math.pow(Math.pow(2, 1D/1.7D), Duration.between(startTime, endTime).toDays());
        double min = currentHcg * Math.pow(Math.pow(2, 1D/2.5D), Duration.between(startTime, endTime).toDays());
        return min + "~" + max;
    }
    public static void main(String[] args) {
        double hcg24 = 3949.29;
        double hcg = 37299.18;
        LocalDateTime startTime = LocalDateTime.of(2019, 9, 24, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, 10, 9, 0, 0);
        System.out.println(calculateHcg(hcg24, startTime, endTime));

    }
}
