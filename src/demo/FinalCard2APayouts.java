package demo;

import java.util.HashMap;

public class FinalCard2APayouts {

    public FinalCard2APayouts() {

    }
    public HashMap<Integer, Integer> setNewPayouts(String gameString, int lines){
        HashMap<Integer, Integer> payouts = new HashMap<>();
        payouts.put(0, 0);
        payouts.put(1, 4);

        switch (gameString) {
            case "Ties Lose Bonus":
            case "Ties Lose Starting Bonus":
                if (lines == 2) {
                    payouts.put(1, 4);

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
        return payouts;
    }
}
