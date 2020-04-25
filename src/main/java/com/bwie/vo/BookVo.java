package com.bwie.vo;

import com.bwie.model.TBook;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/23 9:06
 */
@Data
public class BookVo extends TBook {
    private Date borrowingNewTime ;

    private String flag ;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") //用于接收参数使用
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") //用于接收参数使用
    private Date endTime;
}
