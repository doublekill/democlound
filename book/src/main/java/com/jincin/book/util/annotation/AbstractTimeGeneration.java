package com.jincin.book.util.annotation;

import org.hibernate.HibernateException;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.ValueGenerator;

import java.lang.annotation.Annotation;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 支持Java8新的时间包
 *
 * @see LocalDateTime
 * @see LocalDate
 * @see LocalTime
 */
public abstract class AbstractTimeGeneration<A extends Annotation> implements AnnotationValueGeneration<A> {
    protected ValueGenerator<?> generator;
 
    @Override
    public void initialize(A annotation, Class<?> propertyType) {
        if (LocalDateTime.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> LocalDateTime.now();
        } else if (LocalDate.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> LocalDate.now();
        } else if (LocalTime.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> LocalTime.now();
        } else if (java.sql.Date.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> new java.sql.Date(new Date().getTime());
        } else if (Time.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> new Time(new Date().getTime());
        } else if (Timestamp.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> new Timestamp(new Date().getTime());
        } else if (Date.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> new Date();
        } else if (Calendar.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                return calendar;
            };
        } else {
            throw new HibernateException("Unsupported property type for generator annotation " + annotation.getClass().getSimpleName());
        }
    }
 
    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }
 
    @Override
    public boolean referenceColumnInSql() {
        return false;
    }
 
    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}