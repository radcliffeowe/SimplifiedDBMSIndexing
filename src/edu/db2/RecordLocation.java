package edu.db2;

public class RecordLocation implements Comparable<RecordLocation>{

    private int fileNumber;
    private int recordOffset;

    public RecordLocation(int fileNumber, int recordOffset) {
        this.fileNumber = fileNumber;
        this.recordOffset = recordOffset;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public int getRecordOffset() {
        return recordOffset;
    }

    public void setRecordOffset(int recordOffset) {
        this.recordOffset = recordOffset;
    }

    @Override
    public int compareTo(RecordLocation o){
        if(fileNumber > o.fileNumber){
            return 1;
        }
        else if(fileNumber == o.fileNumber){
            return 0;
        }
        else{
            return -1;
        }
    }
}
