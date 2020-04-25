package com.bwie.dao;

import com.bwie.model.TBorrowingBook;
import com.bwie.model.TBorrowingBookExample;
import java.util.List;

import com.bwie.vo.BorrowingBookVo;
import org.apache.ibatis.annotations.Param;

public interface TBorrowingBookMapper {
    int countByExample(TBorrowingBookExample example);

    int deleteByExample(TBorrowingBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(TBorrowingBook record);

    int insertSelective(TBorrowingBook record);

    List<TBorrowingBook> selectByExample(TBorrowingBookExample example);

    TBorrowingBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TBorrowingBook record, @Param("example") TBorrowingBookExample example);

    int updateByExample(@Param("record") TBorrowingBook record, @Param("example") TBorrowingBookExample example);

    int updateByPrimaryKeySelective(TBorrowingBook record);

    int updateByPrimaryKey(TBorrowingBook record);

    List<BorrowingBookVo> queryBookBorrowingList(String bookId);
}