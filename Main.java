import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger countLenght3 = new AtomicInteger(0);
    public static AtomicInteger countLenght4 = new AtomicInteger(0);
    public static AtomicInteger countLenght5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        for (String text : texts) {
            Runnable isPalindrome = () -> {
                if (text.equals(new StringBuilder().append(text).reverse().toString())) {
                    switchTextLenght(text.length());
                }
            };

            Runnable sameLetters = () -> {
                int i = 0;
                boolean same = false;
                while (i < text.length() - 1) {
                    if (text.charAt(i) == text.charAt(i + 1)) {
                        same = true;
                    } else {
                        same = false;
                        break;
                    }
                    i++;
                }
                if (same) {
                    switchTextLenght(text.length());
                }
            };

            Runnable increaseLetters = () -> {
                int i = 0;
                boolean order = false;
                while (i < text.length() - 1) {
                    if (text.charAt(i) <= text.charAt(i + 1)) {
                        order = true;
                    } else {
                        order = false;
                        break;
                    }
                    i++;
                }
                if (order) {
                    switchTextLenght(text.length());
                }
            };

            System.out.println(text);

            Thread myThread1 = new Thread(isPalindrome);
            threads.add(myThread1);
            myThread1.start();

            Thread myThread2 = new Thread(sameLetters);
            threads.add(myThread2);
            myThread2.start();

            Thread myThread3 = new Thread(increaseLetters);
            threads.add(myThread3);
            myThread3.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Красивых слов с длиной 3: " + countLenght3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + countLenght4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + countLenght5.get() + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void switchTextLenght(int textLenght) {
        switch(textLenght) {
            case 3:
                countLenght3.getAndIncrement();
                break;
            case 4:
                countLenght4.getAndIncrement();
                break;
            case 5:
                countLenght5.getAndIncrement();
                break;
        }
    }
}