package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.SiteMapper;
import org.example.pojo.Customer;
import org.example.pojo.Site;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class SiteService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Site> selectAllSite() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        //调用方法
        List<Site> sites = mapper.selectAllSite();
        //释放资源
        sqlSession.close();
        return sites;
    }


    /**
     * 添加
     *
     * @param site
     */
    public void siteAdd(Site site) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.siteAdd(site);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param site
     */
    public void siteUpdate(Site site) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.siteupdate(site);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void siteDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.sitedeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
