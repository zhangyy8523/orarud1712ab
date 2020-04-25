package com.bwie.service;

import com.bwie.dao.TBookMapper;
import com.bwie.dao.TBorrowingBookMapper;
import com.bwie.model.TBook;
import com.bwie.model.TBookExample;
import com.bwie.model.TBorrowingBook;
import com.bwie.model.TUser;
import com.bwie.utils.StaticFlag;
import com.bwie.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/22 14:50
 */
@Service
public class BookService {
    @Autowired
    TBookMapper tBookMapper ;

    @Autowired
    TBorrowingBookMapper tBorrowingBookMapper ;


    public List<BookVo> queryBookList(BookVo book) {
        List<BookVo> books = tBookMapper.queryBookList(book);
        return books;
    }

    /**
     * 完成借阅记录
     * @param user
     * @param bookId
     * @return
     */
    @Transactional
    public int lendBook(TUser user, String bookId) {
        //需要在借阅记录表中，设置字段
        if(null!=user){
            TBorrowingBook tBorrowingBook = new TBorrowingBook() ;
            tBorrowingBook.setId(StaticFlag.queryUUID()); //设置id主键
            tBorrowingBook.setBookId(bookId);
            tBorrowingBook.setBorrowingTime(new Date()); //书籍的借阅时间
            tBorrowingBook.setUserId(user.getId());
            //把当前的记录插入到TBorrowingBookmapper表中
            int insert = tBorrowingBookMapper.insert(tBorrowingBook);
            //修改状态跟浏览量
            int flag =   tBookMapper.updateFlagAndBorrowingCount(bookId);
            if(insert>0 &&flag>0 ){
                return  1;
            }
        }
        return  0 ;

    }

    @Transactional
    public int restorationBook(String bookId) {
        TBook tBook = new TBook();
        tBook.setBorrowingFlag("0");
        tBook.setId(bookId);
        return tBookMapper.updateByPrimaryKeySelective(tBook);// updateByPrimaryKeySelective 如果实体中某个字段不为空，则更新某个字段
    }

    @Transactional
    public int add(TBook tBook) {
        tBook.setId(StaticFlag.queryUUID());
        tBook.setCreateTime(new Date());
        tBook.setBorrowingFlag("0");//未借出
        tBook.setBorrowingCount((short)0);
        return tBookMapper.insert(tBook);
    }

    public TBook queryBookInfo(String id) {
        return tBookMapper.selectByPrimaryKey(id);
    }
}
