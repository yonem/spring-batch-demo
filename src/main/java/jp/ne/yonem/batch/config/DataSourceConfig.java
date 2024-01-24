package jp.ne.yonem.batch.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "mainDataSource")
    @Primary
    public DataSource mainDataSource(Environment env) {
        return DataSourceBuilder.create()
                .url(env.getProperty("main.datasource.url"))
                .username(env.getProperty("main.datasource.username"))
                .password(env.getProperty("main.datasource.password"))
                .build();
    }

    @Bean(name = "mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(
            @Qualifier("mainDataSource") DataSource dataSource,
            ApplicationContext applicationContext) throws Exception {
        var sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:**/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "subDataSource")
    public DataSource subDataSource(Environment env) {
        return DataSourceBuilder.create()
                .url(env.getProperty("sub.datasource.url"))
                .username(env.getProperty("sub.datasource.username"))
                .password(env.getProperty("sub.datasource.password"))
                .build();
    }

    @Bean(name = "subSqlSessionFactory")
    public SqlSessionFactory subSqlSessionFactory(
            @Qualifier("subDataSource") DataSource dataSource,
            ApplicationContext applicationContext) throws Exception {
        var sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:**/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}