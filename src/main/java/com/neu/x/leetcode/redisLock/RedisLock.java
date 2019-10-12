package com.neu.x.leetcode.redisLock;

import lombok.Data;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:04
 */
@Data
public class RedisLock {
    /**
     * 锁定时间
     */
    private int lockExpireTime;
    /**
     * 锁定的key
     */
    private String lockKey;
    /**
     * 设置的值
     */
    private String lockValue;
    /**
     * category，key前缀部分
     */
    private String category;
    /**
     * 如果需要等待时，间隔重试时间，单位为毫秒
     */
    private int waitInterval;

    /**
     * 等待超时时间，单位毫秒
     */
    private int timeout;

    public RedisLock(String category, String lockKey) {
        this.category = category;
        this.lockKey = lockKey;
    }
}
