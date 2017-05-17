package me.co2y.demo.springbatch.remotepartition;

import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Created by co2y on 17/05/2017.
 */
@Repository
public class SlaveReadRepository {
    private final String filePath = "/Users/MacBookPro/SpringBatchRemotePartition/test.txt";

    public synchronized String getLine(int num) {
        FileReader in = null;
        try {
            in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LineNumberReader reader = new LineNumberReader(in);
        String s = "";

        int lines = 0;
        while (s != null) {
            lines++;
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (lines == num) {
                break;
            }
        }
        try {
            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s.equals("") ? null : s;
    }
}
