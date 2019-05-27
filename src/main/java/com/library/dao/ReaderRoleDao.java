package com.library.dao;


import com.library.bean.ReaderRole;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRoleDao {

    private final static String NAMESPACE = "com.library.dao.ReaderRoleDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public ArrayList<ReaderRole> getAllRole() {
        List<ReaderRole> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllRole");
        return (ArrayList<ReaderRole>) result;
    }

    public ReaderRole getRoleById(final String roleId) {
        ReaderRole result = sqlSessionTemplate.selectOne(NAMESPACE + "getRoleById", roleId);
        return result;
    }

    public int editRole(final ReaderRole readerRole) {
        return sqlSessionTemplate.update(NAMESPACE + "editRole", readerRole);
    }

    public int deleteRole(final String roleId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteRole", roleId);
    }

    public int addRole(final ReaderRole readerRole) {
        return sqlSessionTemplate.insert(NAMESPACE + "addRole", readerRole);
    }
}
