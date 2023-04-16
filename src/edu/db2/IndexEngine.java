package edu.db2;

import java.io.*;

/**
 * Class to create indexes over the dataset
 */
public class IndexEngine {

    private HashBasedIndex hashBasedIndex;   //index using a hash map
    private ArrayBasedIndex arrayBasedIndex; //index using an array

    public IndexEngine() {
        this.hashBasedIndex = new HashBasedIndex();
        this.arrayBasedIndex = new ArrayBasedIndex();
        try {
            this.createIndexes(); //initialize the indexes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create indexes over the data. Reads every file 1-99 and reads each record in the file and parses the random value
     * Creates RecordLocation object and stores the record's file number and byte offset then adds it to both indexes
     * @throws IOException
     */
    private void createIndexes() throws IOException {
        for(int i = 1; i<100; i++){
            File file = new File("Project2Dataset/F" + i + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String content = bufferedReader.readLine();

            for(int j = 0; j<4000; j+=40){
                Integer randomValue = Integer.parseInt(content.substring(j + 33, j+37));
                RecordLocation recordLocation = new RecordLocation(i, j+33);
                hashBasedIndex.addEntry(randomValue, recordLocation);
                arrayBasedIndex.addEntry(randomValue, recordLocation);
            }
            bufferedReader.close();
        }

    }

    public HashBasedIndex getHashBasedIndex() {
        return hashBasedIndex;
    }

    public void setHashBasedIndex(HashBasedIndex hashBasedIndex) {
        this.hashBasedIndex = hashBasedIndex;
    }

    public ArrayBasedIndex getArrayBasedIndex() {
        return arrayBasedIndex;
    }

    public void setArrayBasedIndex(ArrayBasedIndex arrayBasedIndex) {
        this.arrayBasedIndex = arrayBasedIndex;
    }
}
