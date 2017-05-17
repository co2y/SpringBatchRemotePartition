package me.co2y.demo.springbatch.remotepartition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by co2y on 17/05/2017.
 */

@Component
public class FilePartitioner implements Partitioner {

    private int getCount() {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream("/Users/MacBookPro/SpringBatchRemotePartition/test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        try {
            byte[] c = new byte[2048];
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        int linenum;

        linenum = getCount();

        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

        int start = 1;
        int interval = linenum / gridSize;
        int end = start + interval - 1;

        for (int i = 0; i < gridSize && start <= linenum; ++i) {
            ExecutionContext value = new ExecutionContext();
            result.put("partition" + i, value);

            if (end > linenum) {
                end = linenum;
            }

            System.out.println(start);
            System.out.println(end);

            value.putInt("currentIndex", start);
            value.putInt("partitionEnd", end);
            value.putInt("partitionId", i);

            start += interval;
            end += interval;

        }

        return result;

    }
}
