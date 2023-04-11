package edu.db2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        QueryEngine queryEngine = new QueryEngine();
        IndexEngine indexEngine;
        while(loop){
            System.out.println("Program is ready and waiting for user command");
            String input = scanner.nextLine();
            String[] parsedInput = input.split("\\s+");
            switch(parsedInput[0]){
                case "CREATE":
                    indexEngine = new IndexEngine();
                    queryEngine.setIndexEngine(indexEngine);
                    System.out.println("The hash-based and array-based indexes are built successfully.");
                    break;
                case "SELECT":
                    String operator = parsedInput[6];
                    try {
                        if (Objects.equals(operator, "=")) {
                            queryEngine.equalityQuery(Integer.parseInt(parsedInput[7]));
                        } else if (Objects.equals(operator, ">")) {
                            queryEngine.rangeQuery(Integer.parseInt(parsedInput[7]), Integer.parseInt(parsedInput[11]));
                        } else {

                        }
                    } catch (IOException e){
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;
                case "EXIT":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}
