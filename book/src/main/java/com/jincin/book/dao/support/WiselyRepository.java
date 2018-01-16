
package com.jincin.book.dao.support;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/******************************************
 * Created by dongwujing on 2017/6/28.
 *
 ******************************************/
@NoRepositoryBean
public interface WiselyRepository<T, PK extends Serializable> extends JpaRepository<T, PK>,JpaSpecificationExecutor<T> {


		Page<T> queryByExampleWithRange(Example example, List<Range<T>> ranges, Pageable pageable);
	    
}
