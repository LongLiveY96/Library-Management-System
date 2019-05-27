package com.library.dao;

import com.library.bean.Punishment;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PunishmentDao {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.library.dao.PunishmentDao.";

    public ArrayList<Punishment> badList() {
        List<Punishment> result = sqlSessionTemplate.selectList(NAMESPACE + "badList");
        return (ArrayList<Punishment>) result;
    }

    public ArrayList<Punishment> myBadList(final long readerId) {
        List<Punishment> result = sqlSessionTemplate.selectList(NAMESPACE + "myBadList", readerId);
        return (ArrayList<Punishment>) result;
    }
}
