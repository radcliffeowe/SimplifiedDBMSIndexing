package edu.db2;

public class RecordLocation {

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
}
