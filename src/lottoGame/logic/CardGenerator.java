package lottoGame.logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class CardGenerator {

    private ArrayList<ArrayList<String>> lottoCard = new ArrayList<>();


    public ArrayList<ArrayList<String>> getLottoCard() {
        return lottoCard;
    }

    private void showCard() {
        lottoCard.forEach(System.out::println);

    }

    private void generateCard() {

        fillCard();

        final Random random = new Random();
        int currentRandomNumber;

        for (int i = 0; i < 3; i++) {
            for (int index: generateIndex()) {

                if (index == 0) {
                    currentRandomNumber = random.nextInt(9) + 1;
                    lottoCard.get(i).set(index, String.valueOf(currentRandomNumber));
                    continue;
                }
                if (index == 8) {
                    currentRandomNumber = random.nextInt(10 + 1) + (index * 10);
                    lottoCard.get(i).set(index, String.valueOf(currentRandomNumber));
                    continue;
                }

                currentRandomNumber = random.nextInt(9 + 1) + (index * 10);
                lottoCard.get(i).set(index, String.valueOf(currentRandomNumber));
            }
        }
    }

    private static List<Integer> generateIndex() {

        final Random random = new Random();
        final int numbersInLine = 5;

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < numbersInLine; i++) {
            int tempIndex = random.nextInt(9);
            if (indexes.contains(tempIndex)) {
                i--;
                continue;
            }
            indexes.add(tempIndex);
        }

        Collections.sort(indexes);

        return indexes;
    }

    private void fillCard() {
        lottoCard.clear();
        for (int i = 0; i < 3; i++) {
            lottoCard.add(new ArrayList<String>());
            for (int j = 0; j < 9; j++) {
                lottoCard.get(i).add("  ");
            }
        }
    }

    private boolean isThreeNumberInColumn() {

        boolean isContains = false;

        for (int i = 0; i < lottoCard.get(0).size(); i++) {

            int numberInColumn = 0;

            for (int j = 0; j < lottoCard.size(); j++) {
                if (!lottoCard.get(j).get(i).contains("  ")) {
                    numberInColumn++;
                }
            }

            if (numberInColumn == 3) {
                isContains = true;
                break;
            }
        }

        return isContains;
    }

    private void generateFinalCard() {

        do {
            generateCard();
        } while (isThreeNumberInColumn());

    }
}
