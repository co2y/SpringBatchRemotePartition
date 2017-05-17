package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

/**
 * Created by co2y on 17/05/2017.
 */
public class SampleIncrementer implements JobParametersIncrementer {
    @Override
    public JobParameters getNext(JobParameters parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return new JobParametersBuilder().addLong("run.id", 1024L).toJobParameters();
        }
        long id = parameters.getLong("run.id", 1L) + 1;
        return new JobParametersBuilder().addLong("run.id", id).toJobParameters();
    }
}
