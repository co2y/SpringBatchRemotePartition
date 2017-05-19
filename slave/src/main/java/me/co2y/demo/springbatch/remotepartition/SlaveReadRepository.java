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

        } else {
            try {
                s = reader.readLine();


                lines++;
            } catch (IOException e) {
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
                if (lines != currentIndex) {
                    while (s != null) {
                        try {
                            s = reader.readLine();
                            lines++;
                        } catch (IOException e3) {
                            e.printStackTrace();
                        }
                        if (lines == currentIndex) {
                            System.out.println(lines + s);
                            break;
                        }
                    }
                }


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
}
