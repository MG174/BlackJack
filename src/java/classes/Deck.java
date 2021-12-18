package classes;

import java.util.LinkedHashMap;

public class Deck {

    LinkedHashMap<String, Integer> cards_hashmap = new LinkedHashMap<String, Integer>();

    public Deck() {
        cards_hashmap.put("10_of_clubs", 10);
        cards_hashmap.put("10_of_diamonds", 10);
        cards_hashmap.put("10_of_hearts", 10);
        cards_hashmap.put("10_of_spades", 10);
        cards_hashmap.put("2_of_clubs", 2);
        cards_hashmap.put("2_of_diamonds", 2);
        cards_hashmap.put("2_of_hearts", 2);
        cards_hashmap.put("2_of_spades", 2);
        cards_hashmap.put("3_of_clubs", 3);
        cards_hashmap.put("3_of_diamonds", 3);
        cards_hashmap.put("3_of_hearts", 3);
        cards_hashmap.put("3_of_spades", 3);
        cards_hashmap.put("4_of_clubs", 4);
        cards_hashmap.put("4_of_diamonds", 4);
        cards_hashmap.put("4_of_hearts", 4);
        cards_hashmap.put("4_of_spades", 4);
        cards_hashmap.put("5_of_clubs", 5);
        cards_hashmap.put("5_of_diamonds", 5);
        cards_hashmap.put("5_of_hearts", 5);
        cards_hashmap.put("5_of_spades", 5);
        cards_hashmap.put("6_of_clubs", 6);
        cards_hashmap.put("6_of_diamonds", 6);
        cards_hashmap.put("6_of_hearts", 6);
        cards_hashmap.put("6_of_spades", 6);
        cards_hashmap.put("7_of_clubs", 7);
        cards_hashmap.put("7_of_diamonds", 7);
        cards_hashmap.put("7_of_hearts", 7);
        cards_hashmap.put("7_of_spades", 7);
        cards_hashmap.put("8_of_clubs", 8);
        cards_hashmap.put("8_of_diamonds", 8);
        cards_hashmap.put("8_of_hearts", 8);
        cards_hashmap.put("8_of_spades", 8);
        cards_hashmap.put("9_of_clubs", 9);
        cards_hashmap.put("9_of_diamonds", 9);
        cards_hashmap.put("9_of_hearts", 9);
        cards_hashmap.put("9_of_spades", 9);
        cards_hashmap.put("ace_of_clubs", 11);
        cards_hashmap.put("ace_of_diamonds", 11);
        cards_hashmap.put("ace_of_hearts", 11);
        cards_hashmap.put("ace_of_spades2", 11);
        cards_hashmap.put("jack_of_clubs2", 10);
        cards_hashmap.put("jack_of_diamonds2", 10);
        cards_hashmap.put("jack_of_hearts2", 10);
        cards_hashmap.put("jack_of_spades2", 10);
        cards_hashmap.put("king_of_clubs2", 10);
        cards_hashmap.put("king_of_diamonds2", 10);
        cards_hashmap.put("king_of_hearts2", 10);
        cards_hashmap.put("king_of_spades2", 10);
        cards_hashmap.put("queen_of_clubs2", 10);
        cards_hashmap.put("queen_of_diamonds2", 10);
        cards_hashmap.put("queen_of_hearts2", 10);
        cards_hashmap.put("queen_of_spades2", 10);
    }

    public LinkedHashMap<String, Integer> getCards_hashmap() {
        return cards_hashmap;
    }

}
