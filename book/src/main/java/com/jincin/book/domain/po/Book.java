package com.jincin.book.domain.po;

import com.jincin.book.domain.po.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/******************************************
 * Created by dongwujing on 2017/7/3.
 *书
 ******************************************/
@ApiModel(value = "书籍")
@Entity
public class Book  extends BaseModel{
    @Id
    @ApiModelProperty(value = "主键")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String bookId;
    @NotBlank(message = "书名不能为空")
    @ApiModelProperty(value = "书名")
    private String bookName;
    @ApiModelProperty(value = "备注")
    private String memo;
    @Range(min = 1,max = 100,message = "价格必须在1-100之间")
    @ApiModelProperty(value = "价格")
    private Integer price;
    @Transient
    private Double avgPrice;
    @NotBlank(message = "作者不能为空")
    @ApiModelProperty(value = "作者")
    private String author;


    @ApiModelProperty(value = "商家id")
    private String merchantId;
    @ApiModelProperty(value = "门店id")
    private String storeId;

    public Book() {
    }

    public Book(String author,Double avgPrice) {
        this.avgPrice = avgPrice;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }



    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
