package EncryptionDecryption;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mode = scanner.nextLine();
        String message = scanner.nextLine();
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println(cipherTextEncrypt(message, key, mode.equals("dec")));
    }

    public static String cipherTextEncrypt(String text, int key, boolean decrypt) {
        return text
                .chars()
                .map(e -> encryptChar(e, decrypt ? -key : key))
                .mapToObj(e -> String.valueOf((char) e))
                .collect(Collectors.joining());
    }

    public static char encryptChar(int character, int key) {
        return (char) (key + character);
    }

}
