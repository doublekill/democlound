package com.jincin.book.controller;

import com.jincin.book.FeignClient.ProviderFeign;
import com.jincin.book.domain.po.Book;
import com.jincin.book.dao.support.Range;
import com.jincin.book.service.BookService;
import com.jincin.book.util.Result;
import com.jincin.book.util.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/******************************************
 * Created by dongwujing on 2017/7/3.
 *
 ******************************************/
@RestController
public class BookController {
    @Autowired private BookService bookService;
    @Autowired private ProviderFeign providerFeign;

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "均衡调用",response = Result.class)
    public Result String(){
        Result result = providerFeign.findAll();
        return ResultBuilder.success(result);
    }

    @PostMapping(value = "/book/save.json" )
    @ApiOperation(value = "book保存",response = Result.class)
    public Result save(
            @Validated @RequestBody Book book) {
        book= bookService.add(book);
        return ResultBuilder.success(book);
    }

    @PostMapping(value = "/book/modify.json" )
    @ApiOperation(value = "book更新",response = Result.class)
    public Result modify(
            @Validated @RequestBody Book book,
            BindingResult bindingResult) {
        book= bookService.modify(book);
        return ResultBuilder.success(book);
    }


    @GetMapping(value = "/book/delete.json" )
    @ApiOperation(value = "删除book",response = Result.class)
    public Result deleted(
            @RequestParam(required = true) String bookId) {
        bookService.delete(bookId);
        return ResultBuilder.success();

    }

    @GetMapping(value = "/book/info.json" )
    @ApiOperation(value = "查询书籍详情",response = Book.class)
    public Result info(
            @RequestParam(required = true) String bookId,
            @RequestParam(required = true) String bookName
    ) {
        Book book= bookService.info(bookId, bookName);
        return ResultBuilder.success(book);
    }

    @RequestMapping(value = "/book/simplePage.json" ,method = RequestMethod.GET)
    @ApiOperation(value = "简单数据分页",response = Book.class)
    public Result simplePage(
            @ApiParam(defaultValue ="1") @RequestParam Integer pageNo,
            @ApiParam(defaultValue = "10") @RequestParam Integer pageSize,
            @RequestParam(required = false) String key
    ) {
        Book book=new Book();
        book.setBookName(key);
        book.setAuthor(key);
        Page<Book> page= bookService.simplePage(pageNo,pageSize, book);
        return ResultBuilder.success(page);
    }

    @RequestMapping(value = "/book/page.json",method = RequestMethod.GET)
    @ApiOperation(value = "查询数据分页",response = Book.class)
    public Result page(
                     @RequestParam(required = false) String bookName,
                     @RequestParam(required = false) String author,
                     @ApiParam(defaultValue ="1") @RequestParam Integer pageNo,
                     @ApiParam(defaultValue = "10") @RequestParam Integer pageSize,
                     @RequestParam(required = false) String searchKey
    ) {
        pageNo = (pageNo!=null && pageNo!=0) ? pageNo : 1;
        pageSize = (pageSize!=null && pageSize!=0)? pageSize : 10;
        String sortField="";
        String sortType="";
        Page<Book> page= bookService.page(pageNo,pageSize,bookName,author,sortField,sortType, searchKey);
        return ResultBuilder.success(page);
    }

    @RequestMapping(value = "/book/pageByRange.json" ,method = RequestMethod.GET)
    @ApiOperation(value = "通过范围筛选进行分页",response = Book.class)
    public Result pageByRange(
            @ApiParam(defaultValue ="1") @RequestParam Integer pageNo,
            @ApiParam(defaultValue = "10") @RequestParam Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") String start,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") String end,
            @RequestParam(required = false) String author
    ) {
        Range<Book> dateCreatedRange = new Range<Book>("dateCreated",start,end);
        List<Range<Book>> list=new ArrayList<>();
        list.add(dateCreatedRange);
        Book book=new Book();
        book.setAuthor(author);
        //构建匹配器 可以用也可以不用
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                //忽略大小写
                .withIgnoreCase()
                //处理模糊查询 "%+key+%"
                .withMatcher("author",ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略属性
                .withIgnorePaths("memo");
        Page<Book> page= bookService.pageByRange(pageNo,pageSize,list,book,matcher );
        return ResultBuilder.success(page);
    }

    @RequestMapping(value = "/book/count.json" ,method = RequestMethod.GET)
    @ApiOperation(value = "统计数量",response = Result.class)
    public Result count(
//            @RequestBody Book book,//或者可以直接使用book对象
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String bookName
    ) {
        Book book=new Book();
        book.setAuthor(author);
        book.setBookName(bookName);
        //构建匹配器 可以用也可以不用
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                //忽略大小写
                .withIgnoreCase()
                //处理模糊查询 "%+key+%"
//                .withMatcher("author",match->match.contains())
                .withMatcher("author",ExampleMatcher.GenericPropertyMatchers.contains())
                ;
        Long count= bookService.count(Example.of(book,matcher));
        return ResultBuilder.success(count);
    }

    @RequestMapping(value = "/book/group.json" ,method = RequestMethod.GET)
    @ApiOperation(value = "分组数据",response = Result.class)
    public Result group(String merchantId,String storeId) {
        List<String > authors=new ArrayList<>();
//        authors.add("string");
        authors.add("wang");
        List list= bookService.group(merchantId,storeId,authors);
        return ResultBuilder.success(list);
    }

    @RequestMapping(value = "/book/top.json" ,method = RequestMethod.GET)
    public Result top(
            @RequestParam(required = false) String author) {
        List<Book> list= bookService.top(author);
        return ResultBuilder.success(list);
    }

    @RequestMapping(value = "/book/findOneBySql.json" ,method = RequestMethod.POST)
    @ApiOperation(value = "接口描述:原生sql查询单个对象",response = Result.class)
    public Result findOneBySql(String name) {
        Book book= bookService.findOneBySql(name);
        return ResultBuilder.success(book);
    }




}
