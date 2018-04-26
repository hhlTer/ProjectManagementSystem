package homeworkHibernate.view.dialogServise;

import java.math.BigDecimal;
import java.util.Scanner;

public class DialogService {
    public static char getAnswer(String crud) {
        System.out.println(":>");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        char c = ' ';
        if (answer.length() > 0) {
            c = answer.toLowerCase().charAt(0);
        }
        if (!crud.contains("" + c))
            getAnswer(crud);
        return c;
    }

    public static long getLongId() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(":>");
        Scanner sc = new Scanner(System.in);
        long id = 0;
        try {
            id = sc.nextLong();
            return id;
        } catch (Exception e) {
            System.out.println("Wrong input format");
            getLongId();
        }
        return id;
    }

    private static BigDecimal getBigDecimal() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n :>");
        Scanner sc = new Scanner(System.in);
        BigDecimal id;
        try {
            id = sc.nextBigDecimal();
            return id;
        } catch (Exception e) {
            System.out.println("Wrong input format");
            getBigDecimal();
        }
        return null;
    }

    public static String[] getData(String[] field){
        String[] temp = new String[field.length];
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        for (String s:
             field) {
            System.out.println("Enter " + s);

            if (s.toLowerCase().equals("sex")) {
                System.out.println("([m]ale/[f]emale)");
                char ts = getAnswer("mf");
                temp[count++] = (ts == 'm') ? "male" : "female";
                continue;
            }

            if (s.toLowerCase().equals("age")){
                temp[count++] = "" + getLongId();
                continue;
            }

            if (s.toLowerCase().equals("salary") || s.toLowerCase().equals("cost")){
                if (getBigDecimal().toString() == null){
                    temp[count++] = "0";
                }
                temp[count++] = getBigDecimal().toString();
                continue;
            }
            temp[count++] = scanner.nextLine();
        }
        return temp;
    }
    public static Table table(){
        return new Table();
    }
}
