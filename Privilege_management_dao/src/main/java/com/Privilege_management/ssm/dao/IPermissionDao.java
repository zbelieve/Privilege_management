package com.Privilege_management.ssm.dao;

import com.Privilege_management.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //删除权限
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(String id) throws Exception;

    @Delete("delete from permission where id=#{id}")

    void deleteById(String id) throws Exception ;
    //根据角色id查询出对应的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    //查询permission的详情
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
