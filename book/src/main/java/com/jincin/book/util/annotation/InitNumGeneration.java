package com.jincin.book.util.annotation;

import org.hibernate.tuple.GenerationTiming;

/**
 * 初始化数字
 */
public class InitNumGeneration extends AbstractNumGeneration<InitNumber> {
 
    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }
}
