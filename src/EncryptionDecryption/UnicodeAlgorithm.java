package EncryptionDecryption;

public class UnicodeAlgorithm implements CryptographyAlgorithm {
    @Override
    public String decryptText(String text, int key) {
        return encryptText(text, -1 * key);
    }

    @Override
    public char encryptCharacter(char c, int key) {
        return (char) (key + c);
    }
}
