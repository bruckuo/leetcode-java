package com.neu.x.leetcode.redisLock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:03
 */
@Slf4j
public class RedisLockServiceImpl {
    /**
     * 等待时间间隔，单位毫秒
     */
    private static final int DEFAULT_WAIT_INTERVAL = 100;
    /**
     * 过期时间，单位秒
     */
    private static final int DEFAULT_EXPIRE_TIME = 10;

    @Resource(name = "redisClient")
    private RedisStoreClient storeClient;

    /**
     * 加锁，未取得锁时等待
     * @param redisLock:
     * @return boolean
     */
    public boolean acquireLock(RedisLock redisLock) throws Exception {
        int expireTime = redisLock.getLockExpireTime() == 0 ? DEFAULT_EXPIRE_TIME : redisLock.getLockExpireTime();
        int waitInterval = redisLock.getWaitInterval() == 0 ? DEFAULT_WAIT_INTERVAL : redisLock.getWaitInterval();
        int timeout = redisLock.getTimeout() == 0 ? waitInterval * 5 : redisLock.getTimeout();
        log.info("redis请求添加key:category:{},key:{},expireTime:{},timeout:{}", redisLock.getCategory(),
                redisLock.getLockKey(), expireTime, timeout);
        redisLock.setLockValue(getUUID());
        boolean locked;
        try {
            while (timeout >= 0) {
                locked = this.storeClient.setnx(new StoreKey(redisLock.getCategory(), redisLock.getLockKey()),
                        redisLock.getLockValue(), expireTime);
                if (locked) {
                    log.info("redis添加key成功:category:{},key:{}", redisLock.getCategory(), redisLock.getLockKey());
                    return true;
                } else {
                    log.warn("redis添加key重复:category:{},key:{}", redisLock.getCategory(), redisLock.getLockKey());
                    timeout -= waitInterval;
                    // 当前线程休息一段时间 wait需要同步加锁才能使用，否则抛出异常
                    Thread.sleep(waitInterval);
                    // this.wait(waitInterval);
                }
            }
        } catch (Exception e) {
            log.error(MessageFormat.format("redis添加key异常:category:{0},key:{1}", redisLock.getCategory(),
                    redisLock.getLockKey()), e);
            throw e;
        }
        return false;
    }

    /**
     * 加锁,未枷锁直接返回
     * @param redisLock:
     * @return boolean
     */
    public boolean acquireLockUnWait(RedisLock redisLock) {
        int expireTime = redisLock.getLockExpireTime() == 0 ? DEFAULT_EXPIRE_TIME : redisLock.getLockExpireTime();
        log.info("redis请求添加key:category:{},key:{},expireTime:{}", redisLock.getCategory(),
                redisLock.getLockKey(), expireTime);
        boolean locked;
        redisLock.setLockValue(getUUID());
        try {
            locked = this.storeClient.setnx(new StoreKey(redisLock.getCategory(), redisLock.getLockKey()),
                    redisLock.getLockValue(), expireTime);
            if (locked) {
                log.info("redis添加key成功:category:{},key:{},expireTime:{}", redisLock.getCategory(),
                        redisLock.getLockKey(), redisLock.getLockExpireTime());
            }
        } catch (Exception e) {
            log.error(MessageFormat.format("redis添加key异常:category:{0},key:{1},expireTime:{2}",
                    redisLock.getCategory(), redisLock.getLockKey(), expireTime), e);
            throw e;
        }
        return locked;
    }

    /**
     * 解锁
     * @param redisLock:
     * @return boolean
     */
    public boolean releaseLock(RedisLock redisLock) {
        log.info("redis请求删除key:category:{},key:{}", redisLock.getCategory(), redisLock.getLockKey());
        boolean result;
        try {
            StoreKey storeKey = new StoreKey(redisLock.getCategory(), redisLock.getLockKey());
            String lockVal = storeClient.get(storeKey);
            // redis存储的值必须和上次存放的值相等才能删除
            if (!StringUtils.isEmpty(lockVal) && lockVal.equals(redisLock.getLockValue())) {
                result = this.storeClient.delete(storeKey);
                if (!result) {
                    log.warn(MessageFormat.format("redis未删除key:category:{0},key:{1}", redisLock.getCategory(),
                            redisLock.getLockKey()));
                } else {
                    log.info("redis删除key成功:category:{},key:{} success", redisLock.getCategory(), redisLock.getLockKey());
                }
            } else {
                result = true;
            }
        } catch (Exception e) {
            log.error(MessageFormat.format("redis删除key异常:category:{0},key:{1}", redisLock.getCategory(),
                    redisLock.getLockKey()), e);
            throw e;
        }
        return result;
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
