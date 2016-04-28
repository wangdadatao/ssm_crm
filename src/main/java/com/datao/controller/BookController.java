package com.datao.controller;

import com.datao.pojo.Book;
import com.datao.service.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/27.
 */

@Controller
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(value = "/book/list", method = RequestMethod.GET)
    public String showBookList(Model model) {
        System.out.println("访问了bookLIst");

        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @RequestMapping(value = "/book/{id:\\d+}", method = RequestMethod.GET)
    public String showIdBook(@PathVariable Integer id, Model model) {
        System.out.println("查看单个书籍的id为：" + id);
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "id";
    }

    @RequestMapping(value = "/book/up", method = RequestMethod.POST)
    public String upIdBook(Book book, Model model, RedirectAttributes redirectAttributes) {

        System.out.println(book.getWhy() + book.getId() + book.getTitle() + book.getAuthor());
        bookService.upBook(book);

        redirectAttributes.addFlashAttribute("messages", "修改成功！");
        model.addAttribute("book", book);
        return "redirect:/book/" + book.getId();
    }

}
