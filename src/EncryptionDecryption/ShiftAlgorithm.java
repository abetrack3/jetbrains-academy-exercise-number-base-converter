package EncryptionDecryption;

public class ShiftAlgorithm implements CryptographyAlgorithm {
    @Override
    public String decryptText(String text, int key) {
        return encryptText(text, 26 - key);
    }

    @Override
    public char encryptCharacter(char c, int key) {
        String x = String.valueOf(c);
        if (x.matches("[A-Z]")) return (char) (65 + (key + x.charAt(0) - 65) % 26);
        else if (x.matches("[a-z]")) return (char) (97 + (key + x.charAt(0) - 97) % 26);
        else return x.charAt(0);
    }
}
