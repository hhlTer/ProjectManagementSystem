package homeworkHibernate.view;

import homeworkHibernate.controler.main.JDBCStorage;
import homeworkHibernate.enumerated.TypeTable;
import homeworkHibernate.view.DialogImplementation.AdvancedDialog;
import homeworkHibernate.view.DialogMakerInterface.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MainJDBC {
    public static JDBCStorage storage = new JDBCStorage();
    public static void main(String[] args) throws SQLException {
//        JDBCStorage storage = new JDBCStorage();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi!");
        do {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("sleepMainCatch");
            }

            System.out.println("Choice the table\n" +
                    "[D]evelopers | [P]roject  | [C]ustomers  | C[o]mpanies\n" +
                    "[A]dvance    | [E]xit");

            // Table choise dialog


            DialogMaker dialog = () -> null;

            char c = DialogService.getAnswer("dpecoa");
            TypeTable typeTable;
            typeTable = c == 'd' ? TypeTable.developers :
                        c == 'p' ? TypeTable.projects :
                        c == 'c' ? TypeTable.customers :
                        c == 'o' ? TypeTable.companies :
                        null;
            if (c == 'a'){
                new AdvancedDialog().getData();
            } else if (c == 'e'){
                System.err.println("Bye!");
                System.exit(0);
            }
            dialog = dialogMaker(typeTable);
//
//            switch (c) {
//                case 'd':
//                    typeTable = TypeTable.developers;
//                    break;
//                case 'p':
//                    typeTable = TypeTable.projects;
//                    break;
//                case 'c':
//                    typeTable = TypeTable.customers;
//                    break;
//                case 'o':
//                    dialog = dialogMaker(TypeTable.companies);
//                    break;
//                case 'a':
//                    new AdvancedDialog().getData();
//                    continue;
//                case 'e':
//                    System.err.println("Bye!");
//                    System.exit(0);
//                    break;
//            }

            //Command choise dialog
            do {
                long idl;
                System.out.println("Choose command:");
                System.out.println("Crea[t]e, [U]pdate, [R]ead, [L]ist, [D]elete, [C]lear");
                System.out.println("[E]xit");
                char c2 = DialogService.getAnswer("turldce");
                switch (c2) {
//Create
                    case 't':
                        dialog.dialogMake().createDialog();
                        break;
//Read
                    case 'r':
                        dialog.dialogMake().readDialog();
                        break;
//SELECT * FROM
                    case 'l':
                        dialog.dialogMake().listDialog();
                        break;
//Delete where id =
                    case 'd':
                        dialog.dialogMake().deleteDialog();
                        break;
//Clear table
                    case 'c':
                        dialog.dialogMake().eraseDialog();
                        break;
//Update table
                    case 'u':
                        dialog.dialogMake().updateDialog();
                        break;
//Exit
                    case 'e':
                        System.out.println("Bye!");
                        System.exit(0);
                }
            }while (true);//choose command dialog
        }while (true);//main dialog
    }

//        SQLMaker d = new DeveloperSQLMaker(storage);
//
//        ArrayList developers = new ArrayList<>();
//        developers = d.getAllDataTable();
//        Table.printAsTable(developers);

    private static DialogMaker dialogMaker(TypeTable tables) {
        if (tables == TypeTable.developers) {
            return new DialogMakerDevelopers();
        }else if (tables == TypeTable.projects) {
            return new DialogMakerProjects();
        }else if (tables == TypeTable.customers){
            return new DialogMakerCustomer();
        }else if (tables == TypeTable.companies){
            return new DialogMakerCompany();
        }

        throw new RuntimeException("Table " + tables.name() + " not added in project");
    }
}
