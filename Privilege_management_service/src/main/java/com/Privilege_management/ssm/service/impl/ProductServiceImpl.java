package com.Privilege_management.ssm.service.impl;

import com.Privilege_management.ssm.dao.IProductDao;
import com.Privilege_management.ssm.domain.Product;
import com.Privilege_management.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//事务
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void save(Product product){
            productDao.save(product);
    }
}
