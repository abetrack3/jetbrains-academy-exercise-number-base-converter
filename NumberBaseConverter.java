import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class NumberBaseConverter {

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
            System.out.println("conversion result: " + convert(s) + "\n");
            System.out.println(query);
        }
    }

    private String convert(String n) {
        BigInteger temp = convertToDecimal(n, sourceBase);
        return convertFromDecimal(temp, targetBase);
    }

    public static BigInteger convertToDecimal(String source, int sourceBase) {
        BigInteger ans = BigInteger.ZERO;
        BigInteger amp = BigInteger.ONE;
        for (int i = source.length(); i --> 0;) {
            ans = ans.add(
                    new BigInteger(String.valueOf(getLiteralValue(source.charAt(i))))
                            .multiply(amp)
            );
            amp = amp.multiply(
                    new BigInteger(String.valueOf(sourceBase))
            );
        }

        return ans;
    }

    private static String convertFromDecimal(BigInteger dec, int targetBase) {
        StringBuilder result = new StringBuilder();
        while (!dec.equals(BigInteger.ZERO)) {
            BigInteger[] quotientAndRemainder = dec.divideAndRemainder(BigInteger.valueOf(targetBase));
            result.insert(0, getLiteral(quotientAndRemainder[1].intValue()));
            dec = quotientAndRemainder[0];
        }
        return result.toString();
    }

    private static int getLiteralValue (char c) {
        if ('0' <= c && c <= '9') {
            return c - 48;
        } else if ('A' <= c && c <= 'Z') {
            return c - 65 + 10;
        } else {
            return c - 97 +10;
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
