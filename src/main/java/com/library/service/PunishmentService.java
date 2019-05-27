package com.library.service;

import com.library.bean.Punishment;
import com.library.dao.PunishmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PunishmentService {
    @Autowired
    private PunishmentDao punishmentDao;

    public ArrayList<Punishment> badList(){
        return punishmentDao.badList();
    }
    public ArrayList<Punishment> myBadList(long readerId){
        return punishmentDao.myBadList(readerId);
    }
}
