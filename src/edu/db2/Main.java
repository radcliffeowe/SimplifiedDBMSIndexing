package edu.db2;

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
