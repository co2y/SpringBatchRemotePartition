package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by co2y on 17/05/2017.
 */
@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath:slave-config.xml")
public class SlaveApplication {
    public static void main(String[] args) {
        SpringApplication.run(SlaveApplication.class);
    }
}
