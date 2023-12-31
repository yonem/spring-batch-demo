package jp.ne.yonem.batch.config;

import com.google.gson.Gson;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Arrays;

@Configuration
public class SimpleStringOutputBatchConfig {

    @Bean
    Step step1(JobRepository repository, DataSourceTransactionManager tx) {
        return new StepBuilder("chunk", repository)
                .<String, String>chunk(100, tx)
                .reader(new ListItemReader<>(Arrays.asList("Hello", "Spring", "Batch")))
                .processor(String::toUpperCase)
                .writer(chunk -> System.out.println(new Gson().toJson(chunk.getItems())))
                .build();
    }

    @Bean
    Step step2(JobRepository repository, DataSourceTransactionManager tx) {
        return new StepBuilder("tasklet", repository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello world!!");
                    return RepeatStatus.FINISHED;
                }, tx)
                .build();
    }

    @Bean
    Job job(JobRepository repository, Step step1, Step step2) {
        return new JobBuilder("job", repository)
                .start(step1)
                .next(step2)
                .build();
    }
}
