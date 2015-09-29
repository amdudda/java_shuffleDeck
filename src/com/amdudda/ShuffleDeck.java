package com.amdudda;

import java.util.*;

public class ShuffleDeck {

    public static void main(String[] args) {
        // write your code here
        // this program takes a deck and shuffles it.

        // Done: createDeck function
        ArrayList<String> deck = createDeck();
        /* debugging: proof of concept
        for (String k : deck) {
            System.out.println(k);
        }
        */

        /* Done: pickCard function - not used, left here to demonstrate
           I know how to pick cards at random. */
        // pick cards at random from the deck
        // pickCard(deck);

        // DONE: shuffleDeck function that shuffles a deck
        // needs to return a new deck as a LinkedList so we can pop elements off it.
        LinkedList<String> s_deck = shuffleDeck(deck);
        drawCards(s_deck);

    } // end main

    private static void drawCards(LinkedList<String> sd) {
        // discard pile has to be a stack so that we pop things from it in LIFO order.
        Stack<String> discard = new Stack<String>();
        /* debugging: proof of concept
        for (String k : s_deck) {
            System.out.println(k);
        }
        */

        // Done: draw five cards from the shuffled deck and place in a "discard pile" that is a stack.
        System.out.println("Draw five cards drawn from deck:");
        for (int i=0; i<5; i++) {
            // doesn't matter which end we draw from, so long as we're consistent about which end.
            String card_drawn = sd.pop();
            System.out.println(card_drawn);
            discard.add(card_drawn);
        }

        System.out.println("\nDiscard pile:");
        for (String d : discard){
            // the output should have the same order as the list of cards drawn above.
            System.out.println(d);
        }
        System.out.println("\n");

        // let's see what peeking and pop do on a Stack, to verify I understand them.
        // output put should proceed in reverse order from list of cards printed above.
        while (!discard.empty()) {
            System.out.println("Top of discard pile is: " + discard.peek());
            System.out.println("And when we take the top of the discard pile, we take: " + discard.pop());
            if(!discard.empty()) {
                System.out.println("Which means the new top of the pile is now: " + discard.peek());
            } else System.out.println("The discard pile is now empty.");
        }
    } // end drawCards

    private static LinkedList<String> shuffleDeck(ArrayList<String> deck) {
        // takes a deck and returns a new, shuffled deck.
        // create the new LinkedList and initialize our randomizer
        LinkedList<String> shuffled = new LinkedList<String>();
        Random pick_card = new Random();
        Integer card_picked;

        while (deck.size()>0) {
            // pick a card number
            card_picked = pick_card.nextInt(deck.size());
            // add the corresponding card to the LinkedList.
            shuffled.add(deck.get(card_picked));
            // and remove that card from the deck
            deck.remove(deck.get(card_picked));
        }

        // returns a shuffled deck:
        return shuffled;
    } // end shuffleDeck

    public static ArrayList<String> createDeck() {
        // create a deck and return it to the program
        // intialize the ArrayList holding the deck, the array of suit names,
        // and a value to hold the full name of the card.
        ArrayList<String> deck = new ArrayList<String>();
        ArrayList<String> suits = new ArrayList<String>();
        suits.add(" of Hearts");
        suits.add(" of Diamonds");
        suits.add(" of Clubs");
        suits.add(" of Spades");

        for (String j : suits) {
            for (int i = 0; i < 13; i++) {
                deck.add(getCardValue(i % 13) + j);
            } // end run through suits
        }  // end run through card numbers

        // return the deck to the program
        return deck;
    }  // end createDeck

    public static String getCardValue(Integer val) {
        switch (val) {
            case 0:
                return "King";
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            default:
                return val.toString();
        }
    } // end getCardValue


    private static void pickCard(ArrayList<String> d) {
        // an end run around the problem of shuffling the deck, because it's
        // really just picking cards at random in the array.
        System.out.printf("Let's pick a card!");
        Random draw_card = new Random();
        Integer card_picked;
        Scanner s = new Scanner(System.in);
        String another_card;

        // pick cards at random till the user says to stop.
        while (true) {
            card_picked = draw_card.nextInt(52);
            System.out.println("Card picked is: " + d.get(card_picked));
            System.out.println("Pick another card (y/n?)");
            another_card = s.nextLine().toLowerCase();
            if (another_card.equals("n")) break;
        }

        // close scanner
        s.close();
    }  // end pickCard

    /*
    COMMENTARY in re: deck handling
    In a game where suit and value both matter, it'd be better to implement
    the cards as an ArrayList of value and suit so that each attribute
    can be inspected separately.

    Crazy Eights, for example :-) , would benefit from having the following
    objects:
    Cards, with attributes Value and Suit
    Decks, composed of 52 unique cards and a shuffle method, implemented as a Stack.
        Aka "piles of cards" - deck is just a handy monosyllable for the concept.
    Draw pile implemented as Deck, so that a new draw pile can be created directly from the discard
        by having them both be the same data type.
        Strictly speaking, should be a Queue, but since it's "hidden" and randomized after shuffling,
        it doesn't actually matter which end we pop cards off of so long as we're consistent.
    Discard implemented as Deck - *must* pop cards off stack in LIFO order, which is why Deck is
        secretly a Stack.
        Need to be able to use Deck.shuffle() on this when draw pile is exhausted.
    Hands as ArrayLists of Cards - cards are objects, therefore need to be stored in an ArrayList.

    */
} // end ShuffleDeck
