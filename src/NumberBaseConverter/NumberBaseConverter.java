package NumberBaseConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class NumberBaseConverter {

    public static final int DEFAULT_SCALE = 5;

    private int sourceBase;
    private int targetBase;
    private Scanner scanner;

    public static void main(String... args) {
        NumberBaseConverter instance = new NumberBaseConverter();
        instance.run();
    }

    void run() {
        // write your code here

        scanner = new Scanner(System.in);
        String query = "Enter two numbers in format: {source base} {target base} (To quit type /exit) ";
        System.out.println(query);
        String s;

        while (!(s = scanner.next()).equals("/exit")) {
            sourceBase = parseInt(s);
            targetBase = parseInt(scanner.next());
            baseConverter();
            System.out.println(query);
        }

        scanner.close();
    }

    private void baseConverter() {
        String query = String.format(
                "Enter number in base %d to convert to base %d (To go back type /back) ",
                sourceBase,
                targetBase
        );
        System.out.println(query);
        String s;
        while (!(s = scanner.next()).equals("/back")) {
            System.out.println("Conversion result: " + convert(s) + "\n");
            System.out.println(query);
        }
    }

    private String convert(String n) {
        BigDecimal temp = convertToDecimal(n, sourceBase);
        return convertFromDecimal(temp, targetBase);
    }

    public static BigDecimal convertToDecimal(String source, int sourceBase) {
        BigDecimal ans = BigDecimal.ZERO;
        BigDecimal amp = BigDecimal.ONE;
        BigDecimal base = new BigDecimal(String.valueOf(sourceBase));
        String[] split = source.split("\\.");
        String integer = split[0]; //integer portion
        for (int i = integer.length(); i --> 0;) {
            ans = ans.add(
                    new BigDecimal(String.valueOf(getLiteralValue(integer.charAt(i))))
                            .multiply(amp)
            );
            amp = amp.multiply(base);
        }
        if (split.length == 1) return ans; //no fraction portion
        String fraction = split[1]; // fraction portion
        amp = BigDecimal.ONE;
        for (int i = 1; i <= fraction.length(); i++) {
            int val = getLiteralValue(fraction.charAt(i-1));
            amp = amp.divide(base, DEFAULT_SCALE * 4, RoundingMode.HALF_DOWN);
            ans = ans.add(amp.multiply(new BigDecimal(String.valueOf(val))));
        }
        return ans;
    }

    private static String convertFromDecimal(BigDecimal dec, int targetBase) {
        BigInteger base = BigInteger.valueOf(targetBase);
        BigInteger integer = dec.multiply(BigDecimal.valueOf(targetBase).pow(DEFAULT_SCALE)).toBigInteger();
        StringBuilder result = new StringBuilder();
        while (!integer.equals(BigInteger.ZERO)) {
            BigInteger[] quotientAndRemainder = integer.divideAndRemainder(base);
            result.insert(0, getLiteral(quotientAndRemainder[1].intValue()));
            integer = quotientAndRemainder[0];
        }
        result.insert(result.length() - DEFAULT_SCALE, '.');
        if (!dec.toString().contains(".")) return result.substring(0, result.length() - DEFAULT_SCALE - 1);
        else return result.toString();
    }

    private static int getLiteralValue (char c) {
        if ('0' <= c && c <= '9') { //actual digits
            return c - 48;
        } else if ('A' <= c && c <= 'Z') { //capital letters
            return c - 65 + 10;
        } else {
            return c - 97 +10; //small letters
        }
    }

    private static String getLiteral(int a) {
        if (a < 10) {
            return String.valueOf(a);
        } else {
            char c = (char) (65 + a - 10);
            return String.valueOf(c);
        }
    }
}
