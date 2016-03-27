package com.branden;

import java.util.Scanner;

/**
 Write a program that can help decide if a particular programming project is best solved using a Waterfall or Agile methodology. Your program should ask the user

 •	How many programmers will be on the team
 •	If there needs to be firm deadlines and a fixed schedule
 •	If the programmers have experience in requirements, analysis and testing as well as coding
 •	If the programmers are working in the same location or in many locations
 •	If there are stringent quality control requirements
 •	If the customer will be requiring working models early in the process

 Write a method called agileOrWaterfall, which takes this data as argument(s) and returns an opinion on whether Agile or Waterfall is best.  Use this method in your program to suggest which methodology to use.

 Suggestion: would storing the answers in an array make your program tidier? 1 argument is usually easier to deal with than 6.

 */
public class Problem2 {
    static void run(){
        Scanner scan = new Scanner( System.in );
        int numProgrammers = 0;
        Boolean[]requirements = new Boolean[5];

        System.out.println("How many programmers?");
        numProgrammers = Integer.parseInt( scan.nextLine());

        System.out.println("Are there firm deadlines? Y or N");
        requirements[0] = scan.nextLine().equalsIgnoreCase("y");

        System.out.println("Are the programmers experienced ? Y or N");
        requirements[1] = scan.nextLine().equalsIgnoreCase("y");

        System.out.println("Are the programmers in the same location? Y or N");
        requirements[2] = scan.nextLine().equalsIgnoreCase("y");

        System.out.println("Are there stringent quality control requirements? Y or N");
        requirements[3] = scan.nextLine().equalsIgnoreCase("y");

        System.out.println("Will there be required working models early in the process? Y or N");
        requirements[4] = scan.nextLine().equalsIgnoreCase("y");

        System.out.println("We recommend " + agileOrWaterfall( numProgrammers, requirements) );
    }
    static  String agileOrWaterfall( int numProgrammers, Boolean[] requirements ){
        String method = null;
        int agileVote = 0;
        int waterfallVote = 0;

        // if there is a large ( more than 4 ) team and they don't have experience then use waterfall
        if (  numProgrammers > 4 ){
            waterfallVote++;
        } else{
            agileVote++;
        }
        // if they don't have experience then you can budget for experts and specialists w ith waterfall
        if( requirements[1] ){
            waterfallVote++;
        } else {
            agileVote++;
        }

        // if the team is on a firm deadline you can use waterfall
        if (  requirements[0] ){
            waterfallVote++;
        } else {
            agileVote++;
        }
        // if there are stringent quality requirement then use waterfall
        if (  requirements[3] ){
            waterfallVote++;
        } else {
            agileVote++;
        }

        // if not in same location then agile can be hard
        // From:  Slide 33, Java Programming - Week 3 - Methods and Program Design Process - Spring 2016
        if( requirements[2] ){
            agileVote++;
        } else {
            waterfallVote++;
        }
        if ( agileVote == waterfallVote){
            method = "either Agile or Waterfall";
        } else if ( agileVote > waterfallVote ){
            method = "Agile";
        } else {
            method = "Waterfall";
        }
        return method;
    }
}
