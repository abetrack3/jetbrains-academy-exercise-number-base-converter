import java.util.Scanner;

public class NumberBaseConverter {

    public static void main(String... args) {
        // write your code here

        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        String mode;
        while (!(mode = sc.next()).equals("/exit")) {
            switch (mode) {
                case "/from" : {
                    System.out.println("Enter a number in decimal system: ");
                    int decimal = Integer.parseInt(sc.next());
                    System.out.println("Enter the target base: ");
                    int base = Integer.parseInt(sc.next());
                    System.out.println("Conversion result: " + convertFromDecimal(decimal, base) + "\n");
                    break;
                }
                case "/to" : {
                    System.out.println("Enter source number: ");
                    String source = sc.next();
                    System.out.println("Enter source base: ");
                    int base = Integer.parseInt(sc.next());
                    System.out.println("Conversion to decimal result: " + convertToDecimal(source, base) + "\n");
                    break;
                }
            }
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        }
    }

    public static int convertToDecimal(String source, int sourceBase) {
        int ans = 0;
        int amp = 1;
        for (int i = source.length(); i --> 0;) {
            ans += getLiteralValue(source.charAt(i)) * amp;
            amp *= sourceBase;
        }
        return ans;
    }

    private static String convertFromDecimal(int dec, int targetBase) {
        StringBuilder result = new StringBuilder();
        while (dec != 0) {
            result.insert(0, getLiteral(dec % targetBase));
            dec /= targetBase;
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
