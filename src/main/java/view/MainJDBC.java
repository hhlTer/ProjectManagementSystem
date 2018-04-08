package view;

import controler.commands.DeveloperSQLMaker;
import controler.commands.SQLMaker;
import controler.main.JDBCStorage;
import enumerated.TypeTable;
import model.Developer;
import view.DialogImplementation.CaseDialog;
import view.DialogMakerInterface.DialogMaker;
import view.DialogMakerInterface.DialogMakerDevelopers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainJDBC {
    public static JDBCStorage storage = new JDBCStorage();
    public static void main(String[] args) throws SQLException {
//        JDBCStorage storage = new JDBCStorage();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleepMainCatch");
        }

        System.out.println("Choice the table\n" +
                "[D]evelopers, [P]roject\n" +
                "[E]xit");

        // Table choise dialog


        DialogMaker dialog = () -> null;

        char c = DialogService.getAnswer("dpe");
        switch (c){
            case 'd':
                dialog = dialogMaker(TypeTable.developers);
                break;
            case 'p':
                dialog = dialogMaker(TypeTable.projects);
                break;
            case 'e':
                System.err.println("Bye!");
                break;
            default:                 dialog = dialogMaker(TypeTable.developers);

        }

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
                    dialog.dialogMake().clearDialog();
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
        }
        while (true) ;
    }

//        SQLMaker d = new DeveloperSQLMaker(storage);
//
//        ArrayList developers = new ArrayList<>();
//        developers = d.getAllDataTable();
//        Table.printAsTable(developers);

    private static DialogMaker dialogMaker(TypeTable tables) {
        if (tables == TypeTable.developers) {
            return new DialogMakerDevelopers();
        }
//        else if (tables == TypeTable.customers) {
//            return new DialogMakerProjects();
//        }
        throw new RuntimeException("Wrong table name");
    }
}
