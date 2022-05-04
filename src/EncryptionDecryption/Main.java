package EncryptionDecryption;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println(ciphertext(message, key));
    }

    public static String ciphertext(String text, int key) {
        return text
                .chars()
                .map(e -> encryptChar(e, key))
                .mapToObj(e -> String.valueOf((char) e))
                .collect(Collectors.joining());
    }

    public static char encryptChar(int character, int key) {
        String x = String.valueOf((char) character);
        if (x.matches("[A-Z]")) return (char) (65 + (key + x.charAt(0) - 65) % 26);
        else if (x.matches("[a-z]")) return (char) (97 + (key + x.charAt(0) - 97) % 26);
        else return x.charAt(0);
    }

}
