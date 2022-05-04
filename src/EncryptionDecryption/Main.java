package EncryptionDecryption;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String message = "we found a treasure!";
        System.out.println(ciphertext(message));
    }

    public static String ciphertext(String text) {
        return Stream.of(text.split(""))
                .map(x -> {
                    if (x.matches("[A-Z]")) return (char) (90 - x.charAt(0) + 65);
                    else if (x.matches("[a-z]")) return (char) (122 - x.charAt(0) + 97);
                    else return x.charAt(0);
                })
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
