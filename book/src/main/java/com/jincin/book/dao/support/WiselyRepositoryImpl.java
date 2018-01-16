
package com.jincin.book.dao.support;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/******************************************
 * Created by dongwujing on 2017/6/28.
 *
 ******************************************/
public class WiselyRepositoryImpl<T, PK extends Serializable> extends SimpleJpaRepository<T, PK> implements
        WiselyRepository<T, PK> {
    private final EntityManager entityManager;

    public WiselyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }



    @Override
    public Page<T> queryByExampleWithRange(Example example, List<Range<T>> ranges, Pageable pageable) {
        Specification<T> byExample = new ByExampleSpecification<>(example);
        Specification<T> byRanges = new ByRangeSpecification<>(ranges);
        return findAll(where(byExample).and(byRanges),pageable);
    }


}
