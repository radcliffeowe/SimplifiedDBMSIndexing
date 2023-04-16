package edu.db2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class to implement an Array-Based Index.
 * Uses an array of LinkedLists of RecordLocations to support multiple records having the same random value since it is not unique
 */
public class ArrayBasedIndex {

    private LinkedList<RecordLocation>[] arrayIndex;

    public ArrayBasedIndex() {
        this.arrayIndex = new LinkedList[5000];
    }

    /**
     * Method to add an entry to the index.
     * Checks if the index already has an entry for that random value. If it does not, then a new LinkedList is created and set to that index of the array
     * If an entry already exists, then the new entry is added to the LinkedList and the LinkedList is sorted by file number to make I/Os sequential
     * @param randValue is the random value attribute of the record
     * @param recordLocation is the RecordLocation object associated with the entry
     */
    public void addEntry(int randValue, RecordLocation recordLocation){
        LinkedList<RecordLocation> exists = arrayIndex[randValue-1];
        if(Objects.isNull(exists)){
            LinkedList<RecordLocation> entry = new LinkedList<>();
            entry.add(recordLocation);
            arrayIndex[randValue-1] = entry;
        }
        else{
            exists.add(recordLocation);
            Collections.sort(exists);
        }
    }

    public LinkedList<RecordLocation>[] getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(LinkedList<RecordLocation>[] arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
}
