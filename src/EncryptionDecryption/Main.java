package EncryptionDecryption;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String... args) {
        Map<String, String> arguments = new HashMap<>() {{
            put("mode", "enc");
            put("key", "0");
            put("data", "");
        }};
        for (int i = 0; i < args.length - 1; i += 2) {
            arguments.put(args[i].replace("-", ""), args[i + 1]);
        }
        System.out.println(cipherTextEncrypt(
                arguments.get("data"),
                Integer.parseInt(arguments.get("key")),
                arguments.get("mode").equals("enc")
        ));
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
