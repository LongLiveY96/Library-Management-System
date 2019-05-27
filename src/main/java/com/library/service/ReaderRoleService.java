package com.library.service;


import com.library.bean.ReaderRole;
import com.library.dao.ReaderRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderRoleService {

    @Autowired
    private ReaderRoleDao readerRoleDao;

    public ArrayList<ReaderRole> getAllRole() {
        return readerRoleDao.getAllRole();
    }

    public boolean deleteRole(String roleId) {
        return readerRoleDao.deleteRole(roleId) > 0;
    }

    public ReaderRole getRoleById(String roleId) {
        return readerRoleDao.getRoleById(roleId);
    }

    public boolean editRole(ReaderRole readerRole) {
        return readerRoleDao.editRole(readerRole)>0;
    }

    public boolean addRole(ReaderRole readerRole) {
        return readerRoleDao.addRole(readerRole) > 0;
    }
}
