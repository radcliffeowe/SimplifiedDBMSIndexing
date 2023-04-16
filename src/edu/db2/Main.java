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
                case "CREATE": //create a hash-based and array-based index over the data
                    indexEngine = new IndexEngine();
                    queryEngine.setIndexEngine(indexEngine);
                    System.out.println("The hash-based and array-based indexes are built successfully.");
                    break;
                case "SELECT": //query the data
                    String operator = parsedInput[6]; // check the operator of the query statement, either =, >, or !=
                    try {
                        if (Objects.equals(operator, "=")) {
                            queryEngine.equalityQuery(Integer.parseInt(parsedInput[7])); //run an equality query
                        } else if (Objects.equals(operator, ">")) {
                            queryEngine.rangeQuery(Integer.parseInt(parsedInput[7]), Integer.parseInt(parsedInput[11])); // run a range query
                        } else {
                            queryEngine.inequalityQuery(Integer.parseInt(parsedInput[7])); //run an inequality query
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
