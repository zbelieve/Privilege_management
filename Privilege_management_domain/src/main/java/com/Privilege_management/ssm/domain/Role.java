package com.Privilege_management.ssm.domain;

import java.util.List;
//这个role，这个bean对象并不是和数据库名字对应起来，只是需要什么属性就将他封装成一个bean对象
//而数据库中为了使得耦合性低，一般采用中间表将他们连接，这时候在查询数据的时候，只要用框架再写一条额外的
//查询语句查询此属性即可
public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    //返回的是一个list，里面对应了多个权限描述，有一个admin的权限，有一个user权限
    private List<UserInfo> users;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
