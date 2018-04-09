package view.DialogImplementation;

import controler.commands.ProjectSQLMaker;
import controler.commands.SQLMaker;
import model.Project;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ProjectDialog implements CaseDialog{
    private Project project;
    private SQLMaker<Project> projectSQLMaker = new ProjectSQLMaker(MainJDBC.storage);
    private ArrayList<Project> projects;

    @Override
    public void createDialog() {
        project = new Project();
        fillProject(project);
        try {
            projectSQLMaker.insertIntoTable(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                project = projectSQLMaker.selectFromTableById(id);
                Table.printAsTable(Project.getParam(), project.getAll());
                break;
                //service.printTable();
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
            projects = projectSQLMaker.getAllDataTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> tempArray = new ArrayList<>(projects.size());
        for (Project p:
                projects) {
            tempArray.add(p.getAll());
        }
        Table.printAsTable(Project.getParam(), tempArray);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillProject(project);

        try {
            projectSQLMaker.updateInTable(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDialog() {
        readDialog();
        System.out.println("Delete project?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                projectSQLMaker.deleteCortege(project.getId());
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
                projectSQLMaker.eraseTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillProject(Project p){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Project.getParam(), 1, Project.getParam().length)
        );

        BigDecimal cost = BigDecimal.valueOf(Long.parseLong(param[2]));

        p.setProject_name(param[0]);
        p.setDescription(param[1]);
        p.setCost(cost);
    }

}
