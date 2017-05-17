package me.co2y.demo.springbatch.remotepartition;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Created by co2y on 17/05/2017.
 */
@Repository
public class SlaveWriterRepository {
    private final String writeFilePath = "/Users/MacBookPro/SpringBatchRemotePartition/test.txt";

    public void write(List<? extends Text> list) {
        String fileName = writeFilePath + list.get(0).getPartitionId();
        StringBuilder sb = new StringBuilder();
        for (Text text : list) {
            sb.append(text.getLine());
            sb.append("\n");
        }
        try {
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(sb.toString());
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
