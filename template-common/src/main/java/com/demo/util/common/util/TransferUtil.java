package com.demo.util.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author dp
 * @date 2020/7/9 9:07 上午
 */
public class TransferUtil {
    /**
     * Collection类型转换为List
     *
     * @param collection 集合
     * @param function 单个转换函数
     * @param <T> 输出
     * @param <R> 输入
     * @return T类型的List
     */
    public static <T, R> List<R> toList(Collection<T> collection, Function<T,R> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Lists.newArrayList();
        }
        return collection.stream().map(function).distinct().collect(Collectors.toList());
    }

    /**
     * Collection转换为Map
     *
     * @param collection 集合
     * @param keyFunction key生成函数
     * @param valueFunction value 生成函数
     * @param <T> 集合对象类型
     * @param <R> map的key类型
     * @param <U> map的value类型
     * @return Map
     */
    public static <T, R, U> Map<R, U> toMap(Collection<T> collection, Function<T, R> keyFunction, Function<T, U> valueFunction) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream().collect(Collectors.toMap(keyFunction, valueFunction));
    }

    /**
     * 对象转换
     *
     * @param input 输入对象
     * @param output 输出对象
     * @param <T> 输入对象类型
     * @param <R> 输出对象类型
     * @return R
     */
    public static <T, R> R transfer(T input, R output) {
        if (Objects.isNull(input)) {
            return null;
        }
        return BeanUtil.copyProperties(output, input);
    }

    /**
     * 获取枚举类里面的一个值
     *
     * @param enumType 枚举类
     * @param function 枚举值转换函数
     * @param value 目标值
     * @param <T> 枚举类
     * @param <R> 目标类
     * @return 符合条件的枚举类
     */
    public static <T extends Enum<T>, R> T parseEnum(Class<T> enumType, Function<T, R> function, R value) {
        if (Objects.isNull(enumType) || Objects.isNull(value) || Objects.isNull(function)) {
            return null;
        }
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> Objects.equals(function.apply(e), value))
                .findFirst()
                .orElse(null);
    }
}
