package com.library.service;

import com.library.bean.Book;
import com.library.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public ArrayList<Book> queryBook(String searchWord) {
        return bookDao.queryBook(searchWord);
    }

    public ArrayList<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public boolean matchBook(String searchWord) {
        return bookDao.matchBook(searchWord) > 0;
    }

    public boolean addBook(Book book, MultipartFile img) throws IOException {
        // 获取服务器的物理路径
        String path = "D:\\All my code\\ideaCode\\LibrarySystem-master\\src\\main\\webapp\\static\\uppic";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename = "";
        if (img.getSize() == 0) {
            filename = "default.jpg";
        }else {
            String name = img.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf("."));
            if (!(".jpg").equals(suffix) && !(".jpeg").equals(suffix) && !(".png").equals(suffix)) {
                return false;
            }
            String prefix = "img" + new Date().getTime();
            filename = prefix + suffix;
            FileOutputStream out = new FileOutputStream(new File(dir, filename));
            out.write(img.getBytes());
            out.close();
        }
        //给实体类Book添加封面图片路径
        book.setImgUrl("uppic/"+filename);
        return bookDao.addBook(book) > 0;
    }

    public Book getBook(Long bookId) {
        return bookDao.getBook(bookId);
    }

    public boolean editBook(Book book, MultipartFile img) throws IOException {
        // 获取服务器的物理路径
        String path = "D:\\All my code\\ideaCode\\LibrarySystem-master\\src\\main\\webapp\\static\\uppic";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename = "";
        //判断这次更改是否上传了图片
        if (img.getSize() == 0) {
            //判断现有图片是否为默认图片
            if(book.getImgUrl().contains("default")) {
                filename = "default.jpg";
            }else {
                //图片不变
                filename = book.getImgUrl().substring(book.getImgUrl().lastIndexOf("/") + 1);
            }
        }else {
            //得到文件名
            String name = img.getOriginalFilename();
            //得到后缀
            String suffix = name.substring(name.lastIndexOf("."));
            if (!(".jpg").equals(suffix) && !(".jpeg").equals(suffix) && !(".png").equals(suffix)) {
                return false;
            }
            String prefix = "img" + new Date().getTime();
            filename = prefix + suffix;
            FileOutputStream out = new FileOutputStream(new File(dir, filename));
            out.write(img.getBytes());
            out.close();
        }
        book.setImgUrl("uppic/"+filename);
        return bookDao.editBook(book) > 0;
    }

    public boolean deleteBook(Long bookId) {
        return bookDao.deleteBook(bookId) > 0;
    }

    public boolean checkBook(Long bookId) {
        return bookDao.checkBook(bookId) > 0;
    }

}
