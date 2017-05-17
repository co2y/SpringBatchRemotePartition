package me.co2y.demo.springbatch.remotepartition;

/**
 * Created by co2y on 17/05/2017.
 */
public class Text {
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    private String line;


    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    private int partitionId;

}
