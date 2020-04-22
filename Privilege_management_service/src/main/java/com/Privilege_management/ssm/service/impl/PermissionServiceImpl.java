package com.Privilege_management.ssm.service.impl;

import com.Privilege_management.ssm.dao.IPermissionDao;
import com.Privilege_management.ssm.domain.Permission;
import com.Privilege_management.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }
    public void save(Permission permission) throws Exception{
        permissionDao.save(permission);
    }
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }
}
