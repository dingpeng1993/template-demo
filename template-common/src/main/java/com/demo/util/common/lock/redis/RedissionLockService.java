package com.demo.util.common.lock.redis;

import com.demo.util.common.lock.AbstractLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author dp
 * @date 2021/4/1 10:15 下午
 */
public class RedissionLockService extends AbstractLockService {

    private final RedissonClient redissonClient;

    public RedissionLockService(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        boolean success;
        try {
            RLock lock = redissonClient.getLock(lockKey);
            success = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            String msg = String.format("LOCK FAILED: key=%s||tryLockTime=%s||lockExpiredTime=%s", lockKey, waitTime, leaseTime);
            throw new IllegalStateException(msg, e);
        }
        return success;
    }

    @Override
    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if (lock != null) {
                lock.unlock();
            }
        } catch (Throwable e) {
            String msg = String.format("UNLOCK FAILED: key=%s||tryLockTime=%s||lockExpiredTime=%s", lockKey);
            throw new IllegalStateException(msg, e);
        }
    }
}
