package jp.ne.yonem.batch.tasklet;

import com.google.gson.Gson;
import jp.ne.yonem.common.mapper.WorkersMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MybatisTasklet {

    private final SqlSessionFactory mainSessionFactory;
    private final SqlSessionFactory subSessionFactory;

    @Autowired
    public MybatisTasklet(
            @Qualifier("mainSqlSessionFactory") SqlSessionFactory mainSessionFactory
            , @Qualifier("subSqlSessionFactory") SqlSessionFactory subSessionFactory
    ) {
        this.mainSessionFactory = mainSessionFactory;
        this.subSessionFactory = subSessionFactory;
    }

    @Transactional
    public void access() {
        var mainSqlSession = new SqlSessionTemplate(mainSessionFactory);
        var subSqlSession = new SqlSessionTemplate(subSessionFactory);

        try {
            var gson = new Gson();
            var list = mainSqlSession.getMapper(WorkersMapper.class).selectAll();
            System.out.println(gson.toJson(list));
            list = subSqlSession.getMapper(WorkersMapper.class).selectAll();
            System.out.println(gson.toJson(list));

        } catch (Exception ex) {
            ex.printStackTrace();
            mainSqlSession.rollback();
            subSqlSession.rollback();
        }
    }
}
