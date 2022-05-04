package EncryptionDecryption;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String... args) {
        Map<String, String> argumentMap = getArguments(args);
        String data = getData(argumentMap);
        int key = Integer.parseInt(argumentMap.get("key"));
        boolean encrypt = argumentMap.get("mode").equals("enc");
        setOut(argumentMap);
        String encryptDecrypt = cipherTextEncrypt(data, key, encrypt);
        System.out.println(encryptDecrypt);
    }

    public static void setOut(Map<String, String> argumentMap) {
        try {
            System.setOut(new PrintStream(argumentMap.get("out")));
        } catch (Exception ignored) {}
    }

    public static String getData(Map<String, String> argumentMap) {
        String data = argumentMap.get("data");
        if (data == null) {
            try (Scanner scanner = new Scanner(new File(argumentMap.get("in")))) {
                data = scanner.nextLine();
            } catch (Exception e) {
                data = "";
            }
        }
        return data;
    }

    public static Map<String, String> getArguments(String... args) {
        Map<String, String> argumentMap = new HashMap<>() {{
            put("mode", "enc");
            put("key", "0");
            put("data", null);
            put("in", null);
            put("out", null);
        }};
        for (int i = 0; i < args.length - 1; i += 2) {
            argumentMap.put(args[i].replace("-", ""), args[i + 1]);
        }
        return argumentMap;
    }

    public static String cipherTextEncrypt(String text, int key, boolean encrypt) {
        return text
                .chars()
                .map(e -> encryptChar(e, encrypt ? key : -key))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    public static char encryptChar(int character, int key) {
        return (char) (key + character);
    }

}
