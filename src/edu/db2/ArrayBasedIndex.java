package edu.db2;

import java.util.LinkedList;
import java.util.Objects;

public class ArrayBasedIndex {

    private LinkedList<RecordLocation>[] arrayIndex;

    public ArrayBasedIndex() {
        this.arrayIndex = new LinkedList[5000];
    }

    public void addEntry(int randValue, RecordLocation recordLocation){
        LinkedList<RecordLocation> exists = arrayIndex[randValue-1];
        if(Objects.isNull(exists)){
            LinkedList<RecordLocation> entry = new LinkedList<>();
            entry.add(recordLocation);
            arrayIndex[randValue-1] = entry;
        }
        else{
            exists.add(recordLocation);
        }
    }

    public LinkedList<RecordLocation>[] getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(LinkedList<RecordLocation>[] arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
}
