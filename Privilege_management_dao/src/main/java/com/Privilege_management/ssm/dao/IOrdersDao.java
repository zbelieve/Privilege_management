package com.Privilege_management.ssm.dao;

import com.Privilege_management.ssm.domain.Member;
import com.Privilege_management.ssm.domain.Orders;
import com.Privilege_management.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    //这样可以更加灵活的进行查询
    @Results({
            //id=true,property指定一个主键，property对应的domain里面的实体，column对应的数据库的字段，根据他去数据库查表
            //通过resulut指定要查询的字段，property对应的是实体类的，column对应的是数据库的字段
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            //这个是通过外键查询的关联的product，需要在IProductDao里面再写一个方法去查询,这个productid是数据库对应的
            //javatype指的对应的是返回值，而此条语句根据productId来去数据库查，然后执行one后面的sql语句，他传给那边的语句一个perductId值
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;

    //根据id查询订单
    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    //这样可以更加灵活的进行查询
    @Results({
            //id=true,property指定一个主键
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            //这个是通过外键查询的关联的product，需要在IProductDao里面再写一个方法去查询
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select="com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            //many表示多对多
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many  = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;

}
