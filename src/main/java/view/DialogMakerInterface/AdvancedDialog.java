package view.DialogMakerInterface;

import controler.commands.AdvanceSQLMaker;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AdvancedDialog {

    private JDBCStorage storage = MainJDBC.storage;
        private AdvancedSQL advancedSQL = new AdvanceSQLMaker(storage);
        private CaseDialog dialog = new ProjectDialog();
        private SQLMaker<Project> projectSQLMaker = new ProjectSQLMaker(storage);
        private Project project;

    public void getData(){
        boolean flag = true;
        do {

            System.out.println("Choose action:");
            System.out.println(" 1 - salary all developers of project");
            System.out.println(" 2 - list of all developers of project");
            System.out.println(" 3 - list of all developers as skill");
            System.out.println(" 0 - Exit");


            char ans = DialogService.getAnswer("1230");
            switch (ans){
                case '1':
                    caseProject();
                    advancedSQL.showDevelopersCost(project);
                    break;
                case '2':
                    caseProject();
                    advancedSQL.showDevelopersJobs(project);
                    break;
                case '3':
                    String skill = caseSkill();
                    advancedSQL.showDevelopersSkill(skill);
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

    private String caseSkill(){
        System.out.println("Chose available skill");
        System.out.println("[1] - Java\n[2] - C#\n[3] - C++\n[4] - JavaScript");
        char ans = DialogService.getAnswer("1234");
        return ans == '1' ? "java" :
               ans == '2' ? "c#" :
               ans == '3' ? "c++" :
                            "js";
    }
}
