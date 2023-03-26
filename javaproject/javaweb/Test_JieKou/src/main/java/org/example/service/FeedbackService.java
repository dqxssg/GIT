package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.FeedbackMapper;
import org.example.pojo.Feedback;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class FeedbackService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Feedback> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FeedbackMapper mapper = sqlSession.getMapper(FeedbackMapper.class);
        List<Feedback> feedbacks = mapper.selectAll();
        sqlSession.close();
        return feedbacks;
    }

    /**
     * 添加数据
     *
     * @param feedback
     */
    public void add(Feedback feedback) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FeedbackMapper mapper = sqlSession.getMapper(FeedbackMapper.class);
        mapper.Add(feedback);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改数据
     *
     * @param feedback
     */
    public void update(Feedback feedback) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FeedbackMapper mapper = sqlSession.getMapper(FeedbackMapper.class);
        mapper.update(feedback);
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
        FeedbackMapper mapper = sqlSession.getMapper(FeedbackMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
