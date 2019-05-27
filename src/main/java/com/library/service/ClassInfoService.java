package com.library.service;

import com.library.bean.ClassInfo;
import com.library.dao.ClassInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassInfoService {
    @Autowired
    private ClassInfoDao classInfoDao;

    public ArrayList<ClassInfo> getAllClass() {
        return classInfoDao.getAllClass();
    }

    public boolean deleteClassInfo(String classId) {
        return classInfoDao.deleteClass(classId) > 0;
    }

    public ClassInfo getClassById(String classId) {
        return classInfoDao.getClassById(classId);
    }

    public boolean editClassInfo(ClassInfo classInfo) {
        return classInfoDao.editClass(classInfo)>0;
    }

    public boolean addClassInfo(ClassInfo classInfo) {
        return classInfoDao.addClass(classInfo) > 0;
    }
}
