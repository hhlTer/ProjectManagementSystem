package view;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    public static void printAsTable(String[] nameColumn, ArrayList<String[]> arrayVolumes){
//sets lohg of table

        //length - length each of entering field
        //+
        //першовизначна ширина
        //FE:
        //lengthCol[0] = (" id:").length = 4
        //lengthCol[1] = (" First name:).length = 12
        //lengthNNN[0] = ("9").length = 1
        //lengthNNN[1] = ("10").length = 2

        final int COUNT_COLUMN = nameColumn.length; // 1. id, 2. name, 3. last_name, 4. skill, 5. salary
//        final int[] LENGTH_COL = new int[COUNT_COLUMN];
        final int[] LENGTH_NNN = new int[COUNT_COLUMN];

        for (int i = 0; i < COUNT_COLUMN; i++) {
            LENGTH_NNN[i] = (nameColumn[i].length()+2);
        }


        for (String[] temp : arrayVolumes) {
            for (int j = 0; j < COUNT_COLUMN; j++) {
                if (LENGTH_NNN[j] < temp[j].length()) {
                    LENGTH_NNN[j] = temp[j].length();
                }
            }
        }

        //верхня планка
        //" ____ _________ _____ __________"
        System.out.println(line("_", LENGTH_NNN));

//                            _____  ____________  ___________  _________
//                          || id: || developer  ||....       ||         ||
        System.out.print("|");
        for (int i = 0; i < COUNT_COLUMN; i++) {
            StringBuilder gaps = new StringBuilder();
            for (int j = 0; j < LENGTH_NNN[i] - nameColumn[i].length(); j++) {
                gaps.append(".");
            }
            System.out.printf("|.%s%s.|", nameColumn[i], gaps.toString());
        }
        System.out.println("|");
        //                  ||    ||        ||     ||       ||        ||
        //                    ,,,,  ,,,,,,,,  ,,,,,  ,,,,,,,  ,,,,,,,,
//        System.out.println(line("`", LENGTH_NNN));
//        System.out.println(line("_", LENGTH_NNN));

        for (String[] arrayVolume : arrayVolumes) {
            System.out.print("|");
            String[] lineWorlds;
            lineWorlds = arrayVolume;
            for (int j = 0; j < COUNT_COLUMN; j++) {
                StringBuilder gaps = new StringBuilder();
                for (int k = 0; k < LENGTH_NNN[j] - lineWorlds[j].length(); k++) {
                    gaps.append(" ");
                }
                System.out.printf("| %s%s |", lineWorlds[j], gaps.toString());
            }
            System.out.println("|");
        }
        System.out.println(line("`", LENGTH_NNN));
    }

    private static String line (String chars, int[] lll){
        StringBuilder l = new StringBuilder();
        for (int aLll : lll) {
            l.append("  ");
            for (int j = 0; j < aLll + 2; j++) {
                l.append(chars);
            }
        }
        return l.toString();
    }

    public static void printAsTable(String[] namesCol, String[] data){
//sets lohg of table
        //

        ArrayList<String[]> temp = new ArrayList<>();
        temp.add(data);
        printAsTable(namesCol, temp);
    }

    public static void printTextFormat(String[] namesCol, String[] data){
        for (int i = 0; i < namesCol.length; i++) {
            System.out.println(namesCol[i] + " - "+ data[i]);
        }
    }
}
