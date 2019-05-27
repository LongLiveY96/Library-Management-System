package com.library.dao;

import com.library.bean.ClassInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassInfoDao {
    private final static String NAMESPACE = "com.library.dao.ClassInfoDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public ArrayList<ClassInfo> getAllClass() {
        List<ClassInfo> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllClass");
        return (ArrayList<ClassInfo>) result;
    }

    public ClassInfo getClassById(final String classId) {
        ClassInfo result = sqlSessionTemplate.selectOne(NAMESPACE + "getClassById", classId);
        return result;
    }

    public int editClass(final ClassInfo classInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editClass", classInfo);
    }

    public int deleteClass(final String classId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteClass", classId);
    }

    public int addClass(final ClassInfo classInfo) {
        return sqlSessionTemplate.insert(NAMESPACE + "addClass", classInfo);
    }
}
