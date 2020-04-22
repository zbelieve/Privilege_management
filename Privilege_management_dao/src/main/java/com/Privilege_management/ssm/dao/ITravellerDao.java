package com.Privilege_management.ssm.dao;

import com.Privilege_management.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    //直接从travller里面是查不出来的，因为传过来的是ordersId
    //因为有一张中间表关联着order_traveller.他两是通过这张中间表来联系的
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
