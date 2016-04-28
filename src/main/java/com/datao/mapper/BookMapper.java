package com.datao.mapper;

import com.datao.pojo.Book;

import javax.inject.Named;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/27.
 */

public interface BookMapper {

    List<Book> findAll();

    Book finById(Integer id);

    void upBook(Book book);
}
