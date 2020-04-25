package com.bwie.vo;

import com.bwie.model.TBorrowingBook;
import lombok.Data;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/24 14:39
 */
@Data
public class BorrowingBookVo extends TBorrowingBook {
    private String loginName ;

    private String bookName ;
}
