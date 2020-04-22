package com.Privilege_management.ssm.service.impl;

import com.Privilege_management.ssm.dao.IUserDao;
import com.Privilege_management.ssm.domain.Role;
import com.Privilege_management.ssm.domain.UserInfo;
import com.Privilege_management.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//加密操作指定的service
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {



    @Autowired
    private IUserDao userDao;
    //在springsecurity中进行配置加密文件，在这儿注入进来
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //查询用户详情//根据用户id值查询能够添加的角色
    public UserInfo findById(String id) throws Exception{
        return userDao.findById(id);
    }
    //添加role给users,user还没有关联的role角色有多个，那么就要将这多个一个个的拿出来然后给对应的userid的user进行添加
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {

        for(String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }
    //保存添加的用户,添加用户
    //在service层对密码进行加密
    public void save(UserInfo userInfo) throws Exception{
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //查询用户
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    //登录时候验证user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            //传入对应的username，然后从在dao从数据库中查询出封装好的UserInfo，这样就直接对这个bean对象进行操作
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        //userInfo对bean直接操作，userInfo.getStatus()是状态，0false，1是true
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述，因为上面就是User里面方法要求要传入list集合
    //userInfo.getRoles()返回的是一个role，也就是有几个权限角色描述，可能一种对应一个权限
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        //将下面角色描述封装到里面
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        //在这儿userInfo.getRoles()返回的是一个roles的list集合
        //将所具有的权限都添加进一个list，封装好返回
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }


}
