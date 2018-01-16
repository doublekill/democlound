package com.jincin.book.util.annotation;

import org.hibernate.HibernateException;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.ValueGenerator;

import java.lang.annotation.Annotation;

/**
 *
 * @param <A>
 */
public abstract class AbstractNumGeneration<A extends Annotation> implements AnnotationValueGeneration<A> {
    protected ValueGenerator<?> generator;
 
    @Override
    public void initialize(A annotation, Class<?> propertyType) {
        if (Integer.class.isAssignableFrom(propertyType)) {
            generator = (session, owner) -> 0;
        }
        else if(Long.class.isAssignableFrom(propertyType)){
            generator = (session, owner) -> 0L;
        }
        else {
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