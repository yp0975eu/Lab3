package com.branden;

import java.util.Scanner;

/**

 Problem 4, Credit Cards:

 Credit card numbers have a check digit at the end that can be used to check if the number is entered correctly.  This is how websites can indicate that your card number is invalid without asking your bank.

 The last digit of your credit card number is a check digit. When you enter a credit card number, an algorithm does a calculation with the first 15 digits entered to calculate a single digit, which should match the check digit, the last digit of your credit card number. (It doesn’t check if the account is valid – just if the digits are entered correctly.)

 Credit cards also begin with a specific number or numbers and must be a certain length. For example, all Visa card numbers begin with 4. And Visa card numbers should be 16 digits.

 Here's how the validation is done:

 http://web.eecs.umich.edu/~bartlett/credit_card_number.html

 Write a program that asks a user for a Visa credit card number. Save this as a String.

 Write a method called isVisaCreditCardNumberValid(String cardNumber) to examine the String, and decide if the credit card number that it represents is valid or not.  Your method should return a boolean value.

 For testing, here’s some example valid CC numbers, (unlikely to be actual numbers) which are valid credit card numbers according to the rules in the link above:

 4123123412341236
 4000111122223339
 4123456789012349

 As an example, for the last number – credit card digits in the top row, calculation and numbers to add (bold) in bottom row

 So the sum is 8 +1 + 4 +3 +8 +5 +1 +2 + 7+ 1 + 6 +9 +0 +1 +4 +3 +8 +9 = 80.
 80 divided by 10 has no remainder so number is valid.

 Test your program with some “valid” numbers, and also try changing one digit, like change a 3 to a 4, and verify that the number is now invalid.



 */
public class Problem4 {
    static void run(){

        String[] validTestNumbers = {"4024007108097519","4929962135647015","4916434051442891","4716582252119817","4556168252025858","4916437344369573","4556324542458371","4716235338266827","4747899523610801","4024007137528583","4929224483137606","4916032749981005","4532628998698374","4024007108981233","4532213432731055","4716802076205786","4024007163421992","4417422927889316","4716725262545337","4223753676922468"};
        String[] inValidTestNumbers = {"4024003193097319","4929930135347015","4916934395142391","4716982232119817","4556168252035898","4916437349369573","4556324542958371","4716295338266827","4749899523610801","4024097133528583","4929224483937696","4916032939981995","4539628993698374","4024907138381233","4532233432739055","4716803076905786","4024007163321992","4417422327889316","4716725362545337","4229753376933468"};
        testNumbers ( validTestNumbers );
        testNumbers ( inValidTestNumbers );


        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter a credit card number");
        boolean valid = false;

        while( !valid ) {
            String cardNumber = scanner.nextLine();
            while (cardNumber.length() != 16) {
                System.out.println("Error, there weren't 16 numbers in your card number, try again.");
                cardNumber = scanner.nextLine();
            }
            valid = isVisaCreditCardNumberValid( cardNumber );
            if (!valid) { System.out.println("Is not a valid Visa number, try again."); }
        }
        System.out.println("Valid credit card number");

    }
    public static int[] stringToInts( String inputString ){
        int[] numbersArray = new int[ inputString.length() ] ;
        // from http://alvinalexander.com/java/edu/qanda/pjqa00010.shtml
        for ( int x = 0; x < inputString.length(); x++){
            numbersArray[x] = Integer.parseInt( inputString.substring( x, x+1) );
        }
        return numbersArray;
    }
    public static  boolean isVisaCreditCardNumberValid( String cardNumber ){
        boolean isValid = false;

        //int size = cardNumber.length();
        int[] intsArray;
        intsArray = stringToInts( cardNumber );
        int[] doubledInts = new int[ 16 ];
        // get the digit if even (  mod 2 ) then double it, if the answer is more than one digit , add them together
        // and store it.
        String doubled;
        int tempTotal = 0;
        for ( int x = 0 ; x < intsArray.length; x++){

            if ( x % 2 == 0){
                if ( intsArray[x] * 2 >= 10 ){
                    tempTotal = 0;
                    doubled = Integer.toString( intsArray[x] * 2);
                    for ( int i = 0; i < doubled.length(); i++){
                     tempTotal += Integer.parseInt( doubled.substring(i, i+1) );
                    }
                    doubledInts[x] = tempTotal;

                } else {
                    doubledInts[x] = intsArray[x] * 2;
                }
            } else {
                doubledInts[x] = intsArray[x];
            }
        }
        int total = 0;
        // add them all up
        for ( int x = 0 ; x < doubledInts.length; x++){

            total += doubledInts[x];
        }

        isValid = total % 10 == 0;

        return isValid;
    }
    static void testNumbers( String[] testNumbers ){
        for( int i = 0; i < testNumbers.length; i++){
            Boolean valid = isVisaCreditCardNumberValid( testNumbers[i] );
            if (!valid) { System.out.println(testNumbers[i] + " is not valid"); } else {
                System.out.println( testNumbers[i] +" is valid");
            }
        }
    }
}

