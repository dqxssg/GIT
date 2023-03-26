package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.ProductNameMapper;
import org.example.mapper.ProvinceMapper;
import org.example.pojo.Customer;
import org.example.pojo.ProductName;
import org.example.pojo.Province;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class ProductNameService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<ProductName> selectAllProductName() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        ProductNameMapper mapper = sqlSession.getMapper(ProductNameMapper.class);
        //调用方法
        List<ProductName> productNames = mapper.selectAllProductName();
        //释放资源
        sqlSession.close();
        return productNames;
    }


    /**
     * 添加
     *
     * @param productName
     */
    public void productNameAdd(ProductName productName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductNameMapper mapper = sqlSession.getMapper(ProductNameMapper.class);
        mapper.productNameAdd(productName);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param productName
     */
    public void productNameUpdate(ProductName productName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductNameMapper mapper = sqlSession.getMapper(ProductNameMapper.class);
        mapper.productNameupdate(productName);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void productNameDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductNameMapper mapper = sqlSession.getMapper(ProductNameMapper.class);
        mapper.productNamedeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
