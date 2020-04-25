package com.bwie.controller;

import com.bwie.service.BorrowingBookService;
import com.bwie.vo.BorrowingBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @描述：图书借阅记录表
 * @作者：zhangyuyang
 * @日期：2020/4/24 14:31
 */
@Controller
@RequestMapping("/borro")
public class BorrowiingBookController {
    @Autowired
    BorrowingBookService borrowingBookService;

    @RequestMapping("/queryBookBorrowingList")
    @ResponseBody
    public  List<BorrowingBookVo> queryBookBorrowingList(String bookId){
       List<BorrowingBookVo> list =  borrowingBookService.queryBookBorrowingList(bookId);

        return list;
    }


}
