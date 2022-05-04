package EncryptionDecryption;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        Map<String, String> argumentMap = getArguments(args);
        String data = getData(argumentMap);
        int key = Integer.parseInt(argumentMap.get("key"));
        boolean encrypt = argumentMap.get("mode").equals("enc");
        setOut(argumentMap);

        CryptographyAlgorithm algorithm = AlgorithmFactory.getAlgorithm(argumentMap.get("alg"));
        String encryptDecrypt = encrypt ? algorithm.encryptText(data, key) : algorithm.decryptText(data, key);
        System.out.println(encryptDecrypt);
        System.out.flush();
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
            put("alg", "shift");
            put("data", null);
            put("in", null);
            put("out", null);
        }};
        for (int i = 0; i < args.length - 1; i += 2) {
            argumentMap.put(args[i].replace("-", ""), args[i + 1]);
        }
        return argumentMap;
    }

}
