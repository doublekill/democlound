package com.jincin.book.util.annotation;

import java.lang.annotation.*;

/**
 * excel 列注解
 * Created by dongwujing on 2017/6/13.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * 默认值
     * @return
     */
    String value() default "";

    /**
     *从1开始 序列
     * @return
     */
    int col() default 0;
}