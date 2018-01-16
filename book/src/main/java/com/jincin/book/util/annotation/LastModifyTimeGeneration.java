package com.jincin.book.util.annotation;
 
import org.hibernate.tuple.GenerationTiming;

/**
 * 设置最近更新时间
 */
public class LastModifyTimeGeneration extends AbstractTimeGeneration<LastUpdateTime> {
 
    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.ALWAYS;
    }
}