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
            System.out.println(" 4 - list of all developers as grade");
            System.out.println(" 5 - list count of all developers in project");
            System.out.println(" 0 - Exit");


            char ans = DialogService.getAnswer("123450");
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
                    advancedSQL.showDevelopersSkill(skill, "skill");
                    break;
                case '4':
                    String grade = caseGrade();
                    advancedSQL.showDevelopersGrade(grade, "grade");
                    break;
                case '5':
                    caseProject();
                    advancedSQL.showCountOfDeveloperByProject(project);
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

    private String caseSkill(){
        System.out.println("Chose available skill");
        System.out.println("[1] - Java\n[2] - C#\n[3] - C++\n[4] - JavaScript");
        char ans = DialogService.getAnswer("1234");
        return ans == '1' ? "java" :
               ans == '2' ? "c#" :
               ans == '3' ? "c++" :
                            "js";
    }
    private String caseGrade(){
        System.out.println("Chose developers grade");
        System.out.println("[1] - junior\n[2] - middle\n[3] - senior\n");
        char ans = DialogService.getAnswer("1234");
        return ans == '1' ? "junior" :
               ans == '2' ? "middle" :
                            "senior";
    }
}
