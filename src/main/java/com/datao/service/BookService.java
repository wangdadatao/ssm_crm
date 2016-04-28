package com.datao.service;

import com.datao.mapper.BookMapper;
import com.datao.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/27.
 */
@Named
@Transactional
public class BookService {

    public BookService() {
        System.out.println("Bookservice构造方法");
    }

    @Inject
    private BookMapper bookMapper;

    public List<Book> findAll() {
        return bookMapper.findAll();
    }


    /**
     *根据id查找书籍
     * @param id
     * @return
     */
    public Book findBookById(Integer id) {
        return bookMapper.finById(id);
    }

    /**
     * 修改书籍
     * @param book
     */
    public void upBook(Book book) {
        bookMapper.upBook(book);
    }
}
