package com.branden;

import java.util.Scanner;

/**
 Problem 3, Arrays and Functions:

 Write a function which takes a number represented as a String as an argument, for example "12345" or "4321432143214321" and returns the digits of that number in an array.

 Your function should create an int[] array with one digit of your number per element.

 Return this array.
 */
public class Problem3 {
    static void run() {
        Scanner scan = new Scanner(System.in);
        String inputString;
        int[] intArray;

        // todo validate input for only numbers
        System.out.println("Enter a number");
        inputString = scan.nextLine();

        // convert string to int array
        intArray = numberStringToArray(inputString);

        // print array
        System.out.println("[");
        for ( int i = 0; i < intArray.length; i++){
            System.out.println( i + " : " +intArray[i] + "," );
        }
        System.out.println("]");

    }

    static int[] numberStringToArray(String numString) {
        int[] intArray = new int[numString.length()];
        for ( int i = 0; i < numString.length(); i++){
                intArray[i] =Integer.parseInt( numString.substring( i, i+1) );
        }
        return intArray;
    }
}
