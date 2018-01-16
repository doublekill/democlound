package com.jincin.book.service.impl;

import com.jincin.book.Exception.LogicException;
import com.jincin.book.domain.dto.BookDto;
import com.jincin.book.domain.po.Book;
import com.jincin.book.dao.BookDao;
import com.jincin.book.dao.support.Range;
import com.jincin.book.service.BookService;
import com.jincin.book.util.HelpUtil;
import com.jincin.book.util.JpaPageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.jincin.book.util.SysConstants.OBJECT_DELETED_TRUE;

/******************************************
 * Created by dongwujing on 2017/7/3.
 *
 ******************************************/
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired private BookDao bookDao;
    @Autowired private EntityManager entityManager;
    /**
     * 保存或者更新book
     * @param book
     * @return
     */
    public Book add(Book book) {
//        String bookName= book.getBookName();
        return bookDao.save(book);
    }

    public Book modify(Book book) {
        //查询是否存在
        boolean exists= bookDao.exists(book.getBookId());
        if (!exists)throw new LogicException("null book","修改失败!");
        Book targetBook= bookDao.findOne(book.getBookId());
        BeanUtils.copyProperties(book,targetBook, HelpUtil.getNullPropertyNames(book));
        //添加其他业务逻辑
        return bookDao.saveAndFlush(targetBook);
    }


    /**
     * 状态 删除
     * @param bookId
     */
    public void delete(String bookId) {
        Book book= bookDao.findOne(bookId);
        Optional optional=Optional.of(book);
        if (optional.isPresent()){
            book.setIsDeleted(OBJECT_DELETED_TRUE);
            bookDao.saveAndFlush(book);
        }
    }

    /**
     * 查询
     * @param bookId
     * @param bookName
     * @return
     */
    public Book info(String bookId, String bookName) {
        Book book=new Book();
        book.setBookName(bookName);
        //通过example查询对象
        Book one= bookDao.findOne(Example.of(book));

        return bookDao.findOne(bookId);
    }



    /**
     * 分页
     * @param pageNo
     * @param pageSize
     * @param bookName
     * @param author
     * @param sortField
     * @param sortType
     * @param key
     * @return
     */
    public Page<Book> page(Integer pageNo, Integer pageSize, String bookName, String author, String sortField, String sortType, String key) {
        String merchantId="merchantId";
        String storeId="storeId";
        Pageable pageable = JpaPageUtil.buildPageRequest(pageNo,pageSize,sortField,sortType);

        Specification specification= new Specification<Book>() {
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("merchantId").as(String.class),merchantId));
                list.add(cb.equal(root.get("storeId").as(String.class),storeId));
                if (StringUtils.isNotBlank(bookName)) list.add(cb.like(root.get("bookName").as(String.class),  "%" + bookName + "%"));
                Predicate andPredicate=cb.and(list.toArray(new Predicate[list.size()]));

                if (StringUtils.isNotBlank(key)){
                    Predicate p1=cb.like(root.get("bookName").as(String.class),  "%" + key + "%");
                    Predicate p2=cb.like(root.get("memo").as(String.class),  "%" + key + "%");
                    Predicate p3= cb.or(p1,p2);//or的处理

                    andPredicate=cb.and(andPredicate,p3);//and的处理
                }
                query.where(andPredicate);
                //排序按照字段
                query.orderBy(cb.desc(root.get("isDeleted").as(Integer.class)));
                return query.getRestriction();
            }
        };

        Page<Book> page = bookDao.findAll(specification, pageable);
        Book book=new Book();
        book.setBookName("java");
        Page page1= bookDao.findAll(Example.of(book),pageable);
        return page;
    }

    /**
     * 简单分页
     * @param pageNo
     * @param pageSize
     * @param book
     * @return
     */
    public Page<Book> simplePage(Integer pageNo, Integer pageSize, Book book) {
        //构建匹配器 可以用也可以不用
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                //忽略大小写
                .withIgnoreCase()
                //处理左匹配 "%+key"
                .withMatcher("bookName", ExampleMatcher.GenericPropertyMatchers.startsWith())
                //处理模糊查询 "%+key+%"
                .withMatcher("author",ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略属性
                .withIgnorePaths("memo");
        //构建分页参数
        Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.DESC, "dateCreated"));
        Page<Book> page= bookDao.findAll(Example.of(book,matcher),pageable);
//        Page<Book> page= bookDao.findAll(Example.of(book),pageable);
        return page;
    }

    /**
     * 按范围分页查询
     * @param pageNo
     * @param pageSize
     * @param list
     * @param book
     * @param exampleMatcher
     * @return
     */
    public Page<Book> pageByRange(Integer pageNo, Integer pageSize, List<Range<Book>> list, Book book, ExampleMatcher exampleMatcher) {
        Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.DESC, "dateCreated"));
        Page<Book> page= bookDao.queryByExampleWithRange(Example.of(book,exampleMatcher),list,pageable);
        return page;
    }

    /**
     * 统计数量
     * @param of
     * @return
     */
    public Long count(Example<Book> of) {

        return bookDao.count(of);
    }

    /**
     * 分组
     * @param merchantId
     * @param storeId
     * @param authors
     */
    public List group(String merchantId, String storeId,List<String> authors) {
        //方式1  query1 做BookDto 分组结果封装    注释掉的query2是做分组结果导出为 Object[]
        CriteriaBuilder builder =entityManager.getCriteriaBuilder();
        //构建查询对象
        CriteriaQuery<BookDto> query1 = builder.createQuery(BookDto.class);
//        CriteriaQuery<Object[]> query2 = builder.createQuery(Object[].class);
        Root<Book> root1= query1.from(Book.class);
//        Root<Book> root2= query2.from(Book.class);
        //构建查询列
        query1.multiselect(
                root1.get("author").alias("author")
                , builder.avg(root1.get("price")).alias("avgPrice")
                ,builder.max(root1.get("price")).alias("maxPrice")
        );
//        query2.select(
//                        builder.array(
//                                root1.get("author"),
//                                builder.avg(root1.get("price"))
//                        )
//        );
        //构建条件
        query1.where(
                builder.and(
                        builder.equal(root1.get("merchantId"),merchantId)
                        ,builder.equal(root1.get("storeId"),storeId)
                        ,root1.get("author").in(authors)//in的形式构建sql -> author in ("zhang","dong")
                )
//                , builder.or(
//                        builder.equal(root1.get("merchantId"),merchantId)
//                        ,builder.equal(root1.get("storeId"),storeId)
//                )

        );
//        query2.where(
//                builder.and(
//                        builder.equal(root1.get("merchantId"),merchantId)
//                        ,builder.equal(root1.get("storeId"),storeId)
//                        ,root1.get("author").in(authors)
//                )
//        );

        query1.groupBy(root1.get("author"));//执行分组
//        query2.groupBy(root1.get("author"));//执行分组
        query1.orderBy(builder.desc(root1.get("dateCreated").as(Date.class)));//执行排序
        query1.having(builder.ge(builder.avg(root1.get("price")),1));//执行having
        //执行查询
        TypedQuery<BookDto> typedQuery1 = entityManager.createQuery(query1);
//        TypedQuery<Object[]> typedQuery2 = entityManager.createQuery(query2);
        List<BookDto> results = typedQuery1.getResultList();
//        List<Object[]> results2 = typedQuery2.getResultList();



        //方式2
        Specification specification=new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
//                and条件
                list.add(cb.equal(root.get("merchantId").as(String.class),merchantId));//等于条件
                list.add(cb.equal(root.get("storeId").as(String.class),storeId)); // 等于条件
                list.add(root.get("author").in(authors));//in 条件构造
                //分组
                query.where(list.toArray(new Predicate[list.size()]));
                //查询分组 的字段
                query.multiselect(
                        root.get("author").alias("author"),
                        cb.avg(root.get("price").as(Integer.class)).alias("avgPrice")
                );
                //分组类型
                query.groupBy(root.get("author").as(String.class));
                return query.getRestriction();
            }
        };
        //转换specification 为 criteriaQuery
        CriteriaQuery<BookDto> criteriaQuery = builder.createQuery(BookDto.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        specification.toPredicate(root, criteriaQuery, builder);
        //执行查询结果
        TypedQuery<BookDto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<BookDto> list = typedQuery.getResultList();
        return results;

    }

    public List<Book> top(String author) {
        Book book=new Book();
        book.setAuthor(author);
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "dateCreated"));
        Page<Book> page= bookDao.findAll(Example.of(book),pageable);
        return page.getContent();
    }

    public Book findOneBySql(String name) {
        String sql="select b.* from book b where b.book_name=?";
        Query query= entityManager.createNativeQuery(sql,Book.class);
        query.setParameter(1,name);
        Book b= (Book) query.getSingleResult();
        return b;
    }


}
