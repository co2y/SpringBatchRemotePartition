package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by co2y on 17/05/2017.
 */
@Component
public class SlaveReader implements ItemReader<String> {
    private SlaveReadRepository readRepository;
    private ExecutionContext executionContext;
    private int partitionEnd;

    @Autowired
    public SlaveReader(SlaveReadRepository readRepository) {
        super();
        this.readRepository = readRepository;
    }

    @BeforeStep
    public void storeExecutionContext(StepExecution stepExecution) {
        this.executionContext = stepExecution.getExecutionContext();
        this.partitionEnd = executionContext.getInt("partitionEnd");
    }

    @Override
    public String read(){
        int currentIndex = executionContext.getInt("currentIndex");
        String item = (currentIndex <= partitionEnd) ? currentIndex+"" : null;
        executionContext.putInt("currentIndex", currentIndex + 1);
        return item;
    }
}
