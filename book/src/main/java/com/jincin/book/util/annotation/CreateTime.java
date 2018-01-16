package com.jincin.book.util.annotation;

import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 当在持久化实体属性上添加该注解，表明让Hibernate在保存该实体时，
 * 自动设置上创建时间，不需要用户对该属性进行管理
 */
 
@ValueGenerationType(generatedBy = CreateTimeGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateTime {
}