package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.pojo.Customer;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class CustomerService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Customer> selectAllCustomer() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        //调用方法
        List<Customer> customers = mapper.selectAllCustomer();
        //释放资源
        sqlSession.close();
        return customers;
    }

    /**
     * 添加
     *
     * @param customer
     */
    public void customerAdd(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.customerAdd(customer);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param customer
     */
    public void customerUpdate(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.customerupdate(customer);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void customerDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.customerdeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
