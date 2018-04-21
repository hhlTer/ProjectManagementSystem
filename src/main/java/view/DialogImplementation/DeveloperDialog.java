package view.DialogImplementation;

import controler.commands.DeveloperSQLMaker;
import controler.commands.DoItHibernate;
import controler.commands.HiberInterface;
import controler.commands.SQLMaker;
import model.Developer;
import model.Project;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class DeveloperDialog implements CaseDialog {
    private Developer developer;
    private ArrayList<Developer> developers;

    private static HiberInterface<Developer> hiberSQLMaker = new DoItHibernate<>();

    @Override
    public void createDialog() {
        developer = new Developer();
        fillDeveloper(developer);
        try {
            hiberSQLMaker.insertIntoTable(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                developer = hiberSQLMaker.getFromTableById(Developer.class, id);
                Table.printAsTable(Project.getParam(), developer.getAll());
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Project not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void listDialog() {
        try {
            developers = hiberSQLMaker.getAllDataTable(Project.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.printAsTable(developers);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillDeveloper(developer);
        try {
            hiberSQLMaker.updateInTable(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDialog() {
        readDialog();
        System.out.println("Delete developer?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                hiberSQLMaker.deleteCortege(developer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eraseDialog() {
        System.out.println("Erase table?\n[Y]es/[N]o?");
        char answer = DialogService.getAnswer("yn");
        if (answer == 'y'){
            try {
                hiberSQLMaker.eraseTable("project");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void fillDeveloper(Developer developer){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Developer.getParam(), 1, Developer.getParam().length)
        );

        int age = Integer.parseInt(param[1]);
        boolean sex = param[2].equals("male");
        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(param[3]));

        developer.setName(param[0]);
        developer.setAge(age);
        developer.setSex(sex);
        developer.setSalary(salary);
    }

}
