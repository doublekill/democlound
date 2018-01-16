package com.jincin.book.util.annotation;

import org.hibernate.tuple.GenerationTiming;

/**
 * 设置创建时间
 */
public class CreateTimeGeneration extends AbstractTimeGeneration<CreateTime> {
 
    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }
}
