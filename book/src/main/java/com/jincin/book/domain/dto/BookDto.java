package com.jincin.book.domain.dto;

/******************************************
 * Created by dongwujing on 2017/7/4.
 *
 ******************************************/
public class BookDto {
    private String author;
    private Double avgPrice;
    private int maxPrice;

    public BookDto() {
    }

    public BookDto(String author, Double avgPrice) {
        this.author = author;
        this.avgPrice = avgPrice;
    }

    public BookDto(String author, Double avgPrice, int maxPrice) {
        this.author = author;
        this.avgPrice = avgPrice;
        this.maxPrice = maxPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
