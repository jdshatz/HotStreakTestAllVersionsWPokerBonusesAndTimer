package demo;

import java.util.HashMap;

public class Payouts {

    public Payouts() {

    }
    public HashMap<Integer, Integer> setNewPayouts(String gameString, int lines){
        HashMap<Integer, Integer> payouts = new HashMap<>();
        payouts.put(0, 0);
        switch (gameString) {
            case "Basic Ties Lose":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 24);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 100);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 8);
                    payouts.put(3, 36);
                    payouts.put(4, 400);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 20);
                    payouts.put(4, 150);
                    payouts.put(5, 2500);
                }
                break;
            case "Basic Ties Win":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 12);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 25);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 4);
                    payouts.put(3, 20);
                    payouts.put(4, 200);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 15);
                    payouts.put(4, 50);
                    payouts.put(5, 400);
                }
                break;
            case "Ties Lose Starting Cards":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 20);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 75);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 8);
                    payouts.put(3, 20);
                    payouts.put(4, 400);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 15);
                    payouts.put(4, 150);
                    payouts.put(5, 2000);
                }
                break;
            case "Ties Win Starting Cards":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 10);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 15);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 4);
                    payouts.put(3, 16);
                    payouts.put(4, 175);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 10);
                    payouts.put(4, 50);
                    payouts.put(5, 500);
                }
                break;
            case "Ties Lose Any Column":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 15);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 45);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 8);
                    payouts.put(3, 20);
                    payouts.put(4, 175);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 15);
                    payouts.put(4, 75);
                    payouts.put(5, 1500);
                }
                break;
            case "Ties Win Any Column":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 10);
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 12);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 4);
                    payouts.put(3, 16);
                    payouts.put(4, 125);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 10);
                    payouts.put(4, 30);
                    payouts.put(5, 500);
                }
                break;
            case "Ties Lose Bonus":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 16);

                /*
                }
                else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 100);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 8);
                    payouts.put(3, 36);
                    payouts.put(4, 400);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 20);
                    payouts.put(4, 150);
                    payouts.put(5, 2500);
                    */
                }
                break;
            case "Ties Lose Starting Bonus":
                if (lines == 2) {
                    payouts.put(1, 2);
                    payouts.put(2, 12);
                    /*
                } else if (lines == 3) {
                    payouts.put(1, 3);
                    payouts.put(2, 6);
                    payouts.put(3, 75);
                } else if (lines == 4) {
                    payouts.put(1, 2);
                    payouts.put(2, 8);
                    payouts.put(3, 20);
                    payouts.put(4, 400);
                } else if (lines == 5) {
                    payouts.put(1, 2);
                    payouts.put(2, 5);
                    payouts.put(3, 15);
                    payouts.put(4, 150);
                    payouts.put(5, 2000);
                    */
                }
                break;
        }
        return payouts;
    }
}
