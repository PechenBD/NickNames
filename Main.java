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
        List<String> names = new ArrayList<>();

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        for (String text : texts) {
            Runnable logic1 = () -> {
                if (text.equals(new StringBuilder().append(text).reverse().toString())) {
                    if (text.length() == 3 && !names.contains(text)) {
                        names.add(text);
                        countLenght3.getAndIncrement();
                    }
                }
                if (text.equals(new StringBuilder().append(text).reverse().toString())) {
                    if (text.length() == 4 && !names.contains(text)) {
                        names.add(text);
                        countLenght4.getAndIncrement();
                    }
                }
                if (text.equals(new StringBuilder().append(text).reverse().toString())) {
                    if (text.length() == 5 && !names.contains(text)) {
                        names.add(text);
                        countLenght5.getAndIncrement();
                    }
                }
            };

            Runnable logic2 = () -> {
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
                    if (text.length() == 3 && !names.contains(text)) {
                        names.add(text);
                        countLenght3.getAndIncrement();
                    }
                }
                if (same) {
                    if (text.length() == 4 && !names.contains(text)) {
                        names.add(text);
                        countLenght4.getAndIncrement();
                    }
                }
                if (same) {
                    if (text.length() == 5 && !names.contains(text)) {
                        names.add(text);
                        countLenght5.getAndIncrement();
                    }
                }
            };

            Runnable logic3 = () -> {
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
                    if (text.length() == 3 && !names.contains(text)) {
                        names.add(text);
                        countLenght3.getAndIncrement();
                    }
                }
                if (order) {
                    if (text.length() == 4 && !names.contains(text)) {
                        names.add(text);
                        countLenght4.getAndIncrement();
                    }
                }
                if (order) {
                    if (text.length() == 5 && !names.contains(text)) {
                        names.add(text);
                        countLenght5.getAndIncrement();
                    }
                }
            };

            System.out.println(text);

            Thread myThread1 = new Thread(logic1);
            threads.add(myThread1);
            myThread1.start();

            Thread myThread2 = new Thread(logic2);
            threads.add(myThread2);
            myThread2.start();

            Thread myThread3 = new Thread(logic3);
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
}