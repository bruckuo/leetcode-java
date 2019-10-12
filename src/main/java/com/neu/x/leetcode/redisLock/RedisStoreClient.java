package com.neu.x.leetcode.redisLock;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:02
 */
public interface RedisStoreClient {

    Boolean setnx(StoreKey storeKey, Object var2, int expireTime);

    <T> T get(StoreKey storeKey);

    Boolean delete(StoreKey storeKey);


}
