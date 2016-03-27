package com.branden;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 Problem 1:

 Try out the Agile or prototyping methodology with this program.

 Write the first prototype of a program that plays a simplified version of the card game Blackjack with you.  You can find the rules online. In this simple version, omit the gambling, and record the number of wins and losses for you and for the computer. What strategy will you program the computer to have?

 Remember prototyping means creating a simple version of the program, and then adding features to it.  What are the bare of minimum features you’d implement to start with?

 Since this is a first prototype, you don't need to finish this code. The aim of this question is to think about the design of the program and what you would need in a very first prototype.


 */

//TODO extract repeating code into methods.
public class AdvancedProblem1 {
    static void run() {

        Random deal = new Random();
        Scanner scanner = new Scanner(System.in);

        // check if we are playing again.  If first time then set deal again to Y.
        String dealAgain = "Y";
        while (dealAgain.equalsIgnoreCase("Y")) {
            // generate new deck of cards
            ArrayList <Integer>deck = getDeck();

            ArrayList<Integer> hand1 = new ArrayList<>();
            ArrayList<Integer> hand2 = new ArrayList<>();
            // first deal
            int cardNum;
            for (int i = 0; i < 2; i++) {
                // random int between 1 ( inclusive)  and deck size ( exclusive )
                cardNum = deal.nextInt(deck.size());

                // add to hand1
                hand1.add( getCardFromInt( cardNum ));
                // remove from deck
                deck.remove(cardNum);

                cardNum = deal.nextInt(deck.size());
                // add to hand2
                hand2.add( getCardFromInt( cardNum ));
                // remove from deck
                deck.remove(cardNum);

            }
            // show cards

            System.out.println("Dealer hand:");
            System.out.println(hand2);
            int hand2Total = checkHandTotal(hand2);
            System.out.println("Dealer total:" + hand2Total);
            System.out.println("Your hand:");
            System.out.println(hand1);
            int hand1Total = checkHandTotal(hand1);
            System.out.println("Your total:" + hand1Total);

            // ask player1 if they want to hit or stand
            Scanner hitOrStand = new Scanner(System.in);
            Boolean standing = false;
            String move;

            if ( hand1Total == 21){
                standing = true;
            }
            while (!standing) {

                System.out.println("Do you want to hit or stand?");
                move = hitOrStand.nextLine();
                while ( !move.equalsIgnoreCase("hit") && !move.equalsIgnoreCase("stand")){
                    System.out.println("Please enter \'hit\' or \'stand\'");
                    move = hitOrStand.next();
                }
                if (move.equalsIgnoreCase("hit")) {
                    // hit - if over 21 then bust
                    // if not over 21 then ask again
                    cardNum = deal.nextInt(deck.size() + 1);

                    // add to hand1
                    hand1.add( getCardFromInt( cardNum ));
                    // show hand
                    System.out.println("Your hand"+ hand1);

                    // check total
                    hand1Total = checkHandTotal(hand1);
                    System.out.println("Hand total: " +hand1Total);
                    if (hand1Total >= 21) {
                        break;
                    }
                    // remove from deck
                    deck.remove(cardNum);
                } else {
                    // if stand then computer turn
                    standing = true;
                }
            }

            // if computer total is < 17 then hit.
            if ( hand1Total > 21 ){
                standing = true;
            } else {
                standing = hand2Total > 17 ? true : false;
            }
            while (!standing) {
                cardNum = deal.nextInt(deck.size() + 1);
                // add to hand2
                hand2.add( getCardFromInt( cardNum ));
                System.out.println("Computer hit ");
                System.out.println("Computer hand " + hand2);
                hand2Total = checkHandTotal(hand2);

                System.out.println("Computer total " + hand2Total);

                if (hand2Total >= 21) {
                    break;
                }
                // if over 17 then dealer turn ends
                standing = hand2Total > 17;

            }
            if ( hand1Total > 21 ){
                System.out.println("You busted dealer Wins");
            } else if ( hand2Total > 21 ){
                System.out.println("Dealer busted you Wins");

            }
            else if (hand2Total == hand1Total) {
                System.out.println("Dealer Wins");
            } else {
                // check which player is closest to 21
                if (hand1Total - 21 > hand2Total - 21) {
                    System.out.println("You win");
                } else {
                    System.out.println("Computer win");
                }
            }
            System.out.println("Deal again? Y or N");
            dealAgain = scanner.nextLine();
            if (dealAgain.equalsIgnoreCase("n")) {
                dealAgain = "n";
            }

        }
        System.out.println("Thanks for playing");
        scanner.close();

    }
    static ArrayList<Integer> getDeck(){
        ArrayList <Integer>deck = new ArrayList();
        // 1 = Ace;
        for ( int i = 0; i < 52; i++){
            deck.add(i);
        }
        return deck;
    }
    static int checkHandTotal(ArrayList<Integer> hand ){
        int total = 0;
        int cardVal = 0;
        Boolean containsAce = false;
        for ( int i = 0; i < hand.size(); i++) {
            cardVal = hand.get(i);
            // if card is 0 then try to add it to hand as an 11
            // TODO logic if  more than one ace
            if ( hand.get(i) == 1 ){
                cardVal = 11;
                containsAce = true;

            }
            // if card is >= 10 then it is a face card and face cards = 10
            else if ( hand.get(i) >= 10 ){
                cardVal = 10;
            }
            total += cardVal;
        }
        if ( total > 21){
            // if hand contains ace then convert to 1
            if ( containsAce ){
                // re-add total
                total = 0;
                for ( int i = 0; i < hand.size(); i++) {
                    if ( hand.get(i) == 1 ){
                     cardVal = 1;
                    }
                    if ( hand.get(i) >= 10){
                     cardVal = 10;
                    }
                    total += cardVal;
                }
            }
        }
        return total;
    }
    static int getCardFromInt( int cardInt ){
        // if cardInt % 13 is 0 then it is an ace and ace is value 1 or 11;
        return (cardInt % 13) + 1;
    }
}

