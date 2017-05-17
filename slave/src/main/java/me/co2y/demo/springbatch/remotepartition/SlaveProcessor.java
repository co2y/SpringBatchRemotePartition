package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by co2y on 17/05/2017.
 */
@Component
public class SlaveProcessor implements ItemProcessor<Text, Text> {
    public SlaveProcessor() {
    }

    @Override
    public Text process(Text item) {
        item.setLine(item.getLine() + " processed");
        return item;
    }
}
