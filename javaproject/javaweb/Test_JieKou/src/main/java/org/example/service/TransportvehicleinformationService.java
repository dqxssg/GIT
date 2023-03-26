package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.TransportvehicleinformationMapper;
import org.example.pojo.Transportvehicleinformation;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class TransportvehicleinformationService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Transportvehicleinformation> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransportvehicleinformationMapper mapper = sqlSession.getMapper(TransportvehicleinformationMapper.class);
        List<Transportvehicleinformation> transportvehicleinformations = mapper.selectAll();
        sqlSession.close();
        return transportvehicleinformations;
    }

    /**
     * 添加数据
     *
     * @param transportvehicleinformation
     */
    public void add(Transportvehicleinformation transportvehicleinformation) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransportvehicleinformationMapper mapper = sqlSession.getMapper(TransportvehicleinformationMapper.class);
        mapper.add(transportvehicleinformation);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改数据
     *
     * @param transportvehicleinformation
     */
    public void update(Transportvehicleinformation transportvehicleinformation) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransportvehicleinformationMapper mapper = sqlSession.getMapper(TransportvehicleinformationMapper.class);
        mapper.update(transportvehicleinformation);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     */
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransportvehicleinformationMapper mapper = sqlSession.getMapper(TransportvehicleinformationMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
