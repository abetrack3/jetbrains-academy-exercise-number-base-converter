package EncryptionDecryption;

public class AlgorithmFactory {

    static CryptographyAlgorithm getAlgorithm(String type) {
        return switch (type) {
            case "shift"    -> new ShiftAlgorithm();
            case "unicode"  -> new UnicodeAlgorithm();
            default         -> null;
        };
    }

}
