package com.bwie.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TBorrowingBook {
    private String id;

    private String bookId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm") //用于接收参数使用
    private Date borrowingTime;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public Date getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Date borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}