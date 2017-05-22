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
    private final String filePath = "/Users/MacBookPro/SpringBatchRemotePartition/test.in";
    private FileReader in = null;
    private LineNumberReader reader = null;
    private int lines = 0;


    public synchronized String getNextLine(int currentIndex, int endIndex) {
        String s = null;

        // 每个parition对应一个SlaveReadRepository实例
        if (in == null) {
            try {
                in = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            reader = new LineNumberReader(in);
            try {
                s = reader.readLine();
                lines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            s = readLine(s, currentIndex);


        } else {
            try {
                s = reader.readLine();


                lines++;
            } catch (IOException e) {
                // reader已关闭，无法判断reader是否关闭，只能处理异常。
                // 重新打开一个reader，相当于每个partition都读了一遍文件

                lines = 0;
                try {
                    in = new FileReader(filePath);
                } catch (FileNotFoundException e1) {
                    e.printStackTrace();
                }
                reader = new LineNumberReader(in);
                try {
                    s = reader.readLine();
                    lines++;
                } catch (IOException e2) {
                    e.printStackTrace();
                }
                s = readLine(s, currentIndex);
            }
        }

        if (lines == endIndex || s == null) {
            try {
                reader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

    private String readLine(String s, int currentIndex) {
        if (lines != currentIndex) {
            while (s != null) {
                try {
                    s = reader.readLine();
                    lines++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (lines == currentIndex) {
                    break;
                }
            }
        }
        return s;
    }
}
