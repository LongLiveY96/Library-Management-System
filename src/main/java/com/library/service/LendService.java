package com.library.service;

import com.library.bean.Lend;
import com.library.dao.LendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    @Autowired
    private LendDao lendDao;

    public boolean returnBook(long bookId, long readerId){
        return lendDao.returnBookOne(bookId, readerId)>0 && lendDao.returnBookTwo(bookId)>0;
    }

    public boolean lendBook(long bookId,long readerId){
        return lendDao.lendBookOne(bookId,readerId)>0 && lendDao.lendBookTwo(bookId)>0;
    }

    public boolean checkReader(long readerId) {
        return lendDao.checkReader(readerId) > 0;
    }

    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }
    public ArrayList<Lend> myLendList(long readerId){
        return lendDao.myLendList(readerId);
    }

    public int deleteLend(long serNum) {
        return lendDao.deleteLend(serNum);
    }

    public boolean doLend(long serNum) {
        return lendDao.doLend(serNum)>0;
    }

    public Lend selectOne(long serNum) {
        return lendDao.selectOne(serNum);
    }

    public boolean addBadList(Lend lend) {
        return lendDao.addBadList(lend)>0;
    }

    public boolean countBook(long readId) {
        return lendDao.countBook(readId)>0;
    }
}
