package jp.ne.yonem.batch.config;

import jp.ne.yonem.batch.tasklet.MybatisTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MybatisJobConfig {

    private final MybatisTasklet tasklet;

    @Autowired
    public MybatisJobConfig(MybatisTasklet tasklet) {
        this.tasklet = tasklet;
    }

    @Bean
    Step step(JobRepository repository, DataSourceTransactionManager tx) {
        return new StepBuilder("access", repository)
                .tasklet((contribution, chunkContext) -> {
                    tasklet.access();
                    return RepeatStatus.FINISHED;
                }, tx)
                .build();
    }

    @Bean
    Job job(JobRepository repository, Step step1, Step step2) {
        return new JobBuilder("MybatisBatchJob", repository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }
}
