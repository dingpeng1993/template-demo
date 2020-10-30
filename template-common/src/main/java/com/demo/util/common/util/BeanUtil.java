package com.demo.util.common.util;

import com.demo.util.common.exception.CommonException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author dp
 * @date 2020/3/15 3:52 下午
 */
public class BeanUtil {

    /**
     * 拷贝相同名称属性的值, 不同类型的属性会尝试转换
     *
     * @param desc 目标对象
     * @param orig 原始对象
     * @param <T>  对象类型
     * @return 目标对象
     */
    public static <T> T copyProperties(T desc, Object orig) {
        try {
            BeanUtils.copyProperties(orig, desc);
        } catch (Exception e) {
            throw new CommonException(e);
        }
        return desc;
    }

    /**
     * 新建结果对象，该方法由子类实现
     *
     * @return 结果对象
     */
    public static <T> T instance(Type type) {
        // 用RESULT泛型
        Class<T> clazz;
        if (type instanceof Class) {
            clazz = (Class<T>) type;
        }
        // 泛型
        else {
            clazz = (Class<T>) ((ParameterizedType) type).getRawType();
        }
        // 创建对象
        return instance(clazz);
    }

    /**
     * 创建clazz的实例
     *
     * @param clazz 类型信息
     * @return clazz实例
     */
    public static <T> T instance(Class<T> clazz) {
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (Exception e) {
            throw new CommonException(e);
        }
        return result;
    }

    /**
     * 取实现类的泛型数组
     *
     * @return 泛型数组
     */
    public static Type[] getGenericTypes(Class<?> clazz) {
        if (clazz.getSuperclass().getName().equals(Object.class.getName())) {
            throw new UnsupportedOperationException("this is a sub instance of Object");
        }

        Type type;
        do {
            // 取泛型参数
            type = clazz.getGenericSuperclass();
            clazz = clazz.getSuperclass();
        }
        // 从当前类向上，直到找到泛型类
        while (!(type instanceof ParameterizedType));
        // 取泛型集合
        return ((ParameterizedType) type).getActualTypeArguments();
    }
}
