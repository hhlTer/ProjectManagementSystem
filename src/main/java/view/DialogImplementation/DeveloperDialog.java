package view.DialogImplementation;

import controler.commands.DeveloperSQLMaker;
import controler.commands.SQLMaker;
import model.Developer;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class DeveloperDialog implements CaseDialog {
    private SQLMaker<Developer> developerSQLMaker = new DeveloperSQLMaker(MainJDBC.storage);
    private Developer developer;
    private ArrayList<Developer> developers;

    @Override
    public void createDialog() {
        developer = new Developer();
        fillDeveloper(developer);
        try {
            developerSQLMaker.insertIntoTable(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                developer = (Developer) developerSQLMaker.selectFromTableById(id);
                Table.printAsTable(Developer.getParam(), developer.getAll());
                break;
                //service.printTable();
            } catch (NoSuchElementException e) {
                System.out.println("Developer not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
// Add developer to project?
//        service.addToMtm(developer, new Project(), new ProjectDialog(), developersCRUD);
// Show project for Developer?

    }

    @Override
    public void listDialog() {
        try {
            developers = developerSQLMaker.getAllDataTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> tempArray = new ArrayList<>(developers.size());
        for (Developer d:
                developers) {
            tempArray.add(d.getAll());
        }
        Table.printAsTable(Developer.getParam(), tempArray);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillDeveloper(developer);

        try {
            developerSQLMaker.updateInTable(developer);
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
                developerSQLMaker.deleteCortege(developer.getId());
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
                developerSQLMaker.eraseTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillDeveloper(Developer developer){
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
