package edu.db2;

/**
 * Object to represent a record with its file number and byte offset to minimize I/O and CPU cost
 */
public class RecordLocation implements Comparable<RecordLocation>{

    private int fileNumber;   //file storing the record (virtual disk block)
    private int recordOffset; //byte offset of the record within the file

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
