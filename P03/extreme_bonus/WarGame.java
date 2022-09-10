import java.util.function.IntConsumer;

class WarGame {
    public static void main(String[] args) {
        Deck deck_1 = new Deck();
        Deck deck_2 = new Deck();

        deck_1.shuffle();
        deck_2.shuffle();

        int i = 1;
        Card player1;
        Card player1_1;
        Card player1_2;
        Card player2;
        Card player2_1;
        Card player2_2;

        while (deck_1.isEmpty() != true && deck_2.isEmpty() != true) {
            System.out.printf("\n=== Round %s of The War ===\n", i);

            player1 = deck_1.deal();
            player2 = deck_2.deal();

            System.out.println("Player 1 deals " + player1);
            System.out.println("Player 2 deals " + player2);

            if (Integer.parseInt(player1.toString().substring(0,1)) > Integer.parseInt(player2.toString().substring(0,1))) {
                System.out.println("Player 1 Wins!\n");
                deck_1.take(player1, player2);
            } 
            else if (Integer.parseInt(player1.toString().substring(0,1)) < Integer.parseInt(player2.toString().substring(0,1))) {
                System.out.println("Player 2 Wins!\n");
                deck_2.take(player1, player2);
            }
            else {
                System.out.println("The War Is A Tie! Time to duel!\n");
                if (deck_1.size() < 2 && deck_2.size() < 2) break;
                if (deck_1.size() < 2) { 
                    System.out.println("Player 2 wins as Player 1 does not have enough cards\n");
                    break;
                }
                if (deck_2.size() < 2) {
                    System.out.println("Player 1 wins as Player 2 does not have enough cards\n");
                    break;
                }

                int values_1 = 0;
                int values_2 = 0;

                player1_1 = deck_1.deal();
                player2_1 = deck_2.deal();
                System.out.println("Player 1 deals " + player1_1);
                System.out.println("Player 2 deals " + player2_1);

                values_1 = Integer.parseInt(player1_1.toString().substring(0,1));
                values_2 = Integer.parseInt(player2_1.toString().substring(0,1));

                player1_2 = deck_1.deal();
                player2_2 = deck_2.deal();
                System.out.println("Player 1 deals " + player1_2);
                System.out.println("Player 2 deals " + player2_2);

                values_1 += Integer.parseInt(player1_2.toString().substring(0,1));
                values_2 += Integer.parseInt(player2_2.toString().substring(0,1));

                if (values_1 > values_2) {
                    System.out.println("Player 1 Wins!\n");
                    deck_1.take(player1, player2);
                    deck_1.take(player1_1, player2_2);
                    deck_1.take(player1_2, player2_2);
                }
                else if (values_2 > values_1) {
                    System.out.println("Player 2 Wins!\n");
                    deck_2.take(player1, player2);
                    deck_2.take(player1_1, player2_2);
                    deck_1.take(player1_2, player2_2);
                }
                else {
                    System.out.println("Tie again. Flipping a coin...\n");
                    int coin = (int) (Math.random() * 10) + 1;
                    if (coin < 5) {
                        System.out.println("Player 1 Wins!\n");
                        deck_1.take(player1, player2);
                        deck_1.take(player1_1, player2_2);
                        deck_1.take(player1_2, player2_2);
                    }
                    else {
                        System.out.println("Player 2 Wins!\n");
                        deck_2.take(player1, player2);
                        deck_2.take(player1_1, player2_2);
                        deck_1.take(player1_2, player2_2);
                    }
                }

            }

            System.out.println("Player 1 has " + deck_1.size() + " cards");
            System.out.println("Player 2 has " + deck_2.size() + " cards");

            deck_1.shuffle();
            deck_2.shuffle();

            i++;
        }

        if (deck_1.size() > deck_2.size()) {
            System.out.println("\n========================================");
            System.out.println("THE WAR IS OVER! PLAYER 1 IS VICTORIOUS!");
            System.out.println("========================================\n");
        }
        else if (deck_2.size() > deck_1.size()) {
            System.out.println("\n========================================");
            System.out.println("THE WAR IS OVER! PLAYER 2 IS VICTORIOUS!");
            System.out.println("========================================\n");
        }
        else {
            System.out.println("\n========================================");
            System.out.println("THE WAR IS OVER! PLAYER 1 AND PLAYER 2 TIE!");
            System.out.println("========================================\n");
        }
    }
}