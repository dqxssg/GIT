package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.ProvinceMapper;
import org.example.pojo.Customer;
import org.example.pojo.Province;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class ProvinceService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    public List<Province> selectAllProvince() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        //调用方法
        List<Province> provinces = mapper.selectAllProvince();
        //释放资源
        sqlSession.close();
        return provinces;
    }


    /**
     * 添加
     * @param province
     */
    public void provinceAdd(Province province) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        mapper.provinceAdd(province);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     * @param province
     */
    public void provinceUpdate(Province province) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        mapper.provinceupdate(province);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     * @param id
     */
    public void provinceDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        mapper.provincedeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
