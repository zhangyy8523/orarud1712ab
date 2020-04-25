package com.bwie.model;

import java.util.Date;

public class TBook {
    private String id;

    private String bookName;

    private String bookIntroduce;

    private String bookAuthor;

    private Short borrowingCount;

    private String borrowingFlag;

    private String picUrl;

    private Date createTime;

    private Long traffic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce == null ? null : bookIntroduce.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public Short getBorrowingCount() {
        return borrowingCount;
    }

    public void setBorrowingCount(Short borrowingCount) {
        this.borrowingCount = borrowingCount;
    }

    public String getBorrowingFlag() {
        return borrowingFlag;
    }

    public void setBorrowingFlag(String borrowingFlag) {
        this.borrowingFlag = borrowingFlag == null ? null : borrowingFlag.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTraffic() {
        return traffic;
    }

    public void setTraffic(Long traffic) {
        this.traffic = traffic;
    }
}