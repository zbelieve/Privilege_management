package com.Privilege_management.ssm.service;

import com.Privilege_management.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    void deleteById(String id) throws Exception;
}
