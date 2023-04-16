package edu.db2;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class to query the dataset
 */
public class QueryEngine {

    private IndexEngine indexEngine; //if indexes have been created over the dataset, the IndexEngine attribute will allow the use of the indexes

    public IndexEngine getIndexEngine() {
        return indexEngine;
    }

    public void setIndexEngine(IndexEngine indexEngine) {
        this.indexEngine = indexEngine;
    }

    /**
     * Runs an equality query over the dataset.
     * Checks if indexes have been created over the dataset, if not, conducts a full table scan checking records' random value to check equality.
     * If indexes have been created, uses a Hash-Based Index to query for the searchValue.
     * @param searchValue is the value being queried for
     * @throws IOException
     */
    public void equalityQuery(int searchValue) throws IOException {
       long start = System.currentTimeMillis();
       boolean indexUsed = false;
       LinkedList<String> results = new LinkedList<>();
       int blocksRead = 0;
       if(Objects.isNull(indexEngine)){
           //full table scan
           for(int i = 1; i<100; i++){
               blocksRead++;
               File file = new File("Project2Dataset/F" + i + ".txt");
               BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

               String content = bufferedReader.readLine();

               for(int j = 0; j<4000; j+=40){
                   Integer randomValue = Integer.parseInt(content.substring(j + 33, j+37));
                   if(randomValue == searchValue){
                       results.add(content.substring(j, j+40));
                   }
               }
               bufferedReader.close();
           }
       }
       else{
           indexUsed = true;
           LinkedList<RecordLocation> entries = indexEngine.getHashBasedIndex().getHashIndex().get(searchValue);
           File file;
           String blockInMemory = "";
           int fileNumber = -1;
           for(RecordLocation recordLocation : entries){
               int offset = recordLocation.getRecordOffset();
                if(recordLocation.getFileNumber() != fileNumber){ //new file has been read, need to increment blocksRead to measure I/O
                    file = new File("Project2Dataset/F" + recordLocation.getFileNumber() + ".txt");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    blockInMemory = bufferedReader.readLine();
                    blocksRead++;
                    fileNumber = recordLocation.getFileNumber();
                }
               results.add(blockInMemory.substring(offset-33, offset + 7));
           }
       }
       long timeTaken = System.currentTimeMillis()- start;
       for(String s: results){
           System.out.println(s);
       }
       if(indexUsed){
           System.out.println("Hash-Based Index used");
       }
       else{
           System.out.println("Full Table Scan used");
       }
       System.out.println("Total time taken: " + timeTaken + " ms");
       System.out.println(blocksRead + " data files read");
    }

    /**
     * Method to conduct a range query.
     * If an index has not been created on the data, the method conducts a full table scan and adds any record within the range to the results.
     * If an index has been created, the method uses an Array-Based Index to query the data by iterating over the index from the lower bound to the upper bound and reads the files in to extract the records.
     * @param lowerBound is the lower bound of the range
     * @param upperBound is the upper bound of the range
     * @throws IOException
     */
    public void rangeQuery(int lowerBound, int upperBound) throws IOException {
        long start = System.currentTimeMillis();
        boolean indexUsed = false;
        LinkedList<String> results = new LinkedList<>();
        int blocksRead = 0;
        if(Objects.isNull(indexEngine)){
            //full table scan
            for(int i = 1; i<100; i++){
                blocksRead++;
                File file = new File("Project2Dataset/F" + i + ".txt");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String content = bufferedReader.readLine();

                for(int j = 0; j<4000; j+=40){
                    int randomValue = Integer.parseInt(content.substring(j + 33, j+37));
                    if(randomValue > lowerBound && randomValue < upperBound){
                        results.add(content.substring(j, j+40));
                    }
                }
                bufferedReader.close();
            }
        }
        else{
            indexUsed = true;
            ArrayBasedIndex index = indexEngine.getArrayBasedIndex();
            File file;
            String blockInMemory = "";
            int fileNumber = -1;
            for(int i = lowerBound; i<=upperBound; i++){
                LinkedList<RecordLocation> entries = index.getArrayIndex()[i];
                if(!Objects.isNull(entries)) {
                    for (RecordLocation recordLocation : entries) {
                        int offset = recordLocation.getRecordOffset();
                        if (recordLocation.getFileNumber() != fileNumber) { //new file has been read, need to increment blocksRead to measure I/O
                            file = new File("Project2Dataset/F" + recordLocation.getFileNumber() + ".txt");
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                            blockInMemory = bufferedReader.readLine();
                            blocksRead++;
                            fileNumber = recordLocation.getFileNumber();
                        }
                        results.add(blockInMemory.substring(offset - 33, offset + 7));
                    }
                }
            }
        }
        long timeTaken = System.currentTimeMillis()- start;
        for(String s: results){
            System.out.println(s);
        }
        if(indexUsed){
            System.out.println("Array-Based Index used");
        }
        else{
            System.out.println("Full Table Scan used");
        }
        System.out.println("Total time taken: " + timeTaken + " ms");
        System.out.println(blocksRead + " data files read");
    }

    /**
     * Conducts an inequality query over the data
     * No index is used for this query
     * @param searchValue is the value being queried for
     * @throws IOException
     */
    public void inequalityQuery(int searchValue) throws IOException {
        long start = System.currentTimeMillis();
        LinkedList<String> results = new LinkedList<>();
        int blocksRead = 0;

        for(int i = 1; i<100; i++){
            blocksRead++;
            File file = new File("Project2Dataset/F" + i + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String content = bufferedReader.readLine();

            for(int j = 0; j<4000; j+=40){
                int randomValue = Integer.parseInt(content.substring(j + 33, j+37));
                if(randomValue != searchValue){
                    results.add(content.substring(j, j+40));
                }
            }
            bufferedReader.close();
        }
        long timeTaken = System.currentTimeMillis()- start;
        for(String s: results){
            System.out.println(s);
        }
        System.out.println("Total time taken: " + timeTaken + " ms");
        System.out.println(blocksRead + " data files read");
    }
}

