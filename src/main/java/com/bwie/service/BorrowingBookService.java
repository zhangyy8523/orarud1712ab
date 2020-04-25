package com.bwie.service;

import com.bwie.dao.TBorrowingBookMapper;
import com.bwie.vo.BorrowingBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/24 14:38
 */
@Service
public class BorrowingBookService {

    @Autowired
    private TBorrowingBookMapper tBorrowingBookMapper ;

    public List<BorrowingBookVo> queryBookBorrowingList(String bookId) {
        return tBorrowingBookMapper.queryBookBorrowingList(bookId);
    }
}
