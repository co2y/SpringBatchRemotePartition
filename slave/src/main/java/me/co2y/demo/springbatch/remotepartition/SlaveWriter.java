package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by co2y on 17/05/2017.
 */
@Component
public class SlaveWriter implements ItemWriter<String> {
    private SlaveWriterRepository writerRepository;

    @Autowired
    public SlaveWriter(SlaveWriterRepository writerRepository) {
        super();
        this.writerRepository = writerRepository;
    }

    @Override
    public void write(List<? extends String> list) {

        writerRepository.write(list);
    }
}
