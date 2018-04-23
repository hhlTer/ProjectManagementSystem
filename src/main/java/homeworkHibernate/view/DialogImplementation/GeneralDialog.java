package homeworkHibernate.view.DialogImplementation;

import homeworkHibernate.controler.commands.DoItHibernate;
import homeworkHibernate.controler.commands.HiberInterface;
import homeworkHibernate.model.*;
import homeworkHibernate.view.DialogService;
import homeworkHibernate.view.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


class GeneralDialog {
    public static HiberInterface<GenerallyTable> hiberSQLMaker = new DoItHibernate<>();

    private ArrayList<GenerallyTable> generallyTables;
    private GenerallyTable generallyTable;

    void createDialog(GenerallyTable gt) {
            fillTable(gt);
            try {
                hiberSQLMaker.insertIntoTable(gt);
                System.out.println("Done!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    void readDialog(Class <? extends GenerallyTable> clazz) {
        while (true) {
            long id = DialogService.getLongId();
            try {
                generallyTable = hiberSQLMaker.getFromTableById(clazz, id);
                Table.printAsTable(generallyTable.getPrm(), generallyTable.getAll());
                break;
            } catch (NoSuchElementException e) {
                System.out.println(clazz.getName() + " not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void listDialog(Class<? extends GenerallyTable> clazz) {
        try {
            generallyTables = hiberSQLMaker.getAllDataTable(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.printAsTable(generallyTables);
    }

    void updateDialog(Class<? extends GenerallyTable> clazz) {
        readDialog(clazz);
        fillTable(generallyTable);
        try {
            hiberSQLMaker.updateInTable(generallyTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteDialog(Class<? extends GenerallyTable> clazz) {
        readDialog(clazz);
        System.out.println("Delete " + clazz.getName().toLowerCase() + "?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                hiberSQLMaker.deleteCortege(generallyTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void eraseDialog(Class<? extends GenerallyTable> clazz) {
        System.out.println("Erase table?\n[Y]es/[N]o?");
        char answer = DialogService.getAnswer("yn");
        if (answer == 'y'){
            try {
                hiberSQLMaker.eraseTable(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable(GenerallyTable gt){
        if (gt instanceof Project){
            ProjectDialog.fillProject((Project) gt);
        }
        if (gt instanceof Developer){
            DeveloperDialog.fillDeveloper((Developer) gt);
        }
    }
}
