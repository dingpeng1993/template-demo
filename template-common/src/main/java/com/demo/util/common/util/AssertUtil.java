package com.demo.util.common.util;

import com.demo.util.common.exception.CommonException;
import com.demo.util.common.object.Status;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author dp
 * @date 2020/3/15 2:22 下午
 */
public class AssertUtil {

    /**
     * @param obj 对象
     * @param status 对象
     */
    public static void assertNotNull(Object obj, Status status) {
        assertNotNull(obj, status.getStatus(), status.getCode(), status.getMsg());
    }

    public static void assertNotNull(Object obj, Status status, String... format) {
        assertNotNull(obj, status.getStatus(), status.getCode(), status.getMsg(format));
    }

    public static void assertNotNull(Object obj, int status, String code, String msg) {
        if (Objects.isNull(obj)) {
            throw new CommonException(status, code, msg);
        }
    }
    public static void assertNotBlank(String str, Status staus) {
        assertNotBlank(str, staus.getStatus(), staus.getCode(), staus.getMsg());
    }

    public static void assertNotBlank(String str, Status satus, String... format) {
        assertNotBlank(str, satus.getStatus(), satus.getCode(), satus.getMsg());
    }

    public static void assertNotBlank(String str, int status, String code, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new CommonException(status, code, msg);
        }
    }

    /**
     * @param collection 集合
     * @param status 状态
     */
    public static void assertNotEmpty(Collection collection, Status status) {
        assertNotEmpty(collection, status.getStatus(), status.getCode(), status.getMsg());
    }


    public static void assertNotEmpty(Collection collection, Status status, String... format) {
        assertNotEmpty(collection, status.getStatus(), status.getCode(), status.getMsg(format));
    }

    public static void assertEmpty(Collection collection, Status status) {
        assertEmpty(collection, status.getStatus(), status.getCode(), status.getMsg());
    }

    public static void assertEmpty(Collection collection, Status status, String... format) {
        assertEmpty(collection, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertNotEmpty(Collection collection, int status, String code, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CommonException(status, code, msg);
        }
    }


    public static void assertEmpty(Collection collection, int status, String code, String msg) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new CommonException(status, code, msg);
        }
    }


    public static void assertNotEmpty(Map map, Status status) {
        assertNotEmpty(map, status.getStatus(), status.getCode(), status.getMsg());
    }


    public static void assertNotEmpty(Map map, Status status, String... format) {
        assertNotEmpty(map, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertNotEmpty(Map map, int status, String code, String msg) {
        if (MapUtils.isEmpty(map)) {
            throw new CommonException(status, code, msg);
        }
    }



    public static void assertEq(Object a, Object b, Status status) {
        assertEq(a, b, status.getStatus(), status.getCode(), status.getMsg());
    }


    public static void assertEq(Object a, Object b, Status status, String... format) {
        assertEq(a, b, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertEq(Object a, Object b, int status, String code, String msg) {
        if (!Objects.equals(a, b)) {
            throw new CommonException(status, code, msg);
        }
    }

    public static void assertTrue(Boolean bool, Status status) {
        assertTrue(bool, status.getStatus(), status.getCode(), status.getMsg());
    }

    public static void assertTrue(Boolean bool, Status status, String... format) {
        assertTrue(bool, status.getStatus(), status.getCode(), status.getMsg(format));
    }

    public static void assertTrue(Boolean bool, int status, String code, String msg) {
        if (Objects.isNull(bool) || !bool) {
            throw new CommonException(status, code, msg);
        }
    }

    /**
     * 判断源数据是否大于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertGt(Integer origin, Integer target, Status status) {
        assertGt(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否大于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertGt(Integer origin, Integer target, Status status, String... format) {
        assertGt(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertGt(Integer origin, Integer target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin > target) {
            return;
        }
        throw new CommonException(status, code, msg);
    }

    /**
     * 判断源数据是否 大于等于 目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertGte(Integer origin, Integer target, Status status) {
        assertGte(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否大于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertGte(Integer origin, Integer target, Status status, String... format) {
        assertGte(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertGte(Integer origin, Integer target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin > target || origin.equals(target)) {
            return;
        }
        throw new CommonException(status, code, msg);
    }


    /**
     * 判断源数据是否小于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertLt(Integer origin, Integer target, Status status) {
        assertLt(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否小于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertLt(Integer origin, Integer target, Status status, String... format) {
        assertLt(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertLt(Integer origin, Integer target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin < target) {
            return;
        }
        throw new CommonException(status, code, msg);
    }

    /**
     * 判断源数据是否小于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertLte(Integer origin, Integer target, Status status) {
        assertLte(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否小于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertLte(Integer origin, Integer target, Status status, String... format) {
        assertLte(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertLte(Integer origin, Integer target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin < target || origin.equals(target)) {
            return;
        }
        throw new CommonException(status, code, msg);
    }

    /**
     * 判断源数据是否大于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertGt(Long origin, Long target, Status status) {
        assertGt(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否大于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertGt(Long origin, Long target, Status status, String... format) {
        assertGt(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertGt(Long origin, Long target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin > target) {
            return;
        }
        throw new CommonException(status, code, msg);
    }

    /**
     * 判断源数据是否 大于等于 目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertGte(Long origin, Long target, Status status) {
        assertGte(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否大于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertGte(Long origin, Long target, Status status, String... format) {
        assertGte(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertGte(Long origin, Long target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin > target || origin.equals(target)) {
            return;
        }
        throw new CommonException(status, code, msg);
    }


    /**
     * 判断源数据是否小于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertLt(Long origin, Long target, Status status) {
        assertLt(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否小于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertLt(Long origin, Long target, Status status, String... format) {
        assertLt(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertLt(Long origin, Long target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin < target) {
            return;
        }
        throw new CommonException(status, code, msg);
    }

    /**
     * 判断源数据是否小于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     */
    public static void assertLte(Long origin, Long target, Status status) {
        assertLte(origin, target, status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 判断源数据是否小于等于目标值
     *
     * @param origin 源数据
     * @param target 目标值
     * @param status 状态
     * @param format 字符串
     */
    public static void assertLte(Long origin, Long target, Status status, String... format) {
        assertLte(origin, target, status.getStatus(), status.getCode(), status.getMsg(format));
    }


    public static void assertLte(Long origin, Long target, int status, String code, String msg) {
        assertNotNull(origin, status, code, msg);
        assertNotNull(target, status, code, msg);
        if (origin < target || origin.equals(target)) {
            return;
        }
        throw new CommonException(status, code, msg);
    }
}
