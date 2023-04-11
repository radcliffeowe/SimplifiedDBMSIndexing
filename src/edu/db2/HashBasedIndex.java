package edu.db2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class HashBasedIndex {

    private HashMap<Integer, LinkedList<RecordLocation>> hashIndex;


    public HashBasedIndex() {
        this.hashIndex = new HashMap<>();
    }

    public void addEntry(int randValue, RecordLocation recordLocation){
        LinkedList<RecordLocation> exists = hashIndex.get(randValue);
        if(Objects.isNull(exists)){
            LinkedList<RecordLocation> entry = new LinkedList<>();
            entry.add(recordLocation);
            hashIndex.put(randValue, entry);
        }
        else{
            exists.add(recordLocation);
            //hashIndex.put(randValue, exists);
        }
    }

    public HashMap<Integer, LinkedList<RecordLocation>> getHashIndex() {
        return hashIndex;
    }

    public void setHashIndex(HashMap<Integer, LinkedList<RecordLocation>> hashIndex) {
        this.hashIndex = hashIndex;
    }
}
