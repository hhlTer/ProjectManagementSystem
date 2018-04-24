package homeworkHibernate.view;

import homeworkHibernate.model.GenerallyTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //one line from to arrays
    public static void printAsTable(String[] namesCol, String[] data){
        ArrayList<String[]> temp = new ArrayList<>();
        temp.add(data);
        printAsTable(namesCol, temp);
    }

    //one line print from one array
    public static void printAsTable(String... data){
        if (data.length%2 != 0){
            data = Arrays.copyOf(data, data.length + 1);
            data[data.length - 1] = "";
        }
        ArrayList<String[]> temp = new ArrayList<>();
        final int len = data.length/2;
        String[] namesCol = new String[len];
        String[] param = new String[len];
        for (int i = 0; i < data.length; i++) {
            if (i < len) {
                namesCol[i] = data[i];
            } else {
                param[i-len] = data[i];
            }
        }
        temp.add(param);
        printAsTable(namesCol, temp);
    }

    //GeneralTable list print
    public static void printAsTable(ArrayList<? extends GenerallyTable> gt){
        ArrayList<String[]> strings;
        strings = gt.stream()
                .map(GenerallyTable::getAll)
                .collect(Collectors.toCollection(ArrayList::new));
        printAsTable(gt.get(0).getPrm(), strings);
    }

    //General table one line print
    public static void printAsTable(GenerallyTable gt){
        ArrayList<String[]> strings = new ArrayList<>();
        strings.add(gt.getAll());
        printAsTable(gt.getPrm(), strings);
    }
    public static void printTextFormat(String[] namesCol, String[] data){
        for (int i = 0; i < namesCol.length; i++) {
            System.out.println(namesCol[i] + " - "+ data[i]);
        }
    }
}
