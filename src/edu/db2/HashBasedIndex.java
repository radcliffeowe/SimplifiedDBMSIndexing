package edu.db2;

import java.util.*;

/**
 * Class to implement a hash-based index using a HashMap with an integer key (random value field) and a
 * LinkedList of RecordLocations to support multiple records containing the same random value since it is not unique
 */
public class HashBasedIndex {

    private HashMap<Integer, LinkedList<RecordLocation>> hashIndex;

    public HashBasedIndex() {
        this.hashIndex = new HashMap<>();
    }

    /**
     * Method to add an entry to the hash-based index
     * Checks if there is already an entry for the random value, if there is, then it adds the RecordLocation to the LinkedList
     * If there is not, then it creates a new LinkedList, adds the RecordLocation, sorts the LinkedList by file number to ensure sequential I/Os and inserts it into the Hash Map.
     * @param randValue is the random value field of the record
     * @param recordLocation is the RecordLocation object associated with the record being indexed
     */
    public void addEntry(int randValue, RecordLocation recordLocation){
        LinkedList<RecordLocation> exists = hashIndex.get(randValue);
        if(Objects.isNull(exists)){
            LinkedList<RecordLocation> entry = new LinkedList<>();
            entry.add(recordLocation);
            hashIndex.put(randValue, entry);
        }
        else{
            exists.add(recordLocation);
            Collections.sort(exists);
            hashIndex.put(randValue, exists);
        }
    }

    public HashMap<Integer, LinkedList<RecordLocation>> getHashIndex() {
        return hashIndex;
    }

    public void setHashIndex(HashMap<Integer, LinkedList<RecordLocation>> hashIndex) {
        this.hashIndex = hashIndex;
    }
}
