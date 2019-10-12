package com.neu.x.leetcode.redisLock.impl;

import com.neu.x.leetcode.redisLock.RedisStoreClient;
import com.neu.x.leetcode.redisLock.StoreKey;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:27
 * Copyright (C) 2018 MTDP
 * All rights reserved
 */
@Service
public class RedisStoreClientImpl implements RedisStoreClient {

    @Override
    public Boolean setnx(StoreKey storeKey, Object var2, int expireTime) {
        return null;
    }

    @Override
    public <T> T get(StoreKey storeKey) {
        return null;
    }

    @Override
    public Boolean delete(StoreKey storeKey) {
        return null;
    }
}
