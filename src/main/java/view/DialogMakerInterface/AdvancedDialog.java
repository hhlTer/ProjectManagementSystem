package view.DialogMakerInterface;

import controler.commands.AdvancedSQL;
import controler.commands.ProjectSQLMaker;
import controler.commands.SQLMaker;
import controler.main.JDBCStorage;
import model.Developer;
import model.Project;
import view.DialogImplementation.CaseDialog;
import view.DialogImplementation.ProjectDialog;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AdvancedDialog {

    private JDBCStorage storage = MainJDBC.storage;
        private AdvancedSQL advancedSQL = new ProjectSQLMaker(storage);
        private CaseDialog dialog = new ProjectDialog();
        private SQLMaker<Project> projectSQLMaker = new ProjectSQLMaker(storage);
        private Project project;

    public void getData(){
        boolean flag = true;
        do {

            System.out.println("Choose action:");
            System.out.println(" 1 - salary all developers of project");
            System.out.println(" 2 - List of all developers of project");


            char ans = DialogService.getAnswer("120");
            switch (ans){
                case '1':
                    caseProject();
                    advancedSQL.getSalary(project);
                    break;
                case '0':
                    flag = false;
                    break;
            }
        }while (flag);
    }

    private void caseProject(){
        try {
            ArrayList<Project> projects = projectSQLMaker.getAllDataTable();
            ArrayList<String[]> strings = new ArrayList<>();
            for (Project p:
                 projects) {
                strings.add(p.getAll());
            }

            DialogService.table().printAsTable(Project.getParam(), strings);
            for (;;) {
                    System.out.println("Choose a project: ");
                    long id = DialogService.getLongId();
                    project = projectSQLMaker.selectFromTableById(id);
                    break;
                 }
            } catch (NoSuchElementException e) {
                System.out.println("Project not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
