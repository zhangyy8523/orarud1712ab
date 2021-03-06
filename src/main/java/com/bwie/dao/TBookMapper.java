package com.bwie.dao;

import com.bwie.model.TBook;
import com.bwie.model.TBookExample;
import java.util.List;

import com.bwie.vo.BookVo;
import org.apache.ibatis.annotations.Param;

public interface TBookMapper {
    int countByExample(TBookExample example);

    int deleteByExample(TBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(TBook record);

    int insertSelective(TBook record);

    List<TBook> selectByExample(TBookExample example);

    TBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TBook record, @Param("example") TBookExample example);

    int updateByExample(@Param("record") TBook record, @Param("example") TBookExample example);

    int updateByPrimaryKeySelective(TBook record);

    int updateByPrimaryKey(TBook record);

    List<BookVo> queryBookList(BookVo book);

    int updateFlagAndBorrowingCount(String bookId);

}