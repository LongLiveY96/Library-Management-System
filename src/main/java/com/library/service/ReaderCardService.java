package com.library.service;

import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import com.library.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardService {
    @Autowired
    private ReaderCardDao readerCardDao;

    public boolean addReaderCard(ReaderInfo readerInfo, String password, int roleId){
        return  readerCardDao.addReaderCard(readerInfo,password,roleId)>0;
    }
    public boolean updatePassword(long readerId, String password){
        return readerCardDao.resetPassword(readerId,password)>0;
    }

    public ReaderCard findReaderByReaderId(long readerId) {
        return readerCardDao.findReaderByReaderId(readerId);
    }

    public boolean deleteReaderCard(long readerId) {
        return readerCardDao.deleteReaderCard(readerId) > 0;
    }

    public int countBook(long readerId) {
        return readerCardDao.countBook(readerId);
    };
}
