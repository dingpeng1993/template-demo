package com.demo.util.common.log;

import java.lang.annotation.*;

/**
 * @author dp
 * @date 2021/3/19 3:40 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogPoint {
}
