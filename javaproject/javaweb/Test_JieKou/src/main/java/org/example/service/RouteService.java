package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.RouteMapper;
import org.example.pojo.Route;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class RouteService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Route> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RouteMapper mapper = sqlSession.getMapper(RouteMapper.class);
        List<Route> routes = mapper.selectAll();
        sqlSession.close();
        return routes;
    }

    /**
     * 添加数据
     *
     * @param route
     */
    public void add(Route route) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RouteMapper mapper = sqlSession.getMapper(RouteMapper.class);
        mapper.add(route);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改数据
     *
     * @param route
     */
    public void update(Route route) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RouteMapper mapper = sqlSession.getMapper(RouteMapper.class);
        mapper.update(route);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void deteleById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RouteMapper mapper = sqlSession.getMapper(RouteMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
