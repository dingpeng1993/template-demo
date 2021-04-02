package com.demo.util.common.lock;

/**
 * @author dp
 * @date 2021/4/1 10:06 下午
 */
public interface LockService {
    /**
     *  非阻塞获取锁
     *
     * @param lockKey 锁名称
     * @param waitTime 等待时间 单位ms
     * @return
     */
    boolean tryLock(String lockKey, long waitTime);

    /**
     * 非阻塞获取锁
     *
     * @param lockKey 锁名称
     * @param waitTime 获取锁的等待时间 单位ms
     * @param leaseTime 过期时间 单位ms
     * @return
     */
    boolean tryLock(String lockKey, long waitTime, long leaseTime);


    /**
     * 释放锁
     *
     * @param lockKey 锁名称
     */
    void unlock(String lockKey);
}
