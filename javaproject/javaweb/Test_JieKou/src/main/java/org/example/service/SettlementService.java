package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.SettlementMapper;
import org.example.pojo.Customer;
import org.example.pojo.Settlement;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class SettlementService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Settlement> selectAllSettlement() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
        //调用方法
        List<Settlement> settlements = mapper.selectAllSettlement();
        //释放资源
        sqlSession.close();
        return settlements;
    }


    /**
     * 添加
     *
     * @param settlement
     */
    public void settlementAdd(Settlement settlement) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
        mapper.settlementAdd(settlement);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param settlement
     */
    public void settlementUpdate(Settlement settlement) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
        mapper.settlementupdate(settlement);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void settlementDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
        mapper.settlementdeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
