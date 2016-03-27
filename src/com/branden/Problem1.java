package com.branden;

import java.util.Scanner;

/**
 Problem 1, Lecture programs:

 If you didn’t complete these programs from the slides, please finish them.  Comment your code.

 •	Write a method which decides if a triangle is a right-angle triangle or not, and returns a boolean value.
 Test your method by calling it from your main method. Ask the user for the lengths of each side of the
 triangle.You can determine if a triangle is right-angled if sideA^2 + sideB^2 = longestSide^2.
 An example right-angle triangle has sides 3, 4 and 5, because 32 + 42 = 52  is 9 + 16 = 25.

 •	Write a method that takes a String as an argument, and prints the String backwards.


 */
public class Problem1 {
    static void run(){
        Scanner scan = new Scanner( System.in );
        int[] triangle = new int[3];
        System.out.println("Enter side lengths  of a triangle");
        for (int i = 0; i < 3; i++) {
            while(!scan.hasNextInt()){
                System.out.println("PLease enter a valid integer");
                scan.next();
            }
            triangle[i] = scan.nextInt();
        }
       if (  testForRightTriangle( triangle ) ){
           System.out.println("You have a right triangle");
       } else {
           System.out.println("Not a right triangle");
       }
        System.out.println("Enter a sentence to see it backwards.");
        scan.nextLine(); // advances past any  any unused newline characters from the previous use
        String sentence = scan.nextLine();
        stringBackwards( sentence );

        scan.close();
    }

    //	Write a method that takes a String as an argument, and prints the String backwards.
    static void stringBackwards( String inputString){
        for (int i = inputString.length()-1; i >= 0 ; i--) {
            System.out.print(inputString.charAt(i));
        }
    }
    static Boolean testForRightTriangle( int[] triangle) {
        int longestSide = 0;
        // determine longest side
        for (int i = 0; i < triangle.length; i++) {
            if ( triangle[i] > longestSide ) {
                longestSide = triangle[i];
            }
        }
        // once we have longest side we can test for right angle.
        // determine longest side
        int shortSideTotal= 0;
        int longSideTotal= 0;
        for (int i = 0; i < triangle.length; i++) {
            if ( triangle[i] != longestSide){
               shortSideTotal += triangle[i] * triangle[i];
            } else{
                longSideTotal = triangle[i] * triangle[i];
            }
        }

        return shortSideTotal == longSideTotal;
    }

}
