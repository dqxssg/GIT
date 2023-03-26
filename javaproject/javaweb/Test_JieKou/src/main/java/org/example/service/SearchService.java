package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.taglibs.standard.tlv.JstlSqlTLV;
import org.example.mapper.SearchMapper;
import org.example.pojo.Search;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class SearchService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Search> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SearchMapper mapper = sqlSession.getMapper(SearchMapper.class);
        List<Search> searches = mapper.selectAll();
        sqlSession.close();
        return searches;
    }

    /**
     * 添加数据
     *
     * @param search
     */
    public void add(Search search) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SearchMapper mapper = sqlSession.getMapper(SearchMapper.class);
        mapper.add(search);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改数据
     *
     * @param search
     */
    public void update(Search search) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SearchMapper mapper = sqlSession.getMapper(SearchMapper.class);
        mapper.update(search);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SearchMapper mapper = sqlSession.getMapper(SearchMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
