package lottoGame.logic;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class KegNumberGenerator {

    private static Set<Integer> usedNumbers = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            int num = generateNumber();
            System.out.println("Generated number: " + num);
            Thread.sleep(2000);
            if (isUsed(num)) {
                System.out.println("#########################################");
                System.err.println(num + " the number is already in the list!");
                System.out.println("#########################################");
                continue;
            }
            System.out.println("----------------------------");
            usedNumbers.add(num);
            System.out.println(num + " was added in usedNumbers List");
            Thread.sleep(2000);
            System.out.println("----------------------------");
            System.out.println("Used numbers: " + usedNumbers);
            System.out.println("----------------------------");
            Thread.sleep(2000);
        }
    }

    private static int generateNumber() {
        return new Random().nextInt(90) + 1;
    }

    private static boolean isUsed(final int kegNumber) {
        return usedNumbers.contains(kegNumber);
    }

    public void addInUsedNumbers(final int kegNumber) {
        usedNumbers.add(kegNumber);
    }

    public void clearUsedNumbers() {
        usedNumbers.clear();
    }

}
