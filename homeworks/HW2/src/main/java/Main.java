package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private BagOfWord bag;
    private String secret;
    private int secret_len;
    private Scanner in;
    private HashSet<Character> secret_set;
    private char[] secret_chars;

//        System.out.print("Input a number: ");
//        int num = in.nextInt();

    private void init() throws IOException {
        this.in = new Scanner(System.in);

        this.bag = new BagOfWord();
        this.secret = this.bag.get_random_word();
        this.secret_len = this.secret.length();
        this.secret_set = new HashSet<>();

        this.secret_chars = this.secret.toCharArray();
        for (int i = 0; i < this.secret_chars.length; i++) {
            this.secret_set.add(this.secret_chars[i]);
        }
    }

    private boolean check_word(String word) {
        if (word.equals(this.secret)) return true;
        int Bulls = 0;
        int Cows = 0;
        char[] word_chars = word.toCharArray();
        for (int i = 0; i < word_chars.length; i++) {
            if (this.secret_set.contains(word_chars[i])) {
                if (word_chars[i]==this.secret_chars[i]) Bulls+=1; else Cows+=1;
            }
        }
        System.out.println(String.format("Bulls: %d   \n" +
                "Cows: %d", Bulls, Cows));
        return false;
    }


    private void run() {

        System.out.println("Welcome to Bulls and Cows game!");
        String user_input;
        boolean wanna_play = true;
        int attempts = 10;

        while (wanna_play) {
            System.out.println(String.format("I offered a %d-letter word, your guess?", this.secret_len));
            System.out.println(this.secret);
            while (attempts > 0) {
                user_input = in.nextLine();
                if (user_input.length() != this.secret_len) {
                    System.out.println(String.format("I offered a %d-letter word, not %d", this.secret_len, user_input.length()));
                    continue;
                }
                if (!user_input.matches("^[a-z]+$")) {
                    System.out.println("Word can include only latin alphabet [a-z]");
                    continue;
                }

                if (check_word(user_input)) break;
//                TODO Добавить обработку сигнала остановки
                attempts -= 1;
            }

            if (attempts > 0) System.out.println("You won!");
            else System.out.println("Its fiasko, bratan!");
            System.out.println("Wanna play again? Y/N");

            while (true) {
                user_input = in.nextLine();
                if (user_input.equals('N')) {
                    wanna_play = false;
                    break;
                }
                if (user_input.equals('Y')) {
                    break;
                } else {
                    System.out.println("I dont understand! Wanna play again? Y/N");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.init();
        app.run();

    }
}


class BagOfWord {
    ArrayList<String> words = new ArrayList();
    Random rnd = new Random(System.currentTimeMillis());

    public BagOfWord() throws IOException {
        File myfile = new File("./dictionary.txt");
        FileReader fr = new FileReader(myfile);
        BufferedReader reader = new BufferedReader(fr);
        String line = null;
        while ((line = reader.readLine()) != null) {
            words.add(line);
        }
        reader.close();
    }

    public String get_random_word() {
        int i = this.rnd.nextInt(this.words.size());
        return (String) this.words.get(i);
    }


}