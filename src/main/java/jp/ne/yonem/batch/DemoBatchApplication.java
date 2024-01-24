package jp.ne.yonem.batch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.ne.yonem.**.mapper")
public class DemoBatchApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(DemoBatchApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run();
    }
}
