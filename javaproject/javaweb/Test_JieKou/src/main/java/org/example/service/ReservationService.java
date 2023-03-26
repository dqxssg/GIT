package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.ReservationMapper;
import org.example.pojo.Reservation;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class ReservationService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Reservation> seletAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        List<Reservation> reservations = mapper.selectAll();
        sqlSession.close();
        return reservations;
    }

    /**
     * 添加
     *
     * @param reservation
     */
    public void add(Reservation reservation) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        mapper.Add(reservation);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改数据
     *
     * @param reservation
     */
    public void update(Reservation reservation) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        mapper.update(reservation);
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
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
