package me.co2y.demo.springbatch.remotepartition;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by co2y on 17/05/2017.
 */
@Repository
public class SlaveWriterRepository {
    public void write(List<? extends String> list) {
        list.stream().map(record -> "Writing record: " + record).forEach(System.out::println);
    }
}
