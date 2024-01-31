package jp.ne.yonem.batch.tasklet;

import com.github.javafaker.Faker;
import jp.ne.yonem.common.entity.Worker;
import jp.ne.yonem.common.mapper.WorkersMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
@SpringJUnitConfig
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class MybatisTaskletTest {

    private final Faker faker = new Faker();
    private final MybatisTasklet sut;
    private final SqlSessionFactory mainSessionFactory;

    @Autowired
    public MybatisTaskletTest(
            @Qualifier("mainSqlSessionFactory") SqlSessionFactory mainSessionFactory
            , MybatisTasklet sut
    ) {
        this.mainSessionFactory = mainSessionFactory;
        this.sut = sut;
    }

    @Test
    @DisplayName("JSON変換タスクレット")
    void test1() {
        var workers = new ArrayList<Worker>();

        for (var i = 0; i < 3; i++) {
            var worker = new Worker(faker.funnyName().name(), 1);
            workers.add(worker);
        }
        var mainSqlSession = new SqlSessionTemplate(mainSessionFactory);
        var mapper = mainSqlSession.getMapper(WorkersMapper.class);
        mapper.bulkInsert(workers);
        System.out.println(mapper.selectAll());
        sut.access();
    }
}