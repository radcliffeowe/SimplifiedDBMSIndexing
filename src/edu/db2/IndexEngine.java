package edu.db2;

import java.io.*;
import java.util.LinkedList;

public class IndexEngine {

    private HashBasedIndex hashBasedIndex;
    private ArrayBasedIndex arrayBasedIndex;

    public IndexEngine() {
        this.hashBasedIndex = new HashBasedIndex();
        this.arrayBasedIndex = new ArrayBasedIndex();
        try {
            this.createIndexes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        System.out.println(hashBasedIndex.getHashIndex().get(4037).toString());
        LinkedList<RecordLocation>[] arrayIndex = arrayBasedIndex.getArrayIndex();
        System.out.println(arrayIndex[4036]);

    }
}
