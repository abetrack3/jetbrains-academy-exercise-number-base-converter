package ReadabilityScore;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final HashMap<String,String> map;

    static {
        map = new HashMap<String,String>();
        map.put("ARI",  "readability.AutomatedReadabilityIndex");
        map.put("FK",   "readability.FleschKincaidReadabilityTest");
        map.put("SMOG", "readability.Gobbledygook");
        map.put("CL",   "readability.ColemanLiauIndex");
    }

    public static void main(String... args) throws FileNotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileName = args[0];
        Text text = new Text(new Scanner(new File(fileName)).nextLine());
        System.out.println(text);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        String mode = new Scanner(System.in).nextLine();
        if (!mode.equals("all")) {
            Class test = Class.forName(map.get(mode));
            System.out.println(test.getDeclaredConstructor(Text.class).newInstance(text).toString());
        } else {
            for (String testName : map.values()) {
                Class test = Class.forName(testName);
                System.out.println(test.getDeclaredConstructor(Text.class).newInstance(text).toString());
            }
        }
    }

}
