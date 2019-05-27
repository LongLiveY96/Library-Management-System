package com.library.controller;

import com.library.bean.Book;
import com.library.bean.ClassInfo;
import com.library.bean.Lend;
import com.library.bean.ReaderCard;
import com.library.service.BookService;
import com.library.service.ClassInfoService;
import com.library.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@MultipartConfig
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LendService lendService;
    @Autowired
    private ClassInfoService classInfoService;

    private Date getDate(String pubstr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(pubstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook() {
        ArrayList<ClassInfo> classInfo = classInfoService.getAllClass();
        ModelAndView modelAndView = new ModelAndView("admin_book_add");
        modelAndView.addObject("classInfo", classInfo);
        return modelAndView;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(String searchWord) {
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("admin_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/reader_querybook_do.html")
    public ModelAndView readerQueryBookDo(String searchWord) {
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("reader_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("reader_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/admin_books.html")
    public ModelAndView adminBooks() {
        ArrayList<Book> books = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("admin_books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/editClass.html")
    public ModelAndView editClass(HttpServletRequest request) {
        String classId = request.getParameter("classId");
        ClassInfo classInfo = classInfoService.getClassById(classId);
        ModelAndView modelAndView = new ModelAndView("admin_class_edit");
        modelAndView.addObject("classInf", classInfo);
        return modelAndView;
    }

    @RequestMapping("/class_edit_do.html")
    public String editClassDo(ClassInfo classInfo, RedirectAttributes redirectAttributes) {
        if (classInfoService.editClassInfo(classInfo)) {
            redirectAttributes.addFlashAttribute("succ", "分类修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "分类修改失败！");
        }
        return "redirect:/classList.html";
    }


    @RequestMapping("/class_add_do.html")
    public String classAdd(ClassInfo classInfo, RedirectAttributes redirectAttributes) {
        if (classInfoService.addClassInfo(classInfo)) {
            redirectAttributes.addFlashAttribute("succ", "分类添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "分类添加失败！");
        }
        return "redirect:/classList.html";
    }

    @RequestMapping("/deleteClass.html")
    public String deleteClass(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String classId = request.getParameter("classId");
        if (classInfoService.deleteClassInfo(classId)) {
            redirectAttributes.addFlashAttribute("succ", "分类删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "分类删除失败！");
        }
        return "redirect:/classList.html";
    }


    @RequestMapping("/book_add_do.html")
    public String addBookDo(String pubstr, Book book, MultipartFile bookImg, RedirectAttributes redirectAttributes) throws IOException {
        book.setPubDate(getDate(pubstr));
        if (bookService.addBook(book, bookImg)) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books.html";
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ArrayList<ClassInfo> classInfo = classInfoService.getAllClass();
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        modelAndView.addObject("classInfo", classInfo);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(String pubstr, Book book, MultipartFile bookImg, RedirectAttributes redirectAttributes) throws IOException  {
        book.setPubDate(getDate(pubstr));
        if (bookService.editBook(book, bookImg)) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
        }
        return "redirect:/admin_books.html";
    }

    @RequestMapping("/admin_book_detail.html")
    public ModelAndView adminBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/classList.html")
    public ModelAndView adminClassList() {
        ArrayList<ClassInfo> classInfo = classInfoService.getAllClass();
        ModelAndView modelAndView = new ModelAndView("admin_class_list");
        modelAndView.addObject("classInfo", classInfo);
        return modelAndView;
    }

    @RequestMapping("/reader_book_detail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/admin_header.html")
    public ModelAndView admin_header() {
        return new ModelAndView("admin_header");
    }

    @RequestMapping("/reader_header.html")
    public ModelAndView reader_header() {
        return new ModelAndView("reader_header");
    }

    @RequestMapping("/reader_books.html")
    public ModelAndView readerBooks(HttpServletRequest request) {
        ArrayList<Book> books = bookService.getAllBooks();
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ArrayList<Lend> myAllLendList = lendService.myLendList(readerCard.getReaderId());
        ArrayList<Long> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend.getBookId());
            }
        }
        ModelAndView modelAndView = new ModelAndView("reader_books");
        modelAndView.addObject("books", books);
        modelAndView.addObject("myLendList", myLendList);
        return modelAndView;
    }
}
