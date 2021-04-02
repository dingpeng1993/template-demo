package com.demo.util.common.lock;

/**
 * @author dp
 * @date 2021/4/1 10:14 下午
 */
public abstract class AbstractLockService implements LockService{
    @Override
    public boolean tryLock(String lockKey, long waitTime) {
        return tryLock(lockKey, waitTime, -1);
    }
}
