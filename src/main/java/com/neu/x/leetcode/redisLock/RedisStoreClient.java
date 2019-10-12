package com.neu.x.leetcode.redisLock;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:02
 */
public interface RedisStoreClient {

    /**
     * 设置缓存
     * @param storeKey:
     * @param var2:
     * @param expireTime:
     * @return java.lang.Boolean
     */
    Boolean setnx(StoreKey storeKey, Object var2, int expireTime);

    /**
     * 获取缓存
     * @param storeKey:
     * @return T
     */
    <T> T get(StoreKey storeKey);

    /**
     * 删除缓存
     * @param storeKey:
     * @return java.lang.Boolean
     */
    Boolean delete(StoreKey storeKey);


}
