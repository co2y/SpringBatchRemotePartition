package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by co2y on 17/05/2017.
 */
@Component
public class SlaveProcessor implements ItemProcessor<String, String> {
    public SlaveProcessor() {
    }

    @Override
    public String process(String item) {
        return item + " processed";
    }
}
