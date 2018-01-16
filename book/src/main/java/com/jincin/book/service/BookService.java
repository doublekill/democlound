package com.jincin.book.service;

import com.jincin.book.domain.po.Book;
import com.jincin.book.dao.support.Range;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/******************************************
 * Created by dongwujing on 2017/7/3.
 *
 ******************************************/
public interface BookService {
    /**
     * 保存或者更新
     * @param book
     */
     Book add(Book book);

     Book  modify(Book book);
    /**
     * 删除
     * @param bookId
     */
    void delete(String bookId);

    /**
     * 查询信息
     * @param bookId
     * @param bookName
     * @return
     */
    Book info(String bookId, String bookName);

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
    Page<Book> page(Integer pageNo, Integer pageSize, String bookName, String author, String sortField, String sortType, String key);

    /**
     * 简单分页
     * @param pageNo
     * @param pageSize
     * @param book
     * @return
     */
    Page<Book> simplePage(Integer pageNo, Integer pageSize, Book book);

    /**
     * 通过范围查询分页
     * @param pageNo
     * @param pageSize
     * @param list
     * @param book
     * @param exampleMatcher
     * @return
     */
    Page<Book> pageByRange(Integer pageNo, Integer pageSize, List<Range<Book>> list, Book book, ExampleMatcher exampleMatcher);

    /**
     * 统计数量
     * @param of
     * @return
     */
    Long count(Example<Book> of);

    /**
     * 分组统计
     * @param merchantId
     * @param storeId
     */
    List group(String merchantId, String storeId, List<String> authors);

    /**
     * 查询作者前10本最新出的书籍
     * @param author
     * @return
     */
    List<Book> top(String author);

    /**
     * 通过原生sql查询
     * @param name
     * @return
     */
    Book findOneBySql(String name);
}
