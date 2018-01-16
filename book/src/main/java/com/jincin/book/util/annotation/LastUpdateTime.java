package com.jincin.book.util.annotation;

import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 当在持久化实体属性上添加该注解，表明让Hibernate在保存或更新该实体时，
 * 自动设置上最近更新时间，不需要用户对该属性进行管理
 */

@ValueGenerationType(generatedBy = LastModifyTimeGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface LastUpdateTime {
}