package com.javaexceptions.calcengine;

import com.javaexceptions.calcengine.InvalidStatementException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            processFile(reader);
        } catch (FileNotFoundException ex){
            System.out.println("File no found: "+ args[0]);
        }catch (IOException ex) {
            System.out.println("Error: "+ ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error processing file: "+ ex.getMessage());
        }
    }

    private static void processFile(BufferedReader reader) throws IOException{

        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            try {
                performOperation(inputLine);
            }catch(InvalidStatementException ex){
                System.out.println(ex.getMessage() + " - " + inputLine);
                writeInvalidStatementExceptionToLog(ex, inputLine);
            }
        }

    }

    static void writeInvalidStatementExceptionToLog(InvalidStatementException ex, String inputLine) {
        System.err.println("");
        System.err.println("***************************************************");
        System.err.println("Information written to log system");
        System.err.println("***************************************************");

        System.err.println(ex.getMessage() + " - " + inputLine);
        if(ex.getCause() != null){
            System.out.println(" caused by" + ex.getCause());
        }
        ex.printStackTrace(System.err);
    }

    private static void performOperation(String inputLine) throws InvalidStatementException {
        try {
            String[] parts = inputLine.split(" ");

            if (parts.length != 3) {
                throw new InvalidStatementException("Statement must have 3 parts: operation leftVal rightVal");
            }

            MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());
            int leftVal = valueFromWord(parts[1]);


            int rightVal = valueFromWord(parts[2]);

            int result = execute(operation, leftVal, rightVal);


            System.out.println(inputLine + " = " + result);
        }catch(InvalidStatementException ex){
            throw ex;
        }catch(Exception ex){
            throw new InvalidStatementException("Error processing statement", ex);
        }
    }

    static int execute(MathOperation operation, int leftVal, int rightVal){
        int result = 0;
        switch (operation) {
            case ADD -> result = leftVal + rightVal;
            case SUBTRACT -> result = leftVal - rightVal;
            case MULTIPLY -> result = leftVal * rightVal;
            case DIVIDE -> {
                if (rightVal == 0) {
//                    IllegalArgumentException exception = new IllegalArgumentException("Zero rightVal not permitted with divide operation");
//                    throw exception;
                    throw new IllegalArgumentException("Zero rightVal not permitted with divide operation");
                }
                result = leftVal / rightVal;
            }
        }
        return result;
    }

    static int valueFromWord(String word){
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        int value = -1;
        for(int index = 0; index < numberWords.length; index++){
            if(word.equals(numberWords[index])){
                value = index;
                break;
            }
        }
        if(value == -1){
            value = Integer.parseInt(word);
        }
        return value;
    }
}