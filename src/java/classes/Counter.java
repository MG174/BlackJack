package classes;

import java.util.LinkedHashMap;
import java.util.Random;

public class Counter {

    LinkedHashMap<String, Integer> cards_hashmap = new LinkedHashMap<String, Integer>();

    public Counter() {
    }

    public int count_cards(LinkedHashMap<String, Integer> cards_to_count) {
        int value_of_cards_in_game = 0;

        for (String key : cards_to_count.keySet()) {
            if (!key.equals("ace_of_spades2") && !key.equals("ace_of_hearts") && !key.equals("ace_of_clubs") && !key.equals("ace_of_diamonds")) {
                value_of_cards_in_game = value_of_cards_in_game + cards_to_count.get(key);
            }
        }

        if (cards_to_count.containsKey("ace_of_spades2")) {
            value_of_cards_in_game = value_of_cards_in_game + 1;
            if (cards_to_count.containsKey("ace_of_hearts")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_clubs")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_diamonds")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
        } else if (cards_to_count.containsKey("ace_of_hearts")) {
            value_of_cards_in_game = value_of_cards_in_game + 1;
            if (cards_to_count.containsKey("ace_of_spades2")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_clubs")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_diamonds")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
        } else if (cards_to_count.containsKey("ace_of_clubs")) {
            value_of_cards_in_game = value_of_cards_in_game + 1;
            if (cards_to_count.containsKey("ace_of_hearts")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_spades2")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_diamonds")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
        } else if (cards_to_count.containsKey("ace_of_diamonds")) {
            value_of_cards_in_game = value_of_cards_in_game + 1;
            if (cards_to_count.containsKey("ace_of_hearts")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_clubs")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
            if (cards_to_count.containsKey("ace_of_spades2")) {
                value_of_cards_in_game = value_of_cards_in_game + 1;
            }
        }

        if ((cards_to_count.containsKey("ace_of_spades2") || cards_to_count.containsKey("ace_of_hearts") || cards_to_count.containsKey("ace_of_clubs") || cards_to_count.containsKey("ace_of_diamonds")) && (value_of_cards_in_game + 10) == 21) {
            value_of_cards_in_game += 10;
        }

        return value_of_cards_in_game;
    }

    public LinkedHashMap<String, Integer> get_random_cards_croupier_and_return(LinkedHashMap<String, Integer> cards_to_count) {
        int value_of_cards_in_game = 0;

        Deck deck = new Deck();
        cards_hashmap = deck.getCards_hashmap();

        do {

            Random rnd = new Random();
            int random_int_hashmap_size_croupier = rnd.nextInt(cards_hashmap.size());
            String card_croupier = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size_croupier];

            do {
                random_int_hashmap_size_croupier = rnd.nextInt(cards_hashmap.size());
                card_croupier = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size_croupier];
            } while (cards_to_count.containsKey(card_croupier));

            int value_of_random_card_croupier = cards_hashmap.get(card_croupier);
            cards_to_count.put(card_croupier, value_of_random_card_croupier);

            value_of_cards_in_game = count_cards(cards_to_count);

        } while (value_of_cards_in_game < 17);

        return cards_to_count;
    }

    public int have_won_player(LinkedHashMap<String, Integer> cards_to_count_player, LinkedHashMap<String, Integer> cards_to_count_croupier) {
        int value_of_cards_player = count_cards(cards_to_count_player);
        int value_of_cards_croupier = count_cards(cards_to_count_croupier);

        if ((cards_to_count_player.containsKey("ace_of_spades2") || cards_to_count_player.containsKey("ace_of_hearts") || cards_to_count_player.containsKey("ace_of_clubs") || cards_to_count_player.containsKey("ace_of_diamonds")) && (value_of_cards_player + 10) <= 21) {
            value_of_cards_player += 10;
        }

        if ((cards_to_count_croupier.containsKey("ace_of_spades2") || cards_to_count_croupier.containsKey("ace_of_hearts") || cards_to_count_croupier.containsKey("ace_of_clubs") || cards_to_count_croupier.containsKey("ace_of_diamonds")) && (value_of_cards_croupier + 10) <= 21) {
            value_of_cards_croupier += 10;
        }

        if (value_of_cards_player > 21) {
            return 2;
        } else if (value_of_cards_player == 21) {
            return 4;
        } else if ((value_of_cards_player > value_of_cards_croupier && value_of_cards_player <= 21) || value_of_cards_croupier > 21) {
            return 1;
        } else if ((value_of_cards_player < value_of_cards_croupier && value_of_cards_croupier <= 21) || value_of_cards_player > 21) {
            return 2;
        } else if ((value_of_cards_player <= 21 && value_of_cards_croupier <= 21) && value_of_cards_player == value_of_cards_croupier) {
            return 3;
        }

        return 5;
    }

    public String get_info_value(LinkedHashMap<String, Integer> cards_to_count) {
        int value_of_cards = count_cards(cards_to_count);
        String info_value_cards = "";

        if (cards_to_count.containsKey("ace_of_spades2") && (value_of_cards + 10) <= 21) {
            info_value_cards = String.valueOf(value_of_cards) + "/" + String.valueOf(value_of_cards + 10);
        } else if (cards_to_count.containsKey("ace_of_hearts") && (value_of_cards + 10) <= 21) {
            info_value_cards = String.valueOf(value_of_cards) + "/" + String.valueOf(value_of_cards + 10);
        } else if (cards_to_count.containsKey("ace_of_clubs") && (value_of_cards + 10) <= 21) {
            info_value_cards = String.valueOf(value_of_cards) + "/" + String.valueOf(value_of_cards + 10);
        } else if (cards_to_count.containsKey("ace_of_diamonds") && (value_of_cards + 10) <= 21) {
            info_value_cards = String.valueOf(value_of_cards) + "/" + String.valueOf(value_of_cards + 10);
        } else {
            info_value_cards = String.valueOf(value_of_cards);
        }

        return info_value_cards;
    }
}
