import java.util.Scanner;

public class NumberBaseConverter {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number in decimal system: ");
        int num = sc.nextInt();
        System.out.print("Enter target base: ");
        int base = sc.nextInt();
        
        String result = "";
        while(num != 0) {
            result = getSymbol(num % base) + result;
            num /= base;
        }
        System.out.println("Conversion result: " + result);
    }
    
    public static String getSymbol(int val) {
    	
    	switch(val) {
    		case 10: return "A";
    		case 11: return "B";
    		case 12: return "C";
    		case 13: return "D";
    		case 14: return "E";
    		case 15: return "F";
    		default: return "" + val;
    	}
    }
}
