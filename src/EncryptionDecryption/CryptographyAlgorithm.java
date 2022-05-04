package EncryptionDecryption;

import java.util.stream.Collectors;

public interface CryptographyAlgorithm {

    default String encryptText(String text, int key){
        return text
                .chars()
                .map(c -> encryptCharacter((char) c, key))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    String decryptText(String text, int key);

    char encryptCharacter(char c, int key);

}
